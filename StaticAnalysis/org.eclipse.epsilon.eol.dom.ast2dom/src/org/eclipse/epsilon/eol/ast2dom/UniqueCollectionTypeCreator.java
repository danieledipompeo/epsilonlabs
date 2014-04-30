package org.eclipse.epsilon.eol.ast2dom;

import org.eclipse.epsilon.eol.metamodel.*;

public class UniqueCollectionTypeCreator extends CollectionTypeCreator{
	@Override
	public Type create(Ast2DomContext context) {
		return context.getEolFactory().createUniqueCollectionType();
	}

	@Override
	public String getType() {
		return "UniqueCollection";
	}
}