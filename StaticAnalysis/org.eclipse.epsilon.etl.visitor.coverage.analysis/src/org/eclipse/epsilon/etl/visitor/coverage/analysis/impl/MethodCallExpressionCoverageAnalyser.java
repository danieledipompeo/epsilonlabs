package org.eclipse.epsilon.etl.visitor.coverage.analysis.impl;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.epsilon.eol.metamodel.MethodCallExpression;
import org.eclipse.epsilon.eol.metamodel.OperationDefinition;
import org.eclipse.epsilon.eol.metamodel.visitor.EolVisitorController;
import org.eclipse.epsilon.eol.metamodel.visitor.MethodCallExpressionVisitor;
import org.eclipse.epsilon.etl.visitor.coverage.analysis.context.CoverageAnalysisContext;

public class MethodCallExpressionCoverageAnalyser extends MethodCallExpressionVisitor<CoverageAnalysisContext, Object>{

	@Override
	public Object visit(MethodCallExpression methodCallExpression,
			CoverageAnalysisContext context,
			EolVisitorController<CoverageAnalysisContext, Object> controller) {
		// TODO Auto-generated method stub
		for(EObject od : methodCallExpression.getResolvedMethods())
		{
			if (od instanceof OperationDefinition) {
				controller.visit(od, context);
			}
		}
		return null;
	}

}
