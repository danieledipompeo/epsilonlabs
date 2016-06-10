package org.eclipse.epsilon.eol.visitor.resolution.type.tier1.impl;

import org.eclipse.epsilon.eol.metamodel.AnyType; 
import org.eclipse.epsilon.eol.metamodel.BooleanType;
import org.eclipse.epsilon.eol.metamodel.EolFactory;
import org.eclipse.epsilon.eol.metamodel.EolPackage;
import org.eclipse.epsilon.eol.metamodel.Expression;
import org.eclipse.epsilon.eol.metamodel.NotOperatorExpression;
import org.eclipse.epsilon.eol.metamodel.Type;
import org.eclipse.epsilon.eol.metamodel.visitor.EolVisitorController;
import org.eclipse.epsilon.eol.metamodel.visitor.NotOperatorExpressionVisitor;
import org.eclipse.epsilon.eol.problem.LogBook;
import org.eclipse.epsilon.eol.problem.imessages.IMessage_TypeResolution;
import org.eclipse.epsilon.eol.visitor.resolution.type.tier1.context.TypeResolutionContext;
import org.eclipse.epsilon.eol.visitor.resolution.type.tier1.util.TypeInferenceManager;
import org.eclipse.epsilon.eol.visitor.resolution.type.tier1.util.TypeUtil;

public class NotOperatorExpressionTypeResolver extends NotOperatorExpressionVisitor<TypeResolutionContext, Object>{

	@Override
	public Object visit(NotOperatorExpression notOperatorExpression,
			TypeResolutionContext context,
			EolVisitorController<TypeResolutionContext, Object> controller) {
		
		//get expression
		Expression expression = notOperatorExpression.getExpression();
		
		//resolve
		controller.visit(expression, context);
		
		//get type
		Type expressionType = expression.getResolvedType();
		
		//set type first
		Type type = EolFactory.eINSTANCE.createBooleanType(); 
		notOperatorExpression.setResolvedType(type);
		context.setAssets(type, notOperatorExpression); //set the type to the notOperatorExpression

		if (expressionType == null) {
			LogBook.getInstance().addError(expression, IMessage_TypeResolution.EXPRESSION_DOES_NOT_HAVE_A_TYPE);
			return null;
		}
		
		if (expressionType instanceof BooleanType) {
			return null;
		}
		else if (TypeUtil.getInstance().isInstanceofAnyType(expressionType)) {
			if (TypeInferenceManager.getInstance().containsDynamicType((AnyType) expressionType, EolPackage.eINSTANCE.getBooleanType())) {
				return null;
			}
			else {
				LogBook.getInstance().addError(expression, IMessage_TypeResolution.EXPRESSION_MAY_NOT_BE_BOOLEAN);
			}
		}
		else {
			LogBook.getInstance().addError(expression, IMessage_TypeResolution.EXPRESSION_MAY_NOT_BE_BOOLEAN);
		}
		
		return null;
	}

}
