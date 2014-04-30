package org.eclipse.epsilon.eol.dom.metamodel.coverage.impl;

import org.eclipse.epsilon.eol.dom.FormalParameterExpression;
import org.eclipse.epsilon.eol.dom.OperationDefinition;
import org.eclipse.epsilon.eol.dom.metamodel.coverage.context.CoverageAnalysisContext;

import org.eclipse.epsilon.eol.dom.visitor.EolVisitorController;
import org.eclipse.epsilon.eol.dom.visitor.OperationDefinitionVisitor;

public class OperationDefinitionCoverageAnalyser extends OperationDefinitionVisitor<CoverageAnalysisContext, Object>{

	@Override
	public Object visit(OperationDefinition operationDefinition,
			CoverageAnalysisContext context,
			EolVisitorController<CoverageAnalysisContext, Object> controller) {
		// TODO Auto-generated method stub
		if (operationDefinition.getContextType() != null) {
			controller.visit(operationDefinition.getContextType(), context);
		}
		if (operationDefinition.getReturnType() != null) {
			controller.visit(operationDefinition.getReturnType(), context);	
		}
		for(FormalParameterExpression fpe: operationDefinition.getParameters())
		{
			controller.visit(fpe, context);
		}
		controller.visit(operationDefinition.getBody(), context);
		return null;
	}

}