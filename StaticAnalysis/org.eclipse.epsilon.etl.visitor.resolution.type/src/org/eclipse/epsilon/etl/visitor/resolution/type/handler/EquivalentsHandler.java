package org.eclipse.epsilon.etl.visitor.resolution.type.handler;

import java.util.ArrayList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.epsilon.eol.metamodel.AnnotationBlock;
import org.eclipse.epsilon.eol.metamodel.AssignmentStatement;
import org.eclipse.epsilon.eol.metamodel.BagType;
import org.eclipse.epsilon.eol.metamodel.CollectionType;
import org.eclipse.epsilon.eol.metamodel.EolElement;
import org.eclipse.epsilon.eol.metamodel.Expression;
import org.eclipse.epsilon.eol.metamodel.FeatureCallExpression;
import org.eclipse.epsilon.eol.metamodel.FormalParameterExpression;
import org.eclipse.epsilon.eol.metamodel.MethodCallExpression;
import org.eclipse.epsilon.eol.metamodel.ModelElementType;
import org.eclipse.epsilon.eol.metamodel.OperationDefinition;
import org.eclipse.epsilon.eol.metamodel.SequenceType;
import org.eclipse.epsilon.eol.metamodel.SimpleAnnotation;
import org.eclipse.epsilon.eol.metamodel.StringExpression;
import org.eclipse.epsilon.eol.metamodel.StringType;
import org.eclipse.epsilon.eol.metamodel.Type;
import org.eclipse.epsilon.eol.visitor.resolution.type.operationDefinitionHandler.AnyOperationDefinitionHandler;
import org.eclipse.epsilon.eol.visitor.resolution.type.operationDefinitionUtil.StandardLibraryOperationDefinitionContainer;
import org.eclipse.epsilon.etl.metamodel.RuleDependency;
import org.eclipse.epsilon.etl.metamodel.TransformationRule;
import org.eclipse.epsilon.etl.visitor.resolution.type.context.EtlTypeResolutionContext;
import org.eclipse.epsilon.etl.visitor.resolution.type.context.TraceUnitContainer;

public class EquivalentsHandler extends AnyOperationDefinitionHandler{

	public EquivalentsHandler(EtlTypeResolutionContext context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean appliesTo(String name, ArrayList<Type> argTypes) {
		return name.equals("equivalents");
	}

	@Override
	public OperationDefinition handle(
			FeatureCallExpression featureCallExpression, Type contextType,
			ArrayList<Type> argTypes) {
		
		StandardLibraryOperationDefinitionContainer container = context.getOperationDefinitionControl().getStandardLibraryOperationDefinitionContainer();
		
		OperationDefinition result = null;
		if (argTypes.size() == 0) {
			//get the operation by name and arg types
			result = container.getOperation(((MethodCallExpression) featureCallExpression).getMethod().getName(), argTypes);
		}
		
		else {
			//get the operation by name and arg types
			result = container.getOperation(((MethodCallExpression) featureCallExpression).getMethod().getName(), new ArrayList<Type>());
		}

		ArrayList<StringExpression> parameters = new ArrayList<StringExpression>();
		
		MethodCallExpression mce = (MethodCallExpression) featureCallExpression;
		for(Expression expr: mce.getArguments())
		{
			if (expr.getResolvedType() instanceof StringType) {
				if (expr instanceof StringExpression) {
					parameters.add((StringExpression) expr);
				}
				else {
					context.getLogBook().addWarning(expr, "use of String expression is recommended");
				}
			}
			else {
				context.getLogBook().addError(expr, "This expression should be of type String");
			}
		}

		Expression target = featureCallExpression.getTarget();
		EtlTypeResolutionContext leContext = (EtlTypeResolutionContext) context;

		if (target.getResolvedType() instanceof ModelElementType) {
			ModelElementType targetType = (ModelElementType) target.getResolvedType(); //get the target typ
			if (targetType != null) { //if target type is not null
				EClass ecoreType = (EClass) targetType.getEcoreType(); //get the ecore type from the target type
				
				ArrayList<TraceUnitContainer> containers = null;
				if (parameters.size() == 0) {
					containers = leContext.getTraceUnitContainersWhichTransforms(ecoreType);
				}
				else {
					containers = leContext.getTraceUnitContainersWhichTransforms(ecoreType, parameters);
				}

//				ArrayList<TraceUnitContainer> containers = leContext.getTraceUnitContainersWhichTransforms(ecoreType);

				for(TraceUnitContainer tuc : containers)
				{
					TransformationRule dependingRule = tuc.getTransformationRule(); //get the depending rule from the context
					TransformationRule currentRule = leContext.getCurrentRule(); //get the current rul
					if (currentRule != null) { //if the current rule is not null
						RuleDependency ruleDependency = leContext.getEtlFactory().createRuleDependency();
						ruleDependency.setDependingRule(dependingRule);
						ruleDependency.setSourceElement(featureCallExpression);
						context.setAssets(ruleDependency, currentRule);

						currentRule.getResolvedRuleDependencies().add(ruleDependency); //resolve the dependency
					}
				}
				SequenceType sequenceType = context.getEolFactory().createSequenceType();
//				BagType bag2 = context.getEolFactory().createBagType();
//				bag2.setContentType(context.getEolFactory().createAnyType());
//				context.setAssets(bag2, bag);
//				bag.setContentType(bag2);
				sequenceType.setContentType(context.getEolFactory().createAnyType());
				
				result.setReturnType(sequenceType);
				context.setAssets(sequenceType, result);

				
//				TransformationRule dependingRule = leContext.getTraceUnitContainerWhichTransforms(ecoreType).getTransformationRule(); //get the depending rule from the context
//				if (dependingRule == null) { //if depending rule is null, return null
//					context.getLogBook().addError(featureCallExpression, "No applicable transformation rule is found");
//					return null;
//				}
//				TransformationRule currentRule = leContext.getCurrentRule(); //get the current rul
//				if (currentRule != null) { //if the current rule is not null
//					RuleDependency ruleDependency = leContext.getEtlFactory().createRuleDependency();
//					ruleDependency.setDependingRule(dependingRule);
//					ruleDependency.setSourceElement(featureCallExpression);
//					context.setAssets(ruleDependency, currentRule);
//
//					currentRule.getResolvedRuleDependencies().add(ruleDependency); //resolve the dependency
//				}
//				if (dependingRule.getTargets().size() > 0) { //if the depending rule has targets 
//					FormalParameterExpression primaryTarget = dependingRule.getTargets().get(0); //get the first target
//					ModelElementType primaryTargetType = (ModelElementType) primaryTarget.getResolvedType();
//					if (primaryTargetType != null) { //if the first target is not null
//						if (primaryTargetType.getEcoreType() != null) {
//							result.setReturnType(EcoreUtil.copy(primaryTargetType));
//						}
//						else {
//							context.getLogBook().addError(primaryTarget, "type not resolved properly");
//						}
//					}
//				}
			}

		}
		else if (target.getResolvedType() instanceof CollectionType) {
			CollectionType targetType = (CollectionType) target.getResolvedType();
			ModelElementType targetContentType = (ModelElementType) targetType.getContentType();
			if (targetContentType != null) {
				EClass ecoreType = (EClass) targetContentType.getEcoreType();
				
				ArrayList<TraceUnitContainer> containers = null;
				
				if (parameters.size() == 0) {
					containers = leContext.getTraceUnitContainersWhichTransforms(ecoreType);

				}
				else {
					containers = leContext.getTraceUnitContainersWhichTransforms(ecoreType, parameters);
				}

//				ArrayList<TraceUnitContainer> containers = leContext.getTraceUnitContainersWhichTransforms(ecoreType);
				for(TraceUnitContainer tuc : containers)
				{
					TransformationRule dependingRule = tuc.getTransformationRule(); //get the depending rule from the context
					TransformationRule currentRule = leContext.getCurrentRule(); //get the current rul
					if (currentRule != null) { //if the current rule is not null
						RuleDependency ruleDependency = leContext.getEtlFactory().createRuleDependency();
						ruleDependency.setDependingRule(dependingRule);
						ruleDependency.setSourceElement(featureCallExpression);
						context.setAssets(ruleDependency, currentRule);

						currentRule.getResolvedRuleDependencies().add(ruleDependency); //resolve the dependency
					}
				}
				BagType bag = context.getEolFactory().createBagType();
				BagType bag2 = context.getEolFactory().createBagType();
				bag2.setContentType(context.getEolFactory().createAnyType());
				context.setAssets(bag2, bag);
				bag.setContentType(bag2);
				
				result.setReturnType(bag);
				context.setAssets(bag, result);
			}
			else {
				context.getLogBook().addError(targetType, "type not resolved properly");
			}
		}
		else {
			context.getLogBook().addError(target, "operaiton equivalents() can only be used on model elements and collections"); 
		}
		supressErrorForAssignment(featureCallExpression);
		return result;
	}
	
	public void supressErrorForAssignment(FeatureCallExpression featureCallExpression)
	{
		EolElement container = featureCallExpression;
		Expression rhs = null;
		while(container != null)
		{
			if (container.getContainer() instanceof AssignmentStatement) {
				rhs = (Expression) container;
				EtlTypeResolutionContext leContext = (EtlTypeResolutionContext) context;
				leContext.addSupression(rhs);
//				ArrayList<log.Error> errors = context.getLogBook().getErrors();
//				for(log.Error error: errors)
//				{
//					if (error.getDomElement().equals(rhs)) {
//						errors.remove(error);
//					}
//				}
//				context.getLogBook().addWarning(rhs, "potential type mismatch");
				break;
			}
			container = container.getContainer();
		}
	}

}
