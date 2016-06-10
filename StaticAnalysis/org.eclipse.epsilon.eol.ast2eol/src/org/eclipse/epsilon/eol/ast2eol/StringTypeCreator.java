package org.eclipse.epsilon.eol.ast2eol;

import org.eclipse.epsilon.eol.metamodel.*;

public class StringTypeCreator extends PrimitiveTypeCreator{

	@Override
	public Type create(Ast2EolContext context) {
		return context.getEolFactory().createStringType();
	}

	@Override
	public String getType() {
		return "String";
	}
 
}
