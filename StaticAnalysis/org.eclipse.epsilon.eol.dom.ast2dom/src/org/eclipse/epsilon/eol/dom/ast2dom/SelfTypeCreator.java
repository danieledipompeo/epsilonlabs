package org.eclipse.epsilon.eol.dom.ast2dom;

import org.eclipse.epsilon.eol.dom.Type;

public class SelfTypeCreator extends PseudoTypeCreator{

	@Override
	public Type create(Ast2DomContext context) {
		return context.getEolFactory().createSelfType();
	}

	@Override
	public String getType() {
		return "SelfType";
	}
}