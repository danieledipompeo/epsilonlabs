package org.eclipse.epsilon.eol.dom.ast2dom;

import org.eclipse.epsilon.commons.parse.AST;
import org.eclipse.epsilon.eol.dom.DomElement;

public class GreaterThanOperatorExpressionCreator extends BinaryOperatorExpressionCreator{

	@Override
	public DomElement create(AST ast, DomElement container,
			Ast2DomContext context) {
		// TODO Auto-generated method stub
		return context.getDomFactory().createGreaterThanOperatorExpression();
	}

}