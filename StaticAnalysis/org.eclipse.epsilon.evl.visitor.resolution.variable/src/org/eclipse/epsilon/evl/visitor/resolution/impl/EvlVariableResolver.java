package org.eclipse.epsilon.evl.visitor.resolution.impl;

import org.eclipse.epsilon.eol.metamodel.EolElement;
import org.eclipse.epsilon.eol.metamodel.visitor.EolDefaultVisitor;
import org.eclipse.epsilon.eol.metamodel.visitor.EolVisitorController;
import org.eclipse.epsilon.eol.metamodel.visitor.ExpressionOrStatementBlockVisitor;
import org.eclipse.epsilon.eol.visitor.resolution.variable.context.VariableResolutionContext;
import org.eclipse.epsilon.eol.visitor.resolution.variable.impl.ExpressionOrStatementBlockVariableResolver;
import org.eclipse.epsilon.eol.visitor.resolution.variable.impl.FOLMethodCallExpressionVariableResolver;
import org.eclipse.epsilon.eol.visitor.resolution.variable.impl.ForStatementVariableResolver;
import org.eclipse.epsilon.eol.visitor.resolution.variable.impl.FormalParameterExpressionVariableResolver;
import org.eclipse.epsilon.eol.visitor.resolution.variable.impl.IfStatementVariableResolver;
import org.eclipse.epsilon.eol.visitor.resolution.variable.impl.ImportVariableResolver;
import org.eclipse.epsilon.eol.visitor.resolution.variable.impl.NameExpressionVariableResolver;
import org.eclipse.epsilon.eol.visitor.resolution.variable.impl.OperationDefinitionVariableResolver;
import org.eclipse.epsilon.eol.visitor.resolution.variable.impl.SwitchCaseDefaultStatementVariableResolver;
import org.eclipse.epsilon.eol.visitor.resolution.variable.impl.SwitchCaseExpressionStatementVariableResolver;
import org.eclipse.epsilon.eol.visitor.resolution.variable.impl.SwitchStatementVariableResolver;
import org.eclipse.epsilon.eol.visitor.resolution.variable.impl.VariableDeclarationExpressionVariableResolver;
import org.eclipse.epsilon.eol.visitor.resolution.variable.impl.WhileStatementVariableResolver;
import org.eclipse.epsilon.evl.metamodel.visitor.EvlVisitorController;

public class EvlVariableResolver {
	
	protected EvlVisitorController<VariableResolutionContext, Object> controller = new EvlVisitorController<VariableResolutionContext, Object>();
	protected VariableResolutionContext context = new VariableResolutionContext();
	
	public EvlVariableResolver()
	{
		controller.addImportVisitor(new ImportVariableResolver());
		//controller.addProgramVisitor(new ProgramVariableResolver());
		controller.addDefaultVisitor(new EolDefaultVisitor<VariableResolutionContext, Object>());
		
		//controller.addDefaultVisitor(new EtlDefaultVariableResolver());
		controller.addVariableDeclarationExpressionVisitor(new VariableDeclarationExpressionVariableResolver());
		controller.addFormalParameterExpressionVisitor(new FormalParameterExpressionVariableResolver());
		controller.addNameExpressionVisitor(new NameExpressionVariableResolver());
		controller.addForStatementVisitor(new ForStatementVariableResolver());
		controller.addIfStatementVisitor(new IfStatementVariableResolver());
		controller.addSwitchCaseDefaultStatementVisitor(new SwitchCaseDefaultStatementVariableResolver());
		controller.addSwitchStatementVisitor(new SwitchStatementVariableResolver());
		controller.addSwitchCaseExpressionStatementVisitor(new SwitchCaseExpressionStatementVariableResolver());
		controller.addWhileStatementVisitor(new WhileStatementVariableResolver());
		controller.addOperationDefinitionVisitor(new OperationDefinitionVariableResolver());
		controller.addFOLMethodCallExpressionVisitor(new FOLMethodCallExpressionVariableResolver());
		controller.addExpressionOrStatementBlockVisitor(new ExpressionOrStatementBlockVariableResolver());
		
		controller.addConstraintVisitor(new ConstraintVariableResolver());
		controller.addContextVisitor(new ContextVariableResolver());
		controller.addCritiqueVisitor(new CritiqueVariableResolver());
		controller.addEvlProgramVisitor(new EvlProgramVariableResolver());
		controller.addFixVisitor(new FixVariableResolver());
		controller.addPreBlockVisitor(new PreBlockVariableResolver());
		controller.addPostBlockVisitor(new PostBlockVariableResolver());
	}
	
	public void run(EolElement eolElement)
	{
		controller.visit(eolElement, context);
	}
	
	
	public VariableResolutionContext getContext() {
		return context;
	}


}
