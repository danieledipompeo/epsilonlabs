package org.eclipse.epsilon.eol.visitor.printer.impl;

import org.eclipse.epsilon.eol.metamodel.Block;
import org.eclipse.epsilon.eol.metamodel.ExpressionOrStatementBlock;
import org.eclipse.epsilon.eol.metamodel.Statement;
import org.eclipse.epsilon.eol.metamodel.visitor.EolVisitorController;
import org.eclipse.epsilon.eol.metamodel.visitor.ExpressionOrStatementBlockVisitor;
import org.eclipse.epsilon.eol.visitor.printer.context.PrinterContext;

public class ExpressionOrStatementBlockPrinter extends ExpressionOrStatementBlockVisitor<PrinterContext, Object>{

	@Override
	public Object visit(ExpressionOrStatementBlock expressionOrStatementBlock,
			PrinterContext context,
			EolVisitorController<PrinterContext, Object> controller) {
		String result = "";
		if (expressionOrStatementBlock.getBlock() != null) {
			Block block = expressionOrStatementBlock.getBlock();
			for(Statement stmt: block.getStatements())
			{
				result += controller.visit(stmt, context);
				result += context.newline();
			}
		}
		else if (expressionOrStatementBlock.getExpression() != null) {
			result += controller.visit(expressionOrStatementBlock.getExpression(), context);
			result += context.newline();
		}

		return result;
	}

}
