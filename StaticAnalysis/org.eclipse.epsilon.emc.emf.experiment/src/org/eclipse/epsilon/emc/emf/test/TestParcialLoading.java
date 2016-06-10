package org.eclipse.epsilon.emc.emf.test;


import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;

import org.eclipse.emf.common.util.URI;
import org.eclipse.epsilon.emc.emf.EmfSmartModel;
import org.eclipse.epsilon.emc.emf.SmartEmfModelResourceFactory;
import org.eclipse.epsilon.eol.analysis.optimisation.loading.context.EffectiveMetamodel;
import org.eclipse.epsilon.eol.exceptions.models.EolModelLoadingException;
import org.junit.Before;
import org.junit.Test;

public class TestParcialLoading {
	
	@Before
	public void teardown() {
		SmartEmfModelResourceFactory.getInstance().clearCache();
	}
	
	@Test
	public void testLoadAllOfTypeEClassifier() throws Exception {
		EffectiveMetamodel container = new EffectiveMetamodel("filesystem");
		container.addToAllOfType("Filesystem");
		assertEquals(1, load(container).allContents().size());
	}
	
	@Test
	public void testLoadAllOfKindEClassifier() throws Exception {
		EffectiveMetamodel container = new EffectiveMetamodel("filesystem");
		container.addToAllOfKind("File");
		
		assertEquals(2, load(container).allContents().size());		
	}
	
	@Test
	public void testLoadAttribute() throws Exception {
		EffectiveMetamodel container = new EffectiveMetamodel("filesystem");
		container.addToAllOfKind("File");
		container.addAttributeToModelElement("File", "name");
		
		assertEquals(2, load(container).allContents().size());		
	}
	
	@Test
	public void testLoadReference() throws Exception {
		EffectiveMetamodel container = new EffectiveMetamodel("filesystem");
		container.addToAllOfKind("File");
		container.addToAllOfKind("Filesystem");
		container.addAttributeToModelElement("Filesystem", "files");
		
		assertEquals(3, load(container).allContents().size());	
		
	}
	
	public EmfSmartModel load(EffectiveMetamodel container) throws EolModelLoadingException {
		EmfSmartModel model = new EmfSmartModel();
		
		model.setName("M");
		model.setReadOnLoad(true);
		
		String metamodel = new File("src/org/eclipse/epsilon/emc/emf/test/filesystem.ecore").getAbsolutePath();
		model.setMetamodelFile(metamodel);
		
		URI modelPath = URI.createURI(TestParcialLoading.class.getResource("test_model.model").toString());
		model.setModelFileUri(modelPath);
		
		ArrayList<EffectiveMetamodel> containers = new ArrayList<EffectiveMetamodel>();
		containers.add(container);
		
		model.setModelContainers(containers);
		model.setSmartLoading(true);
		model.setPartialLoading(true);
		model.preProcess();
		model.load();
		
		return model;
	}
	


}
