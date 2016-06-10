package org.eclipse.epsilon.etl.visitor.resolution.variable.impl;

import org.eclipse.epsilon.eol.visitor.resolution.variable.context.VariableResolutionContext;
import org.eclipse.epsilon.etl.metamodel.PreBlock;
import org.eclipse.epsilon.etl.metamodel.visitor.EtlVisitorController;
import org.eclipse.epsilon.etl.metamodel.visitor.PreBlockVisitor;

public class PreBlockVariableResolver extends PreBlockVisitor<VariableResolutionContext, Object>{


	@Override
	public Object visit(PreBlock preBlock, VariableResolutionContext context,
			EtlVisitorController<VariableResolutionContext, Object> controller) {
		if (preBlock.getName() != null) {
			controller.visit(preBlock.getName(), context);
		}
		if (preBlock.getBody() != null) {
			controller.visit(preBlock.getBody(), context);	
		}
		return null;
	}

}
