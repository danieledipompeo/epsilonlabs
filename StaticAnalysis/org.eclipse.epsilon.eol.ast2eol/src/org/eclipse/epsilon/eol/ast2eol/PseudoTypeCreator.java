package org.eclipse.epsilon.eol.ast2eol;

import org.eclipse.epsilon.eol.metamodel.*;

public class PseudoTypeCreator extends TypeCreator{

	@Override
	public Type create(Ast2EolContext context) {
		return context.getEolFactory().createPseudoType();
	}

	@Override
	public String getType() {
		return "PseudoType";
	}
}
