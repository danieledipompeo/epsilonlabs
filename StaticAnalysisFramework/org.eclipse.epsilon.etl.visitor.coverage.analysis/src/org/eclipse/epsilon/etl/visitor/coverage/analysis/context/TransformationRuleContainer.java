package org.eclipse.epsilon.etl.visitor.coverage.analysis.context;

import java.util.ArrayList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.epsilon.eol.metamodel.FormalParameterExpression;
import org.eclipse.epsilon.eol.metamodel.ModelElementType;
import org.eclipse.epsilon.eol.metamodel.Type;
import org.eclipse.epsilon.etl.metamodel.TransformationRule;

public class TransformationRuleContainer {

	protected TransformationRule transformationRule;
	protected MetaElementContainer sourceContainer;
	protected ArrayList<MetaElementContainer> targetcontainers = new ArrayList<MetaElementContainer>();
	protected ArrayList<MetaElementContainer> otherContinaers = new ArrayList<MetaElementContainer>();
	
	public TransformationRuleContainer(TransformationRule rule)
	{
		transformationRule = rule;
		Type sType = rule.getSource().getResolvedType();
		if (sType instanceof ModelElementType) {
			ModelElementType sourceType = (ModelElementType) sType; 
			EClass ecoreType = (EClass) sourceType.getModelType();
			if (ecoreType != null) {
				sourceContainer = new MetaElementContainer(ecoreType);
			}
		}
		
		for(FormalParameterExpression target: rule.getTargets())
		{
			Type tType = target.getResolvedType();
			if (tType instanceof ModelElementType) {
				ModelElementType targetType = (ModelElementType) tType;
				EClass ecoreType = (EClass) targetType.getModelType();
				if (ecoreType != null) {
					targetcontainers.add(new MetaElementContainer(ecoreType));
				}
			}
		}
	}
	
	public TransformationRule getTransformationRule() {
		return transformationRule;
	}
	
	public MetaElementContainer getSourceContainer() {
		return sourceContainer;
	}
	
	public ArrayList<MetaElementContainer> getTargetcontainers() {
		return targetcontainers;
	}
	
	public ArrayList<MetaElementContainer> getOtherContinaers() {
		return otherContinaers;
	}
	
	public void add(EClass eClass, String propertyName, boolean featureAccessViaOpposite)
	{
		if (sourceContainer.getEClass().equals(eClass)) {
			sourceContainer.add(propertyName, featureAccessViaOpposite);
			return;
		}
		
		for(MetaElementContainer mec: targetcontainers)
		{
			if (mec.getEClass().equals(eClass)) {
				mec.add(propertyName, featureAccessViaOpposite);
				return;
			}
		}
		
		for(MetaElementContainer mec: otherContinaers)
		{
			if (mec.getEClass().equals(eClass)) {
				mec.add(propertyName, featureAccessViaOpposite);
				return;
			}
		}
		
		MetaElementContainer newContainer = new MetaElementContainer(eClass);
		newContainer.add(propertyName, featureAccessViaOpposite);
		otherContinaers.add(newContainer);
		
	}
}
