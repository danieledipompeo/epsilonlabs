package org.eclipse.epsilon.emc.emf;


import java.io.File;
import java.util.ArrayList;

import org.eclipse.epsilon.eol.analysis.optimisation.loading.context.EffectiveMetamodel;
import org.eclipse.epsilon.eol.exceptions.models.EolModelLoadingException;

public class ExperimentGenerator {

	public static void main(String[] args) {
		ExperimentGenerator experimentGenerator = new ExperimentGenerator();
		experimentGenerator.run();
	}

	public void run()
	{
		EffectiveMetamodel container = new EffectiveMetamodel("DOM");
		container.addToAllOfKind("TypeDeclaration");
		container.addAttributeToModelElement("TypeDeclaration", "*");
		
		try {
			load(container);
		} catch (EolModelLoadingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public EmfSmartModel load(EffectiveMetamodel container) throws EolModelLoadingException {
		EmfSmartModel model = new EmfSmartModel();
		
		model.setName("M");
		model.setReadOnLoad(true);
		model.setMetamodelFile(new File("test/JDTAST.ecore").getAbsolutePath());
		model.setModelFile(new File("test/set0.xmi").getAbsolutePath());

		ArrayList<EffectiveMetamodel> containers = new ArrayList<EffectiveMetamodel>();
		containers.add(container);
		
		model.setModelContainers(containers);
		model.setSmartLoading(true);
		model.setPartialLoading(true);
		model.setLoadAllAttributes(false);
		model.preProcess();
		model.load();
		
		return model;
	}
}
