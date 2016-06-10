package org.eclipse.epsilon.eol.metamodel.visitor;

import org.eclipse.epsilon.eol.metamodel.*;

public abstract class BagTypeVisitor<T, R> {
	
	public boolean appliesTo(BagType bagType, T context) {
		return true;
	}
	
	public abstract R visit (BagType bagType, T context, EolVisitorController<T, R> controller);
	
}
