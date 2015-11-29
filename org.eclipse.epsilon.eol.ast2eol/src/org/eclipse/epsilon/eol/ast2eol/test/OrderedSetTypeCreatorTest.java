package org.eclipse.epsilon.eol.ast2eol.test;

import static org.junit.Assert.assertEquals;

import org.eclipse.epsilon.eol.metamodel.EOLElement;
import org.eclipse.epsilon.eol.metamodel.EOLModule;
import org.eclipse.epsilon.eol.metamodel.ExpressionStatement;
import org.eclipse.epsilon.eol.metamodel.OrderedSetType;
import org.eclipse.epsilon.eol.metamodel.Statement;
import org.eclipse.epsilon.eol.metamodel.VariableDeclarationExpression;
import org.eclipse.epsilon.eol.metamodel.impl.EOLModuleImpl;
import org.eclipse.epsilon.eol.metamodel.impl.ExpressionStatementImpl;
import org.eclipse.epsilon.eol.metamodel.impl.IntegerTypeImpl;
import org.eclipse.epsilon.eol.metamodel.impl.OrderedSetTypeImpl;
import org.eclipse.epsilon.eol.metamodel.impl.VariableDeclarationExpressionImpl;
import org.junit.Test;

public class OrderedSetTypeCreatorTest {

	@Test
	public void test() {
		EOLElement eolElement = AST2EolElementProducer.parseAST("var a : OrderedSet(Integer);");
		
		
		assertEquals(eolElement.getClass(), EOLModuleImpl.class);
		
		EOLModule program = (EOLModule) eolElement;
		
		Statement statement = program.getBlock().getStatements().get(0);
		
		assertEquals(statement.getClass(), ExpressionStatementImpl.class);
		
		ExpressionStatement expressionStatement = (ExpressionStatement) statement;
		
		assertEquals(expressionStatement.getExpression().getClass(), VariableDeclarationExpressionImpl.class);
		
		VariableDeclarationExpression variableDeclarationExpression = (VariableDeclarationExpression) expressionStatement.getExpression();
		
		assertEquals(variableDeclarationExpression.getName().getName(), "a");
		assertEquals(variableDeclarationExpression.getResolvedType().getClass(), OrderedSetTypeImpl.class);
		OrderedSetType orderedSetType = (OrderedSetType) variableDeclarationExpression.getResolvedType();
		assertEquals(orderedSetType.getContentType().getClass(), IntegerTypeImpl.class);
	}

}
