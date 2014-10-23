package org.eclipse.epsilon.eol.visitor.resolution.type.tier2.impl;

import java.util.ArrayList;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.epsilon.eol.metamodel.AnnotationBlock;
import org.eclipse.epsilon.eol.metamodel.AnyType;
import org.eclipse.epsilon.eol.metamodel.CollectionType;
import org.eclipse.epsilon.eol.metamodel.Expression;
import org.eclipse.epsilon.eol.metamodel.MethodCallExpression;
import org.eclipse.epsilon.eol.metamodel.ModelElementType;
import org.eclipse.epsilon.eol.metamodel.NameExpression;
import org.eclipse.epsilon.eol.metamodel.OperationArgType;
import org.eclipse.epsilon.eol.metamodel.OperationDefinition;
import org.eclipse.epsilon.eol.metamodel.SelfContentType;
import org.eclipse.epsilon.eol.metamodel.SelfInnermostType;
import org.eclipse.epsilon.eol.metamodel.SelfType;
import org.eclipse.epsilon.eol.metamodel.SimpleAnnotation;
import org.eclipse.epsilon.eol.metamodel.StringExpression;
import org.eclipse.epsilon.eol.metamodel.Type;
import org.eclipse.epsilon.eol.metamodel.visitor.EolVisitorController;
import org.eclipse.epsilon.eol.metamodel.visitor.MethodCallExpressionVisitor;
import org.eclipse.epsilon.eol.visitor.resolution.type.tier1.context.TypeResolutionContext;
import org.eclipse.epsilon.eol.visitor.resolution.type.tier2.context.TypeResolutionContext_T2;

public class MethodCallExpressionTypeResolver extends MethodCallExpressionVisitor<TypeResolutionContext_T2, Object>{

	@Override
	public Object visit(MethodCallExpression methodCallExpression,
			TypeResolutionContext_T2 context,
			EolVisitorController<TypeResolutionContext_T2, Object> controller) {
		
		Type targetType = null;
		boolean arrow = false;
		ArrayList<Type> argTypes = new ArrayList<Type>(); //prepare argTypes
		String methodName = methodCallExpression.getMethod().getName();  //get method name
		

		if (methodCallExpression.getTarget() == null) { //if the target is null
			for(Expression argument: methodCallExpression.getArguments()) //process arguments
			{
				controller.visit(argument, context); //resolve the type of the argument first
				argTypes.add(EcoreUtil.copy(argument.getResolvedType())); //if is not any type, add to the argument list immediately
			}
			targetType = context.getEolFactory().createAnyType(); //since the target is null, we create an AnyType
			
		}
		else { //if the target is not null
			controller.visit(methodCallExpression.getTarget(), context); //visit content first
			
			for(Expression argument: methodCallExpression.getArguments()) //process arguments
			{
				controller.visit(argument, context); //resolve the type of the argument first
				argTypes.add(argument.getResolvedType()); //if is not any type, add to the argument list immediately
			}
			targetType = methodCallExpression.getTarget().getResolvedType(); //get target type
			
			if (targetType == null) { //if target type is null
				//note : this should be an error, but leave like this by now ==============================================================================
				targetType = context.getEolFactory().createAnyType(); //there should be an error
			}
			
			arrow = methodCallExpression.getIsArrow().isVal();
		}
		
		OperationDefinition operationDefinition;
		operationDefinition = context.getOperationDefinitionControl().getOperation(methodCallExpression, methodName, targetType, argTypes, arrow); //fetch operation definition using name, context type and arg types
		
		if (operationDefinition != null) { //if there is an operation	
			for(int i = 0; i < operationDefinition.getParameters().size(); i++)//deals with parameter of type Type
			{
				//if the parameter is keyword "Type"
				if (operationDefinition.getParameters().get(i).getResolvedType().eClass().getName().equals("Type")) {
					//if the argument is an name expression
					if(methodCallExpression.getArguments().get(i) instanceof NameExpression)
					{
						NameExpression arg = (NameExpression) methodCallExpression.getArguments().get(i);
						if (context.getTypeUtil().isKeyWord(arg.getName())) {
							
						}
						else {
							context.getLogBook().addError(arg, "The argument should be either an EOL type or a model element type");
						}
					}
					else {
						context.getLogBook().addError(methodCallExpression.getArguments().get(i), "The argument should be either an EOL type or a model element type");
					}
				}
			}
			Type contextType = operationDefinition.getContextType(); //get the context type of the operation
			if (context.getTypeUtil().isEqualOrGeneric(targetType,contextType)) { //if target type and context type is generic
				if (operationDefinition.getAnnotationBlock() != null) {
					AnnotationBlock annotationBlock = operationDefinition.getAnnotationBlock();
					
					//if the operation needs to return the innermost type of the argument
					if(annotationContains(annotationBlock, "returnInnermostType"))
					{
						setInnermostType(operationDefinition, getInnermostType(targetType));
						
						methodCallExpression.setResolvedType(EcoreUtil.copy(operationDefinition.getReturnType()));
						methodCallExpression.getMethod().setResolvedType(EcoreUtil.copy(operationDefinition.getReturnType())); //set the resolved type of the method
						methodCallExpression.getMethod().setResolvedContent(operationDefinition); //set resolved content
						return null;
					}
					
					//check if the target is collection type and the inntermost type 
					if(annotationContains(annotationBlock, "checkCollectionArgSingle"))
					{
						//if targettype is collection
						if (targetType instanceof CollectionType) {
							//get the innermost type of the target
							Type contentType = getInnermostType(targetType);
							if (contentType instanceof AnyType) {
							}
							else {
								for(Expression e: methodCallExpression.getArguments())
								{
									if (context.getTypeUtil().isGeneric(e.getResolvedType(), contentType)) {
										
									}
									else {
										context.getLogBook().addError(e, "The type of this parameter does not comply with the content type of target");
									}
								}
							}
						}
					}
					if(annotationContains(annotationBlock, "checkCollectionArgCollection"))
					{
						if(targetType instanceof CollectionType)
						{
							Type targetContentType = getInnermostType(targetType);
							if (targetContentType instanceof AnyType) {
							}
							else {
								for(Expression e: methodCallExpression.getArguments())
								{
									if (e.getResolvedType() instanceof CollectionType) {
										Type argContentType = getInnermostType(e.getResolvedType());
										if (context.getTypeUtil().isGeneric(argContentType, targetContentType)) {
											
										}
										else {
											context.getLogBook().addError(e, "The type of this parameter does not comply with the content type of target");
										}
									}
									else {
										context.getLogBook().addError(e, "To use the _checkCollectionArgCollection annotation, all arguments should be of type collection");
									}
								}
							}
						}
					}
					if (annotationContains(annotationBlock, "modelOp")) {		
						
						if (targetType instanceof ModelElementType) {
							
						}
						else {
							String methodcallName = methodCallExpression.getMethod().getName();
							context.getLogBook().addError(targetType, "operation " + methodcallName + " can only be used on ModelElementTypes");
							return null;
						}
						
						
												
//						Expression rawTarget = methodCallExpression.getTarget(); //get targettype
//						if(!(rawTarget instanceof NameExpression)) //if targettype is not a NameExpressioin
//						{
//							context.getLogBook().addError(rawTarget, "operation " + methodcallName + " can only be used on ModelElementTypes");
//							return null;
//						}
//						else { //else
//							NameExpression target = (NameExpression) rawTarget; //cast the target to NameExpression
//							String targetname = target.getName();
//							if (targetname.contains("!")) {
//								targetname = targetname.substring(targetname.indexOf("!")+1, targetname.length());
//							}
//							if (context.numberOfMetamodelsDefine(targetname, true) > 0) { //if the NameExpression is a keyword in the metamodels
//								Type rawTargetType = rawTarget.getResolvedType();
//								
//								if (!(rawTargetType instanceof ModelElementType)) {
//									context.getLogBook().addError(rawTarget, "operation " + methodcallName + " can only be used on ModelElementTypes");
//									return null;
//								}
//								else if (rawTargetType instanceof ModelElementType) {
//									
//									operationDefinition.setContextType(EcoreUtil.copy(rawTargetType));
//									//CollectionType returnType = (CollectionType) result.getReturnType();
//									//returnType.setContentType(EcoreUtil.copy(rawTargetType));
//								}
//							}
//							else {
//								context.getLogBook().addError(rawTarget, "Model Element Type " + target.getName() + " does not exist");
//								return null;
//							}
//						}
					}
				}
				else {
//					if (targetIsNull) {
//						context.getLogBook().addError(methodCallExpression, "Operation " + operationDefinition.getName().getName() + " requires a target");
//					}
				}
				if (operationDefinition.getReturnType() instanceof SelfType) { //if is self type
					methodCallExpression.setResolvedType(EcoreUtil.copy(targetType));//just copy the target type because the target type has been resolved
					methodCallExpression.getMethod().setResolvedType(EcoreUtil.copy(targetType)); //set the resolved type of the method
					methodCallExpression.getMethod().setResolvedContent(operationDefinition); //set resolved content
				}
				else if (operationDefinition.getReturnType() instanceof SelfContentType) { //if is selfContentType
					if (targetType instanceof CollectionType) { //if target type is of collection type
						Type contentType = ((CollectionType) targetType).getContentType(); //getContentType
						if (contentType != null) {
							methodCallExpression.setResolvedType(EcoreUtil.copy(contentType)); //set resolved type
							methodCallExpression.getMethod().setResolvedType(EcoreUtil.copy(contentType)); //set method type
							methodCallExpression.getMethod().setResolvedContent(operationDefinition); //set resolved content
						}
						else {
							AnyType tempAnyType = context.getEolFactory().createAnyType();
							methodCallExpression.setResolvedType(EcoreUtil.copy(tempAnyType));
							methodCallExpression.getMethod().setResolvedType(EcoreUtil.copy(tempAnyType));
							//this should be Any i guess?
							//handle content type null
						}
						
					}
					else {
						AnyType tempAnyType = context.getEolFactory().createAnyType();
						methodCallExpression.setResolvedType(EcoreUtil.copy(tempAnyType));
						methodCallExpression.getMethod().setResolvedType(EcoreUtil.copy(tempAnyType));
						//handle this
					}
				}
				else if(operationDefinition.getReturnType() instanceof OperationArgType)
				{
					if(operationDefinition.getParameters().size() != 1)
					{
						context.getLogBook().addError(operationDefinition.getReturnType(), "To use OperationArgType, there needs to be exactly one parameter");
					}
					else
					{
						Type argType = methodCallExpression.getArguments().get(0).getResolvedType();
						methodCallExpression.setResolvedType(EcoreUtil.copy(argType)); //set resolved type
						methodCallExpression.getMethod().setResolvedType(EcoreUtil.copy(argType)); //set method type
						methodCallExpression.getMethod().setResolvedContent(operationDefinition); //set resolved content
					}
				}
				else if(operationDefinition.getReturnType() instanceof SelfInnermostType)
				{
					if (targetType instanceof CollectionType) { //if target type is of collection type============================
						Type contentType = ((CollectionType) targetType).getContentType(); //getContentType
						while(contentType instanceof CollectionType) //while the contenttype is collection
						{
							contentType = ((CollectionType) contentType).getContentType(); //get content type
						}
						if (contentType != null) {
							methodCallExpression.setResolvedType(EcoreUtil.copy(contentType)); //set resolved type
							methodCallExpression.getMethod().setResolvedType(EcoreUtil.copy(contentType)); //set method type
							methodCallExpression.getMethod().setResolvedContent(operationDefinition); //set resolved content
						}
						else {
							//this should be Any i guess?
							//handle content type null
						}
						
					}
					else {
						context.getLogBook().addError(operationDefinition.getReturnType(), "To target should be of type Collection");
					}
				}
				else if (operationDefinition.getReturnType() instanceof CollectionType && 
						(((CollectionType)operationDefinition.getReturnType()).getContentType() instanceof SelfType || 
						((CollectionType)operationDefinition.getReturnType()).getContentType() instanceof SelfContentType)) { //if the return type is collection type and its content type is either SelfType or SelfContentType ============================
					CollectionType returnType = (CollectionType) operationDefinition.getReturnType();
					Type collectionContentType = returnType.getContentType();
					if (collectionContentType instanceof SelfType) {
						CollectionType resultType = EcoreUtil.copy(returnType);
						resultType.setContentType(EcoreUtil.copy(targetType));
						methodCallExpression.setResolvedType(EcoreUtil.copy(resultType)); //set the type of the method call
						methodCallExpression.getMethod().setResolvedType(EcoreUtil.copy(resultType)); //set resolved type
						methodCallExpression.getMethod().setResolvedContent(operationDefinition); //set resolved content
					}
					if (collectionContentType instanceof SelfContentType) {
						if (targetType instanceof CollectionType) { //if target type is of collection type
							Type contentType = ((CollectionType) targetType).getContentType(); //getContentType
							if (contentType != null) {
								CollectionType resultType = EcoreUtil.copy(returnType);
								resultType.setContentType(EcoreUtil.copy(contentType));
								methodCallExpression.setResolvedType(EcoreUtil.copy(resultType)); //set resolved type
								methodCallExpression.getMethod().setResolvedType(EcoreUtil.copy(resultType)); //set method type
								methodCallExpression.getMethod().setResolvedContent(operationDefinition); //set resolved content
							}
							else {
								//this should be Any i guess?
								//handle content type null
							}
						}
						else {
							context.getLogBook().addError(operationDefinition.getReturnType(), "The target should be of type Collection");
						}
					}
				}
				
				else {
					methodCallExpression.setResolvedType(EcoreUtil.copy(operationDefinition.getReturnType())); //set the type of the method call
					methodCallExpression.getMethod().setResolvedType(EcoreUtil.copy(operationDefinition.getReturnType())); //set resolved type
					methodCallExpression.getMethod().setResolvedContent(operationDefinition); //set resolved content
				}
			}
			else if (targetType instanceof AnyType) {
//				if (targetIsNull) {
//					context.getLogBook().addError(methodCallExpression, "Operation " + operationDefinition.getName().getName() + " requires a target");
//					return null;
//				}
//				else {
					methodCallExpression.setResolvedType(EcoreUtil.copy(operationDefinition.getReturnType())); //set the type of the method call
					methodCallExpression.getMethod().setResolvedType(EcoreUtil.copy(operationDefinition.getReturnType())); //set resolved type
					methodCallExpression.getMethod().setResolvedContent(operationDefinition); //set resolved conten
//				}
			}
			else {
				//handle type incompatible
				String expectedType = "";
				String actualType = "";
				if (contextType instanceof ModelElementType) {
					expectedType = ((ModelElementType)contextType).getModelName() + "!" + ((ModelElementType)contextType).getElementName();
				}
				
				else {
					expectedType = contextType.getClass().toString();
				}
				if (targetType instanceof ModelElementType) {
					actualType = ((ModelElementType)targetType).getModelName() + "!" + ((ModelElementType)targetType).getElementName();
				}
				else {
					actualType = targetType.getClass().toString();
				}
				context.getLogBook().addError(methodCallExpression.getTarget(), "Type mismatch for Operation: " + 
				operationDefinition.getName().getName() + "()" + "; Expected type: " + expectedType + 
				" , actual type: " + actualType);
				
			}
		}
		else {
			String argString = "";
			for(int i = 0; i < argTypes.size(); i++)
			{
				argString += (argTypes.get(i).getClass().getSimpleName());
				if (i == argTypes.size() - 1) {
					
				}
				else {
					argString += ", ";
				}
			}
			context.getLogBook().addError(methodCallExpression, "OperationDefinition: " + methodName + "("+ argString +") " + "cannot be found");
		}
		return null;
	}

	public boolean annotationContains(AnnotationBlock block, String s)
	{
		boolean result = false;
		for(SimpleAnnotation annot: block.getSimpleAnnotations())
		{
			if (annot.getName().getName().equals("_sysParam")) {
				for(StringExpression str : annot.getValues())
				{
					if (str.getVal().equals(s)) {
						result = true;
						return result;
					}
				}
				
			}
		}
		return result;
	}
	
	public Type getInnermostType(Type t)
	{
		if (t instanceof CollectionType) {
			CollectionType collectionType = (CollectionType) t;
			Type contentType = collectionType.getContentType();
			while(contentType instanceof CollectionType)
			{
				contentType = ((CollectionType)contentType).getContentType();
			}
			return EcoreUtil.copy(contentType);
		}
		else {
			return EcoreUtil.copy(t);
		}
	}
	
	public void setInnermostType(OperationDefinition op, Type innermost)
	{
		//get the return type
		Type returnType = op.getReturnType();
		//if return type is collection type
		if (returnType instanceof CollectionType) {
			//get the content type of the collection type
			Type contentType = ((CollectionType)returnType).getContentType();
			//if contenttype is also a collection type
			if (contentType instanceof CollectionType) {
				//while the content type is collection type
				while(contentType instanceof CollectionType)
				{
					//go one level deeper into the contenttype
					if (((CollectionType)contentType).getContentType() instanceof CollectionType) {
						contentType = ((CollectionType)contentType).getContentType();
					}
					else {
						break;
					}
				}
				//set the content type
				((CollectionType)contentType).setContentType(EcoreUtil.copy(innermost));
			}
			else {
				((CollectionType) returnType).setContentType(EcoreUtil.copy(innermost));
			}
		}
		else
		{
			op.setReturnType(EcoreUtil.copy(innermost));
		}
	}
}