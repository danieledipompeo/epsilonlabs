package org.eclipse.epsilon.egl.servlet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

import javax.servlet.ServletContext;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.epsilon.commons.util.StringProperties;
import org.eclipse.epsilon.emc.emf.EmfModel;
import org.eclipse.epsilon.emc.emf.EmfModelResourceFactory;
import org.eclipse.epsilon.emc.emf.EmfUtil;
import org.eclipse.epsilon.eol.exceptions.models.EolModelLoadingException;
import org.eclipse.epsilon.eol.models.IModel;
import org.eclipse.epsilon.eol.models.ModelRepository;

public class ModelManager {
	
	protected ServletContext servletContext;
	protected ModelRepository currentModelRepository;
	protected HashMap<String, EmfModel> cachedModels = new HashMap<String, EmfModel>();
	
	public void setCurrentModelRepository(ModelRepository currentModelRepository) {
		this.currentModelRepository = currentModelRepository;
	}
	
	public ModelRepository getCurrentModelRepository() {
		return currentModelRepository;
	}
	
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
	
	public ServletContext getServletContext() {
		return servletContext;
	}
	
	public void loadModel(String name, String aliases, String modelFile, String metamodel, boolean expand, boolean metamodelIsFilebased) throws EolModelLoadingException {
		
		EmfModel model = cachedModels.get(modelFile);
		
		if (model == null) {
			model = new EmfModel();
			
			StringProperties properties = new StringProperties();
			properties.put(EmfModel.PROPERTY_NAME, name);
			properties.put(EmfModel.PROPERTY_ALIASES, aliases);
			properties.put(EmfModel.PROPERTY_EXPAND, expand + "");
			properties.put(EmfModel.PROPERTY_MODEL_FILE, this.getServletContext().getRealPath(modelFile));
			if (!metamodelIsFilebased) {
				properties.put(EmfModel.PROPERTY_METAMODEL_URI, metamodel);
				properties.put(EmfModel.PROPERTY_IS_METAMODEL_FILE_BASED, "false");
			}
			else {
				properties.put(EmfModel.PROPERTY_METAMODEL_FILE, this.getServletContext().getRealPath(metamodel));
				properties.put(EmfModel.PROPERTY_IS_METAMODEL_FILE_BASED, "true");			
			}
			properties.put(EmfModel.PROPERTY_READONLOAD, "true");
			properties.put(EmfModel.PROPERTY_STOREONDISPOSAL, "false");
			
			model.load(properties, null);
			cachedModels.put(modelFile, model);
		}
		
		getCurrentModelRepository().addModel(model);
		
	}

	public void loadModel(String name, String modelFile, String metamodelUri) throws EolModelLoadingException {
		loadModel(name, "", modelFile, metamodelUri, true, false);
	}
	
	public void loadModelByFile(String name, String modelFile, String metamodelFile) throws EolModelLoadingException {
		loadModel(name, "", modelFile, metamodelFile, true, true);
	}	
	
	protected ArrayList<String> registeredMetamodels = new ArrayList<String>(); 
	
	public void registerMetamodel(String metamodelFile) throws Exception {
		if (registeredMetamodels.contains(metamodelFile)) return;
		EmfUtil.register(URI.createURI(getServletContext().getResource(metamodelFile).toString()), EPackage.Registry.INSTANCE);
		registeredMetamodels.add(metamodelFile);
	}
	
	public void uncacheModel(String modelFile) {
		cachedModels.remove(modelFile);
	}
	
	protected void reset() {
		cachedModels.clear();
		registeredMetamodels.clear();
	}
	
}
