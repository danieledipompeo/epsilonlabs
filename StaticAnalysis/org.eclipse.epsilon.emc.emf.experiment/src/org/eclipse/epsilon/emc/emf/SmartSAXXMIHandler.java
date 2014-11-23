package org.eclipse.epsilon.emc.emf;

import java.util.ArrayList;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xmi.XMLHelper;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.SAXXMIHandler;
import org.eclipse.epsilon.eol.analysis.optimisation.loading.context.ModelContainer;
import org.eclipse.epsilon.eol.analysis.optimisation.loading.context.ModelElementContainer;

public class SmartSAXXMIHandler extends SAXXMIHandler{
	
	protected ArrayList<ModelContainer> modelContainers = new ArrayList<ModelContainer>(); // <-------------------- point of change

	protected ArrayList<String> elementStack = new ArrayList<String>();
	
	public SmartSAXXMIHandler(XMLResource xmiResource, XMLHelper helper,
			Map<?, ?> options) {
		super(xmiResource, helper, options);
	}
	
	public void addModelContainer(ModelContainer modelContainer)
	{
		modelContainers.add(modelContainer);
	}
	
	public void setModelContainers(ArrayList<ModelContainer> modelContainers) {
		this.modelContainers = modelContainers;
	}

	
	@Override
	public void startElement(String uri, String localName, String name) {
		
		if (documentRoot != null) {
			if (isNeeded(name)) {
				elementStack.add(name);
				EObject eObject = objects.peekEObject();
				if (eObject == documentRoot && (extendedMetaData == null || extendedMetaData.isDocumentRoot(eObject.eClass()))) {
					types.pop();
					objects.pop();
					mixedTargets.pop();
					documentRoot = null;
				}
			}
			else if (elementStack.size() > 1) {
				EObject eObject = objects.peekEObject();
				if (eObject == documentRoot && (extendedMetaData == null || extendedMetaData.isDocumentRoot(eObject.eClass()))) {
					types.pop();
					objects.pop();
					mixedTargets.pop();
					documentRoot = null;
				}
			}
		}
		else {
			elementStack.add(name);
			super.startElement(uri, localName, name);
			return;
		}
		if (isNeeded(name) || elementStack.size() > 1) {
			super.startElement(uri, localName, name);
		}
		
		
	}
	
	@Override
	public void endElement(String uri, String localName, String name) {
		if (elementStack.size() > 0 || isNeeded(name)) {
			super.endElement(uri, localName, name);
			if (elementStack.size() > 0 && elementStack.get(elementStack.size()-1).equals(name)) {
				elementStack.remove(elementStack.size()-1);
			}
		}
		
	}
	
	public boolean isNeeded(String name)
	{
		String localName = name;
		int index = name.indexOf(':', 0);
		if (index != -1) {
			localName = name.substring(index+1);
		}

		for(ModelContainer mc: modelContainers)
		{
			for(ModelElementContainer mec: mc.getModelElementsAllOfKind())
			{
				String elementName = mec.getElementName();
				if (localName.equals(elementName)) {
					return true;
				}
			}
			
			for(ModelElementContainer mec: mc.getModelElementsAllOfType())
			{
				String elementName = mec.getElementName();
				if (localName.equals(elementName)) {
					return true;
				}
			}
		}
		return false;
	}



}
