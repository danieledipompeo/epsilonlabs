package org.eclipse.epsilon.emc.emf;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.epsilon.eol.EolModule;
import org.eclipse.epsilon.eol.analysis.optimisation.loading.context.LoadingOptimisationAnalysisContext;
import org.eclipse.epsilon.eol.analysis.optimisation.loading.context.EffectiveMetamodel;
import org.eclipse.epsilon.eol.analysis.optimisation.loading.context.EffectiveType;
import org.eclipse.epsilon.eol.analysis.optimisation.loading.impl.LoadingOptimisationAnalyser;
import org.eclipse.epsilon.eol.ast2eol.Ast2EolContext;
import org.eclipse.epsilon.eol.exceptions.models.EolModelElementTypeNotFoundException;
import org.eclipse.epsilon.eol.exceptions.models.EolModelLoadingException;
import org.eclipse.epsilon.eol.metamodel.EolElement;
import org.eclipse.epsilon.eol.visitor.resolution.type.tier1.impl.TypeResolver;
import org.eclipse.epsilon.eol.visitor.resolution.variable.impl.VariableResolver;

public class EmfSmartModel extends EmfModel{


	protected ArrayList<EffectiveMetamodel> modelContainers = new ArrayList<EffectiveMetamodel>();	//protected ModelContainer modelContainer;
	protected ArrayList<EClass> visitedClasses = new ArrayList<EClass>();
	protected HashMap<String, HashMap<String, ArrayList<String>>> objectsAndRefNamesToVisit = new HashMap<String, HashMap<String,ArrayList<String>>>();
	protected HashMap<String, HashMap<String, ArrayList<String>>> actualObjectsToLoad = new HashMap<String, HashMap<String,ArrayList<String>>>();

	protected boolean loadAllAttributes = true;
	protected boolean smartLoading = true;
	protected boolean partialLoading = false;
	
	protected SmartEmfModelResourceFactory f;
	
	public void clearCollections()
	{		
		visitedClasses.clear();
		visitedClasses = null;
		objectsAndRefNamesToVisit.clear();
		objectsAndRefNamesToVisit = null;
		actualObjectsToLoad.clear();
		actualObjectsToLoad = null;
		f.clearCollections();
	}
	
	public void setLoadAllAttributes(boolean loadAllAttributes) {
		this.loadAllAttributes = loadAllAttributes;
	}
	
	public void setSmartLoading(boolean smartLoading) {
		this.smartLoading = smartLoading;
	}
	
	public void setPartialLoading(boolean partialLoading) {
		this.partialLoading = partialLoading;
	}

	public void addModelContainer(EffectiveMetamodel modelContainer)
	{
		modelContainers.add(modelContainer);
	}
	
	public void setModelContainers(ArrayList<EffectiveMetamodel> modelContainers) {
		this.modelContainers = modelContainers;
	}
	
	public void preProcess() throws EolModelLoadingException
	{
		ResourceSet resourceSet = createResourceSet();
		
        // Check if global package registry contains the EcorePackage
		if (EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI) == null) {
			EPackage.Registry.INSTANCE.put(EcorePackage.eNS_URI, EcorePackage.eINSTANCE);
		}
		
		determinePackagesFrom(resourceSet);
		populateEmptyObjects();

	}
	
	
	public void loadModelFromUri() throws EolModelLoadingException {
		ResourceSet resourceSet = createResourceSet();
		
        // Check if global package registry contains the EcorePackage
		if (EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI) == null) {
			EPackage.Registry.INSTANCE.put(EcorePackage.eNS_URI, EcorePackage.eINSTANCE);
		}
		
		determinePackagesFrom(resourceSet);
//		long init = System.nanoTime();
//		populateEmptyObjects();
//		System.out.println((System.nanoTime()-init)/1000000);
		
		for (EPackage ep : packages) {
			String nsUri = ep.getNsURI();
			if (nsUri == null || nsUri.trim().length() == 0) {
				nsUri = ep.getName();
			}
			resourceSet.getPackageRegistry().put(nsUri, ep);
		}
		resourceSet.getPackageRegistry().put(EcorePackage.eNS_URI, EcorePackage.eINSTANCE);
				
		Resource model = resourceSet.createResource(modelUri);
		if (this.readOnLoad) {
			try {
				model.load(null);
				if (expand) {
					EcoreUtil.resolveAll(model);
				}
			} catch (IOException e) {
				// Unload the model, so it will not be wrongly cached as "loaded",
				// causing the intermittent errors produced in bug #386255
				model.unload();
				throw new EolModelLoadingException(e, this);
			}
		}
		modelImpl = model;

		if (modelContainers.size() != 0) {
			try {
				if (smartLoading) {
					populateCaches_v2();	
				}
			} catch (EolModelElementTypeNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		
		if (partialLoading) {
			clearCollections();
		}
		
		modelContainers.clear();
		modelContainers = null;
	}
	
	public void populateEmptyObjects()
	{
		for(EPackage ePackage: packages)
		{
			for(EClassifier eClassifier: ePackage.getEClassifiers())
			{
				if (eClassifier instanceof EClass) {
					if (actualObjectToLoad(ePackage, (EClass) eClassifier)) {
						addActualObjectToLoad((EClass) eClassifier);
					}
					EClass eClass = (EClass) eClassifier;
					visitedClasses.clear();
					visitEClass(eClass);
				}
			}
		}
		
		for(EPackage ePackage: packages)
		{
			for(EClassifier eClassifier: ePackage.getEClassifiers())
			{
				if (eClassifier instanceof EClass) {
					EClass leClass = (EClass) eClassifier;
					if (actualObjectToLoad(ePackage, (EClass) eClassifier)) {
						
						for(EReference eReference: leClass.getEAllReferences())
						{
							if(actualObjectsToLoad.get(ePackage.getName()).get(eClassifier.getName()).contains(eReference.getName()))
							{
								EClass eType = (EClass) eReference.getEType();
								addActualObjectToLoad(eType);
							}
						}
					}
				}
			}
		}
		
		for(EffectiveMetamodel mc: modelContainers)
		{
			for(EffectiveType mec: mc.getAllOfKind())
			{
				ArrayList<String> features = actualObjectsToLoad.get(mc.getName()).get(mec.getName());
				features.addAll(mec.getAttributes());
				features.addAll(mec.getReferences());
			}
			for(EffectiveType mec: mc.getAllOfType())
			{
				ArrayList<String> features = actualObjectsToLoad.get(mc.getName()).get(mec.getName());
				features.addAll(mec.getAttributes());
				features.addAll(mec.getReferences());
			}
		}
	}
	
	
	
	public void insertHollowOjbects(EPackage ePackage, EClass eClass)
	{
		boolean inserted = false;
		for(EffectiveMetamodel mc: modelContainers)
		{
			if (mc.getName().equalsIgnoreCase(ePackage.getName())) {
				for(EffectiveType mec: mc.getAllOfKind())
				{
					if (mec.getName().equals(eClass.getName())) {
						inserted = true;
						return;
					}
					EClass kind = (EClass) ePackage.getEClassifier(mec.getName());
					for(EClass superClass: eClass.getEAllSuperTypes())
					{
						if (kind.getName().equals(superClass.getName())) {
							inserted = true;
							return;
						}
					}

				}
				for(EffectiveType mec: mc.getAllOfType())
				{
					if (mec.getName().equals(eClass.getName())) {
						inserted = true;
						return;
					}
				}
				if (!inserted) {
					inserted = true;
					mc.addEmptyElement(eClass.getName());
					break;
				}
			}
		}
		if (!inserted) {
			EffectiveMetamodel newContainer = new EffectiveMetamodel(ePackage.getName());
			newContainer.addEmptyElement(eClass.getName());
			modelContainers.add(newContainer);
			
		}
	}
	
	
	public void visitEClass(EClass eClass)
	{
		//add this class to the visited
		visitedClasses.add(EcoreUtil.copy(eClass));
		
		//if this one is a live class, should addRef()
		if (liveClass(eClass.getEPackage(), eClass.getName())) {
			addRef(eClass, null);
			insertHollowOjbects(eClass.getEPackage(), eClass);
		}
		
		for(EReference eReference: eClass.getEAllReferences())
		{
			if (!visitedEClass((EClass) eReference.getEType())) {
				visitEClass((EClass) eReference.getEType());
			}
			
			if (liveReference(eReference)) {
				addRef(eClass, eReference);
				insertHollowOjbects(eClass.getEPackage(), eClass);
			}
		}
		
		for(EClassifier every: eClass.getEPackage().getEClassifiers())
		{
			if (every instanceof EClass) {
				EClass theClass = (EClass) every;
				if (theClass.getEAllSuperTypes().contains(eClass)) {
					for(EReference eReference: theClass.getEAllReferences())
					{
						if (!visitedEClass((EClass) eReference.getEType())) {
							visitEClass((EClass) eReference.getEType());
						}
						
						if (liveReference(eReference)) {
							addRef(theClass, eReference);
							insertHollowOjbects(theClass.getEPackage(), theClass);
						}
					}
				}
			}
		}
	}
	
	public ArrayList<String> getFeaturesForClassToLoad(EClass eClass)
	{
		//get the package
		EPackage ePackage = eClass.getEPackage();
		//prepare the result
		ArrayList<String> result = new ArrayList<String>();
		
		//for all model containers
		for(EffectiveMetamodel mc: modelContainers)
		{
			//if the container is the container needed
			if (mc.getName().equals(ePackage.getName())) {
				//for elements all of kind
				loop1:
				for(EffectiveType mec: mc.getAllOfKind())
				{
					//if class name equals, add all attributes and references
					if (eClass.getName().equals(mec.getName())) {
						result.addAll(mec.getAttributes());
						result.addAll(mec.getReferences());
						break loop1;
					}
					
					//if eclass is a sub class of the kind, add all attributes and references
					EClass kind = (EClass) ePackage.getEClassifier(mec.getName());
					if (eClass.getEAllSuperTypes().contains(kind)) {
						result.addAll(mec.getAttributes());
						result.addAll(mec.getReferences());
						break loop1;
					}
				}
				
				//for elements all of type
				loop2:
				for(EffectiveType mec: mc.getAllOfType())
				{
					//if class name equals, add all references and attributes
					if (eClass.getName().equals(mec.getName())) {
						result.addAll(mec.getAttributes());
						result.addAll(mec.getReferences());
						break loop2;
					}
				}
			}
		}
		return result;
	}
	
	public void addActualObjectToLoad(EClass eClass)
	{
		//get the epackage name
		String epackage = eClass.getEPackage().getName();
		//get the submap with the epackage name
		HashMap<String, ArrayList<String>> subMap = actualObjectsToLoad.get(epackage);
		//if sub map is null
		if (subMap == null) {
			//create new sub map
			subMap = new HashMap<String, ArrayList<String>>();
			//create new refs for the map
			ArrayList<String> refs = getFeaturesForClassToLoad(eClass);
			
			//add the ref to the sub map
			subMap.put(eClass.getName(), refs);
			//add the sub map to objectsAndRefNamesToVisit
			actualObjectsToLoad.put(epackage, subMap);
		}
		else
		{
			//if sub map is not null, get the refs by class name
			ArrayList<String> refs = subMap.get(eClass.getName());

			//if refs is null, create new refs and add the ref and then add to sub map
			if (refs == null) {
				refs = getFeaturesForClassToLoad(eClass);
				subMap.put(eClass.getName(), refs);
			}
		}
	}
	
	public void addActualObjectToLoad(EClass eClass, EReference eReference)
	{
		//get the epackage name
		String epackage = eClass.getEPackage().getName();
		//get the submap with the epackage name
		HashMap<String, ArrayList<String>> subMap = actualObjectsToLoad.get(epackage);
		//if sub map is null
		if (subMap == null) {
			//create new sub map
			subMap = new HashMap<String, ArrayList<String>>();
			//create new refs for the map
			ArrayList<String> refs = new ArrayList<String>();
			//if eReference is not null
			if (eReference != null) {
				//add the eReference to the ref
				refs.add(eReference.getName());
			}
			//add the ref to the sub map
			subMap.put(eClass.getName(), refs);
			//add the sub map to objectsAndRefNamesToVisit
			actualObjectsToLoad.put(epackage, subMap);
		}
		else {
			//if sub map is not null, get the refs by class name
			ArrayList<String> refs = subMap.get(eClass.getName());

			//if refs is null, create new refs and add the ref and then add to sub map
			if (refs == null) {
				refs = new ArrayList<String>();
				if(eReference != null)
				{
					refs.add(eReference.getName());
				}
				subMap.put(eClass.getName(), refs);
			}
			//if ref is not null, add the ref
			else {
				if (eReference != null) {
					if (!refs.contains(eReference.getName())) {
						refs.add(eReference.getName());	
					}
				}
			}
		}
	}

	
	public void addRef(EClass eClass, EReference eReference)
	{
		//get the epackage name
		String epackage = eClass.getEPackage().getName();
		//get the submap with the epackage name
		HashMap<String, ArrayList<String>> subMap = objectsAndRefNamesToVisit.get(epackage);
		//if sub map is null
		if (subMap == null) {
			//create new sub map
			subMap = new HashMap<String, ArrayList<String>>();
			//create new refs for the map
			ArrayList<String> refs = new ArrayList<String>();
			//if eReference is not null
			if (eReference != null) {
				//add the eReference to the ref
				refs.add(eReference.getName());
			}
			//add the ref to the sub map
			subMap.put(eClass.getName(), refs);
			//add the sub map to objectsAndRefNamesToVisit
			objectsAndRefNamesToVisit.put(epackage, subMap);
		}
		else {
			//if sub map is not null, get the refs by class name
			ArrayList<String> refs = subMap.get(eClass.getName());

			//if refs is null, create new refs and add the ref and then add to sub map
			if (refs == null) {
				refs = new ArrayList<String>();
				if(eReference != null)
				{
					refs.add(eReference.getName());
				}
				subMap.put(eClass.getName(), refs);
			}
			//if ref is not null, add the ref
			else {
				if (eReference != null) {
					if (!refs.contains(eReference.getName())) {
						refs.add(eReference.getName());	
					}
				}
			}
		}
	}
	
	public boolean visitedEClass(EClass eClass)
	{
		for(EClass clazz: visitedClasses)
		{
			if (clazz.getName().equals(eClass.getName())) {
				return true;
			}
		}
		return false;
	}

	
	public boolean liveReference(EReference eReference)
	{
		if(eReference.isContainment())
		{
			EClassifier eClassifier = eReference.getEType();
			EClass etype = (EClass) eClassifier;
			if (liveClass(etype.getEPackage(), etype.getName())) {
				return true;
			}
			
			return false;
		}
		return false;
		
	}
	
	public boolean actualObjectToLoad(EPackage ePackage, EClass eClass)
	{
		for(EffectiveMetamodel mc: modelContainers)
		{
			if (mc.getName().equalsIgnoreCase(ePackage.getName())) {
				for(EffectiveType mec: mc.getAllOfKind())
				{
					String elementName = mec.getName();
					if (elementName.equals(eClass.getName())) {
						return true;
					}
					
					EClass kind = (EClass) ePackage.getEClassifier(elementName);
					if(eClass.getESuperTypes().contains(kind))
					{
						return true;
					}
				}
				
				for(EffectiveType mec: mc.getAllOfType())
				{
					String elementName = mec.getName();
					if (elementName.equals(eClass.getName())) {
						return true;
					}
				}
			}
		}
		return false;

	}
	
	
	/*
	 * this method determines if a class is live for a eType of an eReference
	 */
	
	public boolean liveClass(EPackage ePackage, String className)
	{
		for(EffectiveMetamodel mc: modelContainers)
		{
			//get the package first
			if (mc.getName().equalsIgnoreCase(ePackage.getName())) {
				
				//for all of kinds
				for(EffectiveType mec: mc.getAllOfKind())
				{
					//the element n ame
					String elementName = mec.getName();
					//if name equals return true
					if (className.equals(elementName)) {
						return true;
					}
					
					//get the eclass for the mec
					EClass kind = (EClass) ePackage.getEClassifier(elementName);
					//get the eclass for the current class under question
					EClass actual = (EClass) ePackage.getEClassifier(className);
					//if the current class under question is a sub class of the mec, should return true
					if(actual.getEAllSuperTypes().contains(kind))
					{
						return true;
					}
					//if the current class under question is a super class of the mec, should also return true
					if (kind.getEAllSuperTypes().contains(actual)) 
					{
						return true;
					}
				}
				
				for(EffectiveType mec: mc.getAllOfType())
				{
					//the element n ame
					String elementName = mec.getName();
					//if name equals return true
					if (className.equals(elementName)) {
						return true;
					}
					
					//get the eclass for the mec
					EClass type = (EClass) ePackage.getEClassifier(elementName);
					//get the eclass for the class under question
					EClass actual = (EClass) ePackage.getEClassifier(className);
					//if the class under question is a super class of the mec, should return true
					if (type.getEAllSuperTypes() != null && type.getEAllSuperTypes().contains(actual)) 
					{
						return true;
					}

				}
				
				for(String hollowElement: mc.getEmptyElements())
				{
					//if the class under question is an "empty" class that is to be loaded, return true;
					if (hollowElement.equals(className)) {
						return true;
					}
					
					//get the eclass for the mec
					EClass type = (EClass) ePackage.getEClassifier(hollowElement);
					//get the eclass for the class under question
					EClass actual = (EClass) ePackage.getEClassifier(className);
					//if the class under question is a super class of the mec, should return true
					if (type.getEAllSuperTypes().contains(actual)) 
					{
						return true;
					}

				}
			}
		}
		return false;
	}
	
	@Override
	public void disposeModel() {
		if (partialLoading) {
			registry = null;
			if (modelImpl != null) {
				//modelImpl.unload();
				SmartEmfModelResourceFactory.getInstance().removeCachedResource(modelImpl.getURI());
				modelImpl = null;
			}

			eClassCache.clear();
		}
		else {
			super.disposeModel();
		}

	}
	
	@Override
	protected ResourceSet createResourceSet() {
		if (partialLoading) {
			ResourceSet resourceSet = new EmfModelResourceSet();
			SmartEmfModelResourceFactory factory = SmartEmfModelResourceFactory.getInstance(); // <----------------------- point of change
			f = factory;
			factory.setLoadAllAttributes(loadAllAttributes);
			//factory.setModelContainers(modelContainers); // <----------------------- point of change
			factory.setObjectsAndRefNamesToVisit(objectsAndRefNamesToVisit);
			factory.setActualObjectsToLoad(actualObjectsToLoad);
			resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("*", factory);   // <----------------------- point of change
			return resourceSet;
		}
		else {
			return super.createResourceSet();
		}
		
	}
	
	public void populateCaches_v2() throws Exception
	{
		ArrayList<EClass> allOfKinds = new ArrayList<EClass>();
		ArrayList<EClass> allOfTypes = new ArrayList<EClass>();
		
		for(EffectiveMetamodel mc: modelContainers)
		{
			for(EffectiveType mec: mc.getAllOfKind())
			{
				EClass eClass = classForName(mec.getName());
				if (eClass != null) {
					allOfKinds.add(eClass);
					cachedKinds.add(eClass);	
				}
				else {
					System.out.println("eclass is null!");
				}
			}
			
			for(EffectiveType mec: mc.getAllOfType())
			{
				EClass eClass = classForName(mec.getName());
				allOfTypes.add(eClass);
				cachedTypes.add(eClass);
			}
		}
		
		for (EObject eObject : (Collection<EObject>)allContents()) {
			for(EClass eClass : allOfKinds)
			{
				if (eClass.isInstance(eObject)) {
					kindCache.put(eClass, eObject);
				}
			}
			for(EClass eClass : allOfTypes)
			{
				if (eObject.eClass() == eClass){
					typeCache.put(eClass, eObject);
				}
			}
		}
	}
	
	public void checkIntegrity() throws Exception
	{
		for(EffectiveMetamodel mc: modelContainers)
		{
			for(EffectiveType mec: mc.getAllOfKind())
			{
				EClass eClass = classForName(mec.getName());
				if (cachedKinds.contains(eClass)) {
					if (kindCache.get(eClass) != null) {
						if (kindCache.get(eClass).size() == 0) {
							System.out.println("size is 0!");
							throw new Exception();
						}
					}
					else {
						
						System.out.println("returned null!");
						throw new Exception();
					}
				}
				else {
					System.out.println("Does not contain!" + eClass.getName());
					throw new Exception();
				}
			}
			
			for(EffectiveType mec: mc.getAllOfType())
			{
				EClass eClass = classForName(mec.getName());
				if (cachedTypes.contains(eClass)) {
					if (typeCache.get(eClass) != null) {
						if (typeCache.get(eClass).size() == 0) {
							System.out.println("size is 0!");
							throw new Exception();
						}
					}
					else {
						System.out.println("returned null!");
						throw new Exception();
					}
				}
				else {
					System.out.println("Does not contain!" + eClass.getName());
					throw new Exception();
				}
			}
		}
	}
	
	public static void main(String[] args) throws URISyntaxException, Exception {
		for(int i = 0; i < 1; i++)
		{
			EolModule eolModule = new EolModule();
			eolModule.parse(new File("test/set4_100percent.eol"));
			
			EmfSmartModel smartModel = new EmfSmartModel();
			smartModel.setName("oo");
			smartModel.setModelFile(new File("test/set4.xmi").getAbsolutePath());
			smartModel.setMetamodelFile(new File("test/JDTAST.ecore").getAbsolutePath());
			
			loadEPackageFromFile("test/JDTAST.ecore");
			
			Ast2EolContext ast2EolContext = new Ast2EolContext();
			EolElement dom = ast2EolContext.getEolElementCreatorFactory().createDomElement(eolModule.getAst(), null, ast2EolContext);
			
			VariableResolver vr = new VariableResolver();
			vr.run(dom);
			
			TypeResolver tr = new TypeResolver();
			tr.getTypeResolutionContext().setModule(eolModule);
			tr.run(dom);
			
			
			LoadingOptimisationAnalyser loa = new LoadingOptimisationAnalyser();
			loa.run(dom);
			
			LoadingOptimisationAnalysisContext loaContext = (LoadingOptimisationAnalysisContext) loa.getTypeResolutionContext();
			
			smartModel.setModelContainers(loaContext.getModelContainers());
			smartModel.preProcess();
			smartModel.setSmartLoading(true);
			long init = System.nanoTime();

			smartModel.load();
			System.out.println("(took ~" + (System.nanoTime() - init) / 1000000 + "ms to load");
			init = System.nanoTime();
			
			eolModule.getContext().getModelRepository().addModel(smartModel);
			eolModule.execute();
			System.out.println("(took ~" + (System.nanoTime() - init)
					/ 1000000 + "ms to run)");
			eolModule.getContext().getModelRepository().dispose();
		}
	}
	
	public static EPackage loadEPackageFromFile(String fileName)
	{
		EPackage result = null;
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());
		URI uri = URI.createFileURI(new File(fileName).getAbsolutePath());
		Resource resource = resourceSet.getResource(uri, true);
		for(EObject obj: resource.getContents())
		{
			if(obj instanceof EPackage)
			{
				EPackage.Registry.INSTANCE.put(((EPackage) obj).getNsURI(), obj);
				result = (EPackage) obj;
				//break;
			}
		}
		return result;
	}

}
