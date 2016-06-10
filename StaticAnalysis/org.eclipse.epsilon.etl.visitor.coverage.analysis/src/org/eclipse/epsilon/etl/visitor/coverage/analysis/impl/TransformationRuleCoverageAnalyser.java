package org.eclipse.epsilon.etl.visitor.coverage.analysis.impl;

import org.eclipse.epsilon.eol.metamodel.FormalParameterExpression;
import org.eclipse.epsilon.etl.metamodel.TransformationRule;
import org.eclipse.epsilon.etl.metamodel.visitor.EtlVisitorController;
import org.eclipse.epsilon.etl.metamodel.visitor.TransformationRuleVisitor;
import org.eclipse.epsilon.etl.visitor.coverage.analysis.context.CoverageAnalysisContext;

public class TransformationRuleCoverageAnalyser extends TransformationRuleVisitor<CoverageAnalysisContext, Object>{

	@Override
	public Object visit(TransformationRule transformationRule,
			CoverageAnalysisContext context,
			EtlVisitorController<CoverageAnalysisContext, Object> controller) {
		//context.setCurrentElement(transformationRule);
		controller.visit(transformationRule.getSource(), context);
		for(FormalParameterExpression target: transformationRule.getTargets())
		{
			controller.visit(target, context);
		}
		if (transformationRule.getGuard() != null) {
			controller.visit(transformationRule.getGuard(), context);	
		}
		controller.visit(transformationRule.getBody(), context);
		
		return null;
	}

}
