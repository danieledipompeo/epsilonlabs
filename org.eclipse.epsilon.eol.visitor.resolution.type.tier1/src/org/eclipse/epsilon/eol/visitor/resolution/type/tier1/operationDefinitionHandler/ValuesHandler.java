package org.eclipse.epsilon.eol.visitor.resolution.type.tier1.operationDefinitionHandler;

import java.util.ArrayList;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.epsilon.eol.metamodel.AnyType;
import org.eclipse.epsilon.eol.metamodel.BagType;
import org.eclipse.epsilon.eol.metamodel.EolFactory;
import org.eclipse.epsilon.eol.metamodel.EolPackage;
import org.eclipse.epsilon.eol.metamodel.Expression;
import org.eclipse.epsilon.eol.metamodel.FeatureCallExpression;
import org.eclipse.epsilon.eol.metamodel.MapType;
import org.eclipse.epsilon.eol.metamodel.MethodCallExpression;
import org.eclipse.epsilon.eol.metamodel.OperationDefinition;
import org.eclipse.epsilon.eol.metamodel.Type;
import org.eclipse.epsilon.eol.problem.LogBook;
import org.eclipse.epsilon.eol.problem.imessages.IMessage_TypeResolution;
import org.eclipse.epsilon.eol.visitor.resolution.type.tier1.context.AnalysisInterruptException;
import org.eclipse.epsilon.eol.visitor.resolution.type.tier1.operationDefinitionUtil.OperationDefinitionManager;
import org.eclipse.epsilon.eol.visitor.resolution.type.tier1.operationDefinitionUtil.StandardLibraryOperationDefinitionContainer;
import org.eclipse.epsilon.eol.visitor.resolution.type.tier1.util.TypeInferenceManager;
import org.eclipse.epsilon.eol.visitor.resolution.type.tier1.util.TypeUtil;

public class ValuesHandler extends MapOperationDefinitionHandler{

	@Override
	public boolean appliesTo(String name, ArrayList<Type> argTypes) {
		return name.equals("values") && argTypes.size() == 0;
	}

	@Override
	public OperationDefinition handle(
			FeatureCallExpression featureCallExpression, Type contextType,
			ArrayList<Type> argTypes) throws AnalysisInterruptException {
		
		//get the manager
		StandardLibraryOperationDefinitionContainer manager = OperationDefinitionManager.getInstance().getStandardLibraryOperationDefinitionContainer();
		
		//get the result
		OperationDefinition result = manager.getOperation(((MethodCallExpression) featureCallExpression).getMethod().getName(), argTypes);
		
		//if result is not null
		if (result != null) {
			
			//register the result as it has been handled
			OperationDefinitionManager.getInstance().registerHandledOperationDefinition(result);

			//get the target
			Expression target = featureCallExpression.getTarget();
			
			//if target is null, report and return
			if (target == null) {
				LogBook.getInstance().addError(featureCallExpression, IMessage_TypeResolution.OPERATION_REQUIRES_TARGET);
				BagType returnType = EolFactory.eINSTANCE.createBagType();
				result.setReturnType(EcoreUtil.copy(returnType));
				return result;
			}
			else {
				//get the target type copy
				Type targetType = EcoreUtil.copy(target.getResolvedType());

				//if target type is null, report and return (this will not happend)
				if (targetType == null) {
					LogBook.getInstance().addError(target, IMessage_TypeResolution.EXPRESSION_DOES_NOT_HAVE_A_TYPE);
					BagType returnType = EolFactory.eINSTANCE.createBagType();
					result.setReturnType(EcoreUtil.copy(returnType));
					return result;
				}
				//if target type is collection type
				if (targetType instanceof MapType) {
					MapType _targetType = (MapType) EcoreUtil.copy(targetType);
					AnyType valueType = _targetType.getValueType();
					BagType returnType = EolFactory.eINSTANCE.createBagType();
					returnType.setContentType(valueType);
					result.setReturnType(EcoreUtil.copy(returnType));
					return result;
				}
				//else if target type is an instance of any
				else if (TypeUtil.getInstance().isInstanceofAnyType(targetType)) {
					//get dynamic types that are of type collection
					ArrayList<Type> dyntypes = TypeInferenceManager.getInstance().getDynamicTypes((AnyType) targetType, EolPackage.eINSTANCE.getMapType());
					//if size is 0, no collection type is found, report and return
					if (dyntypes.size() == 0) {
						LogBook.getInstance().addError(target, IMessage_TypeResolution.EXPRESSION_MAY_NOT_BE_MAP);
						BagType returnType = EolFactory.eINSTANCE.createBagType();
						result.setReturnType(EcoreUtil.copy(returnType));
						return result;
					}
					else {
						MapType _targetType = (MapType) EcoreUtil.copy(dyntypes.get(0));
						AnyType valueType = _targetType.getValueType();
						BagType returnType = EolFactory.eINSTANCE.createBagType();
						returnType.setContentType(valueType);
						result.setReturnType(EcoreUtil.copy(returnType));
						return result;	
					}
				}
				else {
					LogBook.getInstance().addError(target, IMessage_TypeResolution.EXPRESSION_SHOULD_BE_COLLECTION_TYPE);
					BagType returnType = EolFactory.eINSTANCE.createBagType();
					result.setReturnType(EcoreUtil.copy(returnType));
					return result;
				}
			}
		}
		return result;
	}

}