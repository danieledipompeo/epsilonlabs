package org.eclipse.epsilon.eol.visitor.resolution.type.operationDefinitionHandler;

import java.util.ArrayList;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.epsilon.eol.metamodel.*;
import org.eclipse.epsilon.eol.visitor.resolution.type.context.TypeResolutionContext;
import org.eclipse.epsilon.eol.visitor.resolution.type.operationDefinitionUtil.StandardLibraryOperationDefinitionContainer;

public class _deprecated_CollectionQueryingOperationHandler extends _deprecated_CollectionOperationDefinitionHandler{

	public _deprecated_CollectionQueryingOperationHandler(TypeResolutionContext context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean appliesTo(String name, ArrayList<Type> argTypes) {
		// TODO Auto-generated method stub
		return (name.equals("includes") ||
				name.equals("excludes") ||
				name.equals("includesAll") ||
				name.equals("excludesAll") && argTypes.size() == 1);
	}

	@Override
	public OperationDefinition handle(
			FeatureCallExpression featureCallExpression, Type contextType,
			ArrayList<Type> argTypes) {
		
		String name = ((MethodCallExpression) featureCallExpression).getMethod().getName();
		
		StandardLibraryOperationDefinitionContainer container = context.getOperationDefinitionControl().getStandardLibraryOperationDefinitionContainer();
		
		OperationDefinition result = container.getOperation(name, argTypes);

		CollectionType targetType = (CollectionType) featureCallExpression.getTarget().getResolvedType();
		
		if (name.equals("includes") || name.equals("excludes")) {
			if (targetType.getContentType() instanceof AnyType) {
			}
			else {
				Type contentType = targetType.getContentType();
				Type argType = argTypes.get(0);
				if (!contentType.getClass().equals(argType.getClass())) {
					context.getLogBook().addError(((MethodCallExpression) featureCallExpression).getArguments().get(0), "Type mismiatch with Collection's content");
				}
			}
		}
		
		else if (name.equals("includesAll") || name.equals("excludesAll")) {
			if (targetType.getContentType() instanceof AnyType) {
			}
			else {
				Type contentType = targetType.getContentType();
				CollectionType argType = (CollectionType) argTypes.get(0);
				Type argContentType = argType.getContentType();
				if (!contentType.getClass().equals(argContentType.getClass())) {
					context.getLogBook().addError(((MethodCallExpression) featureCallExpression).getArguments().get(0), "Type mismiatch with Collection's content");
				}
			}
		}
		return result;
	}

}
