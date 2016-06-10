package org.eclipse.epsilon.eol.visitor.resolution.type.impl;

import org.eclipse.epsilon.eol.metamodel.Block;
import org.eclipse.epsilon.eol.metamodel.Expression;
import org.eclipse.epsilon.eol.metamodel.ExpressionOrStatementBlock;
import org.eclipse.epsilon.eol.metamodel.visitor.EolVisitorController;
import org.eclipse.epsilon.eol.metamodel.visitor.ExpressionOrStatementBlockVisitor;
import org.eclipse.epsilon.eol.visitor.resolution.type.context.TypeResolutionContext;

public class ExpressionOrStatementBlockTypeResolver extends ExpressionOrStatementBlockVisitor<TypeResolutionContext, Object>{

	@Override
	public Object visit(ExpressionOrStatementBlock expressionOrStatementBlock,
			TypeResolutionContext context,
			EolVisitorController<TypeResolutionContext, Object> controller) {
		Expression expr = expressionOrStatementBlock.getExpression();
		Block block = expressionOrStatementBlock.getBlock();
		
		if (expr != null) {
			controller.visit(expr, context);
		}
		
		if (block != null) {
			controller.visit(block, context);
		}
		
		return null;
	}

}
