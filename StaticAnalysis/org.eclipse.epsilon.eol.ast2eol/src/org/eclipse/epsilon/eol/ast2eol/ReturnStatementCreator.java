package org.eclipse.epsilon.eol.ast2eol;

import org.eclipse.epsilon.common.parse.AST;
import org.eclipse.epsilon.eol.metamodel.*;
import org.eclipse.epsilon.eol.parse.EolParser;

public class ReturnStatementCreator extends StatementCreator{
	
	@Override
	public boolean appliesTo(AST ast) {
		if(ast.getType() == EolParser.RETURN)
		{
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public EolElement create(AST ast, EolElement container,
			Ast2EolContext context) {
		
		ReturnStatement statement = (ReturnStatement) context.getEolFactory().createReturnStatement(); //create a statement
		this.setAssets(ast, statement, container);
		
		if (ast.getFirstChild() != null) {
			statement.setReturned((Expression) context.getEolElementCreatorFactory().createDomElement(ast.getFirstChild(), statement, context)); //process returned
		}
		return statement;
	}

}
