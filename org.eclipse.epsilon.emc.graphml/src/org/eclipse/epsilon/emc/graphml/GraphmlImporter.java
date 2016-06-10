package org.eclipse.epsilon.emc.graphml;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.eclipse.epsilon.emc.muddle.BooleanType;
import org.eclipse.epsilon.emc.muddle.Feature;
import org.eclipse.epsilon.emc.muddle.IntegerType;
import org.eclipse.epsilon.emc.muddle.LinkElementType;
import org.eclipse.epsilon.emc.muddle.Muddle;
import org.eclipse.epsilon.emc.muddle.MuddleElement;
import org.eclipse.epsilon.emc.muddle.MuddleElementType;
import org.eclipse.epsilon.emc.muddle.MuddleFactory;
import org.eclipse.epsilon.emc.muddle.RealType;
import org.eclipse.epsilon.emc.muddle.Slot;
import org.eclipse.epsilon.emc.muddle.Type;
import org.eclipse.epsilon.eol.EolModule;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.filter.Filter;
import org.jdom.input.SAXBuilder;

public class GraphmlImporter {
	
	// Add support for node label patterns
	protected Muddle graph = null;
	protected HashMap<String, MuddleElement> nodeMap;
	protected HashMap<MuddleElement, Element> nodeElementMap;
	protected Element graphElement = null;
	protected Namespace namespace;
	protected List<OrphanLink> orphanEdges;
	protected GraphmlConfiguration configuration;
	protected List<MuddleElement> referenceNodes;
	
	
	
	public static void main(String[] args) throws Exception {
		
		GraphmlModel model = new GraphmlModel();
		model.setFile(new File("/Users/dimitrioskolovos/Downloads/eclipse-epsilon-kepler/workspace/org.eclipse.epsilon.emc.graphml/samples/template.graphml"));
		model.setName("X");
		model.load();
		
		EolModule module = new EolModule();
		module.parse("Element.all.title.println();");
		module.getContext().getModelRepository().addModel(model);
		module.execute();
		
		/*
		GraphmlImporter importer = new GraphmlImporter();
		Muddle graph = importer.importGraph(new File("/Users/dimitrioskolovos/Desktop/sample.graphml"));
		
		GraphmlModel model = new GraphmlModel();
		model.setName("X");
		model.setGraph(graph);
		
		EolModule module = new EolModule();
		
		module.parse("City.all.collect(c|c.incoming.collect(r|r.distance)).println();");
		//module.parse("Road.all.collect(r|r.distance).println();");
		//module.parse("Admin.all.collect(a|a.language).println();");
		//module.parse("Entity.all.second().admin.collect(a|a.language).println();");
		//module.parse("Foo.all.size().println();");
		module.getContext().getModelRepository().addModel(model);
		module.execute();*/
	}
	
	public Muddle importGraph(File file) throws Exception {
		
		graph = MuddleFactory.eINSTANCE.createMuddle();
		nodeMap = new HashMap<String, MuddleElement>();
		nodeElementMap = new HashMap<MuddleElement, Element>();
		orphanEdges = new ArrayList<OrphanLink>();
		referenceNodes = new ArrayList<MuddleElement>();
		
		SAXBuilder builder = new SAXBuilder();
		Document doc = builder.build(file);
		Element root = doc.getDocument().getRootElement();
		namespace = root.getNamespace();
		
		graphElement = root.getChild("graph", namespace);
		configuration = new GraphmlConfiguration(root);
		
		populateGraph();
		adjustSlotPrototypeMultiplicitiesAndSlotValueTypes();
		
		return graph;
	}
	
	protected void populateGraph() {
		
		// Process node elements
		for (Element nodeElement : getNodeElements()) {
			
			String nodeTypeName = getElementData(nodeElement, configuration.getNodeTypeKey());
			if (nodeTypeName == null) continue;
			boolean isReference = false;
			
			if (nodeTypeName.startsWith("@")) {
				nodeTypeName = nodeTypeName.substring(1);
				isReference = true;
			}
			
			// Create nodes only for typed node elements
			MuddleElement node = MuddleFactory.eINSTANCE.createMuddleElement();
			node.setId(nodeElement.getAttributeValue("id"));
			nodeMap.put(node.getId(), node);
			nodeElementMap.put(node, nodeElement);
			
			if (isReference) {
				referenceNodes.add(node);
			}
			graph.getElements().add(node);
			node.setType(nodeTypeForName(nodeTypeName));
			
			// If the node is a reference there's nothing
			// else to do here
			if (isReference) continue;
			
			createPrimaryPrototypeSlot(node, nodeElement, configuration.getNodePrimarySlotPrototypeNameKey());
		}
		
		for (MuddleElement node : graph.getElements()) {
			Element nodeElement = nodeElementMap.get(node);
			populateSlots(node, nodeElement);
		}
		
		// Replace references with actual elements in the node map
		for (MuddleElement referenceNode : referenceNodes) {
			MuddleElement target = findReferenceTarget(referenceNode);
			if (target != null) {
				nodeMap.put(referenceNode.getId(), target);
			}
		}
		
		// We don't need references any more
		graph.getElements().removeAll(referenceNodes);
		for (MuddleElement referenceNode : referenceNodes) {
			annihilate(referenceNode);
		}
		
		// Process untyped edge elements
		for (Element edgeElement : getEdgeElements()) {
			
			String edgeTypeName = getElementData(edgeElement, configuration.getEdgeTypeKey());
			if (edgeTypeName != null) continue;
				
			MuddleElement source = nodeMap.get(edgeElement.getAttributeValue("source"));
			MuddleElement target = nodeMap.get(edgeElement.getAttributeValue("target"));
			
			String label = getFirstLabel(edgeElement);
			if (label == null) {
				orphanEdges.add(new OrphanLink(source, target));
				continue;
			}
			
			Feature prototype = new LinkFeatureLabelParser(label).getFeature();
			Slot slot = addSlot(source, prototype);
			slot.setFeature(addSlotPrototype(source.getType(), prototype));
			slot.getValues().add(target);
		}
		
		// Try to put orphan edge targets to suitable slots
		for (OrphanLink orphanEdge : orphanEdges) {
			Slot slot = findSuitableSlot(orphanEdge.getSource(), orphanEdge.getTarget());
			if (slot != null) {
				slot.getValues().add(orphanEdge.getTarget());
			}
		}
		
		List<MuddleElement> typedEdgeNodes = new ArrayList<MuddleElement>();
		
		// Create nodes for typed edges
		for (Element edgeElement : getEdgeElements()) {
			String edgeTypeName = getElementData(edgeElement, configuration.getEdgeTypeKey());
			if (edgeTypeName == null) continue;
			
			MuddleElement node = MuddleFactory.eINSTANCE.createMuddleElement();
			node.setId(edgeElement.getAttributeValue("id"));
			nodeElementMap.put(node, edgeElement);
			graph.getElements().add(node);
			typedEdgeNodes.add(node);
			LinkElementType edgeType = edgeTypeForName(edgeTypeName);
			node.setType(edgeType);
			createPrimaryPrototypeSlot(node, edgeElement, configuration.getEdgePrimarySlotPrototypeNameKey());
			
			Feature sourcePrototype = createEdgeTypeSlotPrototype(node, configuration.getEdgeSourceKey());
			
			if (sourcePrototype != null && edgeType.getSourceFeature() == null) {
				edgeType.setSourceFeature(sourcePrototype);
				edgeType.getFeatures().add(sourcePrototype);
			}
			
			Feature targetPrototype = createEdgeTypeSlotPrototype(node, configuration.getEdgeTargetKey());
			if (targetPrototype != null && edgeType.getTargetFeature() == null) {
				edgeType.setTargetFeature(targetPrototype);
				edgeType.getFeatures().add(targetPrototype);
			}
			
			Feature roleInSourcePrototype = createEdgeTypeSlotPrototype(node, configuration.getEdgeRoleInSourceKey());
			if (roleInSourcePrototype != null && edgeType.getRoleInSourceFeature() == null) {
				edgeType.setRoleInSourceFeature(roleInSourcePrototype);
			}
			
			Feature roleInTargetPrototype = createEdgeTypeSlotPrototype(node, configuration.getEdgeRoleInTargetKey());
			if (roleInTargetPrototype != null && edgeType.getRoleInTargetFeature() == null) {
				edgeType.setRoleInTargetFeature(roleInTargetPrototype);
			}
		}
		
		// Populate nodes for typed edges
		for (MuddleElement edgeNode : typedEdgeNodes) {
			Element edgeElement = nodeElementMap.get(edgeNode);
			populateSlots(edgeNode, edgeElement);
			
			MuddleElement source = nodeMap.get(edgeElement.getAttributeValue("source"));
			MuddleElement target = nodeMap.get(edgeElement.getAttributeValue("target"));
		
			if (source != null) {
				addEdgeNodeToNode(edgeNode, source, ((LinkElementType) edgeNode.getType()).getRoleInSourceFeature());
				addNodeToEdgeNode(edgeNode, source, ((LinkElementType) edgeNode.getType()).getSourceFeature());
				
			}
			if (target != null) {
				addEdgeNodeToNode(edgeNode, target, ((LinkElementType) edgeNode.getType()).getRoleInTargetFeature());
				addNodeToEdgeNode(edgeNode, target, ((LinkElementType) edgeNode.getType()).getTargetFeature());
			}
			
		}
		
		// Populate contents
		for (MuddleElement muddleElement : graph.getElements()) {
			
			if (muddleElement.getType() == null) continue;
			Element element = nodeElementMap.get(muddleElement);
			String contentsFeatureName = getElementData(element, configuration.getNodeContentsKey());
			if (contentsFeatureName == null) continue;
			
			Feature contentsFeature = getOrCreateFeature(muddleElement.getType(), contentsFeatureName);
			contentsFeature.setMany(true);
			
			Slot contentsSlot = findSlot(muddleElement, contentsFeature);
			if (contentsSlot == null) {
				contentsSlot = MuddleFactory.eINSTANCE.createSlot();
				contentsSlot.setFeature(contentsFeature);
				contentsSlot.setOwningElement(muddleElement);
			}
			
			for (Object child : element.getChildren()) {
				if (child instanceof Element) {
					Element childElement = (Element) child;
					
					for (Object grandChild : childElement.getChildren()) {
						if (grandChild instanceof Element) {
							Element grandChildElement = (Element) grandChild;
							MuddleElement contentsElement = nodeMap.get(grandChildElement.getAttributeValue("id"));
							
							if (contentsElement != null) {
								contentsSlot.getValues().add(contentsElement);
							}
						}
					}
				}
			}
			
		}
	}
	
	protected Feature getOrCreateFeature(MuddleElementType type, String featureName) {
		Feature feature = null;
		for (Feature candidateFeature : type.getFeatures()) {
			if (candidateFeature.getName().equals(featureName)) {
				feature = candidateFeature;
			}
		}
		
		if (feature == null) {
			feature = MuddleFactory.eINSTANCE.createFeature();
			feature.setName(featureName);
			feature.setOwningType(type);
		}
		
		return feature;
	}
	
	protected Feature createEdgeTypeSlotPrototype(MuddleElement edgeNode, String key) {
		String slotPrototypeLabel = getNodeData(edgeNode, key);
		if (slotPrototypeLabel == null) return null;
		Feature prototype = new LinkFeatureLabelParser(slotPrototypeLabel).getFeature(); 
		return prototype;
	}
	
	protected void addNodeToEdgeNode(MuddleElement edgeNode, MuddleElement node, Feature prototype) {
		if (prototype == null) return;
		Slot slot = addSlot(edgeNode, prototype);
		slot.getValues().add(node);
	}
	
	protected void addEdgeNodeToNode(MuddleElement edgeNode, MuddleElement node, Feature prototype) {
		if (prototype == null) return;
		Slot slot = addSlot(node, addSlotPrototype(node.getType(), clone(prototype)));
		slot.getValues().add(edgeNode);
	}
	
	protected String getNodeData(MuddleElement node, String key) {
		return getElementData(nodeElementMap.get(node), key);
	}
	
	protected void populateSlots(MuddleElement node, Element element) {
		for (String label : getLabels(element)) {
			
			// Named slot value
			if (isSlotValueLabel(label)) {
				ValuedSlotFeatureLabelParser parser = new ValuedSlotFeatureLabelParser(label);
				Slot slot = addSlot(node, parser.getFeature());
				slot.setFeature(addSlotPrototype(node.getType(), parser.getFeature()));
				slot.getValues().add(parser.getValue());
			}
			// Default slot value
			else { 
				Slot primarySlot = getPrimarySlot(node);
				if (primarySlot != null) primarySlot.getValues().add(label);
			}
			
		}
	}
	
	protected void createPrimaryPrototypeSlot(MuddleElement node, Element element, String defaultSlotPrototypeNameKey) {
		// Create default prototype slot if exists
		String defaultSlotPrototypeName = defaultSlotPrototypeNameKey;
		if (defaultSlotPrototypeName == null) return;
		String defaultSlotPrototypeLabel = getElementData(element, defaultSlotPrototypeName);
		if (defaultSlotPrototypeLabel == null) return;
		Feature prototype = new ValuedSlotFeatureLabelParser(defaultSlotPrototypeLabel + " = 0").getFeature();
		prototype.setPrimary(true);
		addSlotPrototype(node.getType(), prototype);
	}
	
	protected String getElementData(Element e, String key) {
		for (Element descriptionElement : getDescendants(e, "data")) {
			if (descriptionElement.getAttributeValue("key","").equals(key)) {
				String data = descriptionElement.getText().trim();
				if (data.length() == 0) return null;
				else return data;
			}
		}
		return null;
	}
	
	protected void annihilate(MuddleElement node) {
		node.setType(null);
		for (Slot slot : node.getSlots()) {
			slot.setFeature(null);
			slot.getValues().clear();
		}
	}
	
	protected MuddleElement findReferenceTarget(MuddleElement referenceNode) {
		
		for (MuddleElement node : referenceNode.getType().getInstances()) {
			if (referenceNodes.contains(node)) continue;
			if (matches(node, referenceNode)) {
				return node;
			}
		}
		
		return null;
		
	}
	
	protected boolean matches(MuddleElement node, MuddleElement referenceNode) {
		for (Slot referenceSlot : referenceNode.getSlots()) {
			Slot slot = findSlot(node, referenceSlot.getFeature());
			if (slot == null) return false;
			for (Object referenceValue : referenceSlot.getValues()) {
				if (!slot.getValues().contains(referenceValue)) return false;
			}
		}
		return true;
	}
	
	protected Slot findSlot(MuddleElement node, Feature slotPrototype) {
		for (Slot slot : node.getSlots()) {
			if (slot.getFeature().equals(slotPrototype)) return slot;
		}
		return null;
	}
	
	/**
	 * If single-valued slots are found to have
	 * multiple values, adjust the multiplicity accordingly.
	 * Also, adjust the type of slot values
	 */
	protected void adjustSlotPrototypeMultiplicitiesAndSlotValueTypes() {
		for (MuddleElement node : graph.getElements()) {
			for (Slot slot : node.getSlots()) {
				if (!slot.getFeature().isMany() && slot.getValues().size() > 1) {
					slot.getFeature().setMany(true);
				}
				if (slot.getValues().size() > 0) {
					Type slotType = slot.getFeature().getType();
					if (slotType instanceof IntegerType || slotType instanceof BooleanType || slotType instanceof RealType) {
						List<Object> castedValues = new ArrayList<Object>();
						for (Object value : slot.getValues()) {
							castedValues.add(cast(value, slotType));
						}
						slot.getValues().clear();
						slot.getValues().addAll(castedValues);
					}
				}
			}
		}
	}
	
	protected Object cast(Object object, Type type) {
		if (type instanceof IntegerType) {
			try {
				return Integer.parseInt(object + "");
			}
			catch (Exception ex) { return 0; }
		}
		else if (type instanceof BooleanType) {
			try {
				return Boolean.parseBoolean(object + "");
			}
			catch (Exception ex) { return false; }			
		}
		else if (type instanceof RealType) {
			try {
				return Float.parseFloat(object + "");
			}
			catch (Exception ex) { return 0.0f; }
		}
		else return object;
	}
	
	protected Slot findSuitableSlot(MuddleElement source, MuddleElement target) {
		if (source == null || source.getType() == null) return null;
		Feature slotPrototype = findSuitableSlotPrototype(source.getType(), target);
		if (slotPrototype == null) return null;
		for (Slot slot : source.getSlots()) {
			if (slot.getFeature() == slotPrototype) {
				return slot;
			}
		}
		
		Slot slot = MuddleFactory.eINSTANCE.createSlot();
		slot.setFeature(slotPrototype);
		source.getSlots().add(slot);
		return slot;
		
	}
	
	protected Feature findSuitableSlotPrototype(MuddleElementType type, MuddleElement value) {
		for (Feature slotPrototype : type.getFeatures()) {
			for (Slot slot : slotPrototype.getSlots()) {
				for (Object existingValue : slot.getValues()) {
					if (existingValue instanceof MuddleElement && ((MuddleElement) existingValue).getType().equals(value.getType())) {
						return slotPrototype;
					}
				}
			}
		}
		return null;
	}
	
	protected boolean isReferenceLabel(String label) {
		return label.startsWith("@");
	}
	
	protected boolean isSlotValueLabel(String label) {
		return label.indexOf('=') > -1;
	}
	
	protected Slot addSlot(MuddleElement node, Feature prototype) {
		for (Slot existingSlot : node.getSlots()) {
			if (existingSlot.getFeature().getName().equals(prototype.getName())) {
				return existingSlot;
			}
		}
		Slot slot = MuddleFactory.eINSTANCE.createSlot();
		slot.setFeature(prototype);
		node.getSlots().add(slot);
		return slot;
	}
	
	protected Feature addSlotPrototype(MuddleElementType nodeType, Feature prototype) {
		for (Feature existingPrototype : nodeType.getFeatures()) {
			if (existingPrototype.getName().equals(prototype.getName())) {
				if (existingPrototype.getType() == null) {
					existingPrototype.setType(prototype.getType());
				}
				if (!existingPrototype.isMany()) {
					existingPrototype.setMany(prototype.isMany());
				}
				if (!existingPrototype.isPrimary()) {
					existingPrototype.setPrimary(prototype.isPrimary());
				}
				return existingPrototype;
			}
		}
		nodeType.getFeatures().add(prototype);
		return prototype;
	}
	
	protected Feature clone(Feature prototype) {
		Feature clone = MuddleFactory.eINSTANCE.createFeature();
		clone.setName(prototype.getName());
		clone.setPrimary(prototype.isPrimary());
		clone.setMany(prototype.isMany());
		return clone;
	}
	
	protected Slot getPrimarySlot(MuddleElement node) {
		Feature primaryFeature = null;
		for (Feature prototype : node.getType().getFeatures()) {
			if (prototype.isPrimary()) primaryFeature = prototype;
			break;
		}
		
		if (primaryFeature == null) return null;
		
		Slot primarySlot = null;
		for (Slot slot : node.getSlots()) {
			if (slot.getFeature().equals(primaryFeature)) {
				primarySlot = slot;
				break;
			}
		}
		
		if (primarySlot == null) {
			Slot slot = MuddleFactory.eINSTANCE.createSlot();
			slot.setFeature(primaryFeature);
			slot.setOwningElement(node);
			return slot;
		}
		else {
			return primarySlot;
		}
		
	}
	
	protected String getFirstLabel(Element e) {
		List<String> labels = getLabels(e);
		if (labels.size() > 0) return labels.get(0);
		else return null;
	}
	
	protected List<String> getLabels(Element e) {
		String labelTag = "NodeLabel";
		String propertiesKey = configuration.getNodePropertiesKey();
		
		if (e.getName().equals("edge")) {
			labelTag = "EdgeLabel";
			propertiesKey = configuration.getEdgePropertiesKey();
		}
		List<String> labels = new ArrayList<String>();
		for (Element labelElement : getDescendants(e, labelTag)) {
			if (getFirstAncestor(labelElement, e.getName()) == e) {
				labels.addAll(getLabels(labelElement.getText()));
			}
		}
		
		for (Element descriptionElement : getDescendants(e, "data")) {
			if (descriptionElement.getParentElement() != e) continue;
			if (descriptionElement.getAttributeValue("key","").equals(propertiesKey)) {
				labels.addAll(getLabels(descriptionElement.getText()));
			}
		}
		return labels;
	}
	
	protected Element getFirstAncestor(Element element, String name) {
		Element parentElement = element.getParentElement();
		while (parentElement != null) {
			if (parentElement.getName().equals(name)) {
				return parentElement;
			}
			else {
				parentElement = parentElement.getParentElement();
			}
		}
		return null;
	}
	
	protected List<String> getLabels(String s) {
		List<String> labels = new ArrayList<String>();
		s = s.trim();
		for (String label : s.split("\\n")) {
			if (label.trim().length() > 0) {
				labels.add(label.trim());
			}
		}
		return labels;
	}
	
	protected List<Element> getDescendants(Element node, final String name) {
		List<Element> descendants = new ArrayList<Element>();
		Iterator<?> iterator = node.getDescendants(new Filter() {
			@Override
			public boolean matches(Object o) {
				return o instanceof Element && ((Element) o).getName().equals(name);
			}
		});
		while (iterator.hasNext()) {
			descendants.add((Element) iterator.next());
		}
		return descendants;
	}
	
	protected List<Element> getNodeElements() {
		List<Element> elements = new ArrayList<Element>();
		
		for (Object o : getDescendants(graphElement, "node") /*graphElement.getChildren("node", namespace)*/) {
			elements.add((Element) o);
		}
		return elements;
	}
	
	protected List<Element> getEdgeElements() {
		List<Element> elements = new ArrayList<Element>();
		for (Object o : getDescendants(graphElement, "edge") /*graphElement.getChildren("edge", namespace)*/) {
			elements.add((Element) o);
		}
		return elements;
	}

	protected LinkElementType edgeTypeForName(String name) {
		return (LinkElementType) typeForName(name, true);
	}
	
	protected MuddleElementType nodeTypeForName(String name) {
		return typeForName(name, false);
	}
	
	protected MuddleElementType typeForName(String name, boolean edgeType) {
		
		int gt = name.indexOf(">");
		
		if (gt > -1) {
			
			String typeName = name.substring(0, gt).trim();
			String superTypeName = name.substring(gt+1, name.length()).trim();

			MuddleElementType type = typeForName(typeName, edgeType);
			MuddleElementType superType = typeForName(superTypeName, edgeType);
			if (!type.getSuperTypes().contains(superType)) {
				type.getSuperTypes().add(superType);
			}
			return type;
		}
		
		for (Type type : graph.getTypes()) {
			if (type instanceof MuddleElementType && type.getName().equals(name)) {
				return (MuddleElementType) type;
			}
		}
		
		MuddleElementType nodeType = null;
		if (edgeType) {
			nodeType = MuddleFactory.eINSTANCE.createLinkElementType();
		}
		else {
			nodeType = MuddleFactory.eINSTANCE.createMuddleElementType();
		}
		nodeType.setName(name);
		graph.getTypes().add(nodeType);
		return nodeType;
	}
	
	public GraphmlConfiguration getConfiguration() {
		return configuration;
	}
}
