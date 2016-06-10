package org.eclipse.epsilon.eol.visitor.resolution.type.tier2.impl;

import org.eclipse.epsilon.eol.metamodel.*;
import org.eclipse.epsilon.eol.metamodel.visitor.EolVisitorController;
import org.eclipse.epsilon.eol.metamodel.visitor.NotOperatorExpressionVisitor;
import org.eclipse.epsilon.eol.visitor.resolution.type.tier1.context.TypeResolutionContext;
import org.eclipse.epsilon.eol.visitor.resolution.type.tier2.context.TypeResolutionContext_T2;

public class NotOperatorExpressionTypeResolver extends NotOperatorExpressionVisitor<TypeResolutionContext_T2, Object>{

	@Override
	public Object visit(NotOperatorExpression notOperatorExpression,
			TypeResolutionContext_T2 context,
			EolVisitorController<TypeResolutionContext_T2, Object> controller) {
		
		Expression expr = notOperatorExpression.getExpr();
		
		controller.visit(expr, context); //visit expr first
		
		Type exprType = expr.getResolvedType();
		
		Type type = context.getEolFactory().createBooleanType(); //set type first, this allows minor-error in expressions n statements
		notOperatorExpression.setResolvedType(type);
		context.setAssets(type, notOperatorExpression); //set the type to the notOperatorExpression
		
		if (exprType instanceof AnyType) {
			return null;
		}
		
		if(!(exprType instanceof BooleanType))
		{
			context.getLogBook().addError(notOperatorExpression.getExpr(), "Expression is not of type Boolean");
		}
		
		return null;
	}

}
