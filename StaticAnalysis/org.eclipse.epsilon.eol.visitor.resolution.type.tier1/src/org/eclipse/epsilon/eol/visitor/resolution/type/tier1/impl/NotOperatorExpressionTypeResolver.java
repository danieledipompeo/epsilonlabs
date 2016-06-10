package org.eclipse.epsilon.eol.visitor.resolution.type.tier1.impl;

import org.eclipse.epsilon.eol.metamodel.*;
import org.eclipse.epsilon.eol.metamodel.visitor.EolVisitorController;
import org.eclipse.epsilon.eol.metamodel.visitor.NotOperatorExpressionVisitor;
import org.eclipse.epsilon.eol.visitor.resolution.type.tier1.context.TypeResolutionContext;

public class NotOperatorExpressionTypeResolver extends NotOperatorExpressionVisitor<TypeResolutionContext, Object>{

	@Override
	public Object visit(NotOperatorExpression notOperatorExpression,
			TypeResolutionContext context,
			EolVisitorController<TypeResolutionContext, Object> controller) {
		
		Expression expr = notOperatorExpression.getExpr();
		
		controller.visit(expr, context); 
		
		Type exprType = expr.getResolvedType();
		
		//set type first, this allows minor-error in expressions n statements
		Type type = context.getEolFactory().createBooleanType(); 
		notOperatorExpression.setResolvedType(type);
		context.setAssets(type, notOperatorExpression); //set the type to the notOperatorExpression

		
		if (exprType != null) {
			if (exprType instanceof AnyType) {
				return null;
			}
			if(!(exprType instanceof BooleanType))
			{
				context.getLogBook().addError(notOperatorExpression.getExpr(), "Expression should be boolean");
			}
		}
		else {
			context.getLogBook().addError(expr, "Expression does not have a type");
		}
		
		return null;
	}

}
