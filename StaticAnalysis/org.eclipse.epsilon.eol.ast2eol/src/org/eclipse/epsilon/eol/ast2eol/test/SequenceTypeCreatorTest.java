package org.eclipse.epsilon.eol.ast2eol.test;

import static org.junit.Assert.*;

import org.eclipse.epsilon.eol.metamodel.EolElement;
import org.eclipse.epsilon.eol.metamodel.EolProgram;
import org.eclipse.epsilon.eol.metamodel.ExpressionStatement;
import org.eclipse.epsilon.eol.metamodel.OrderedSetType;
import org.eclipse.epsilon.eol.metamodel.SequenceType;
import org.eclipse.epsilon.eol.metamodel.Statement;
import org.eclipse.epsilon.eol.metamodel.VariableDeclarationExpression;
import org.eclipse.epsilon.eol.metamodel.impl.EolProgramImpl;
import org.eclipse.epsilon.eol.metamodel.impl.ExpressionStatementImpl;
import org.eclipse.epsilon.eol.metamodel.impl.IntegerTypeImpl;
import org.eclipse.epsilon.eol.metamodel.impl.OrderedSetTypeImpl;
import org.eclipse.epsilon.eol.metamodel.impl.SequenceTypeImpl;
import org.eclipse.epsilon.eol.metamodel.impl.VariableDeclarationExpressionImpl;
import org.junit.Test;

public class SequenceTypeCreatorTest {

	@Test
	public void test() {
		EolElement eolElement = AST2EolElementProducer.parseAST("var a : Sequence(Integer);");
		
		
		assertEquals(eolElement.getClass(), EolProgramImpl.class);
		
		EolProgram program = (EolProgram) eolElement;
		
		Statement statement = program.getBlock().getStatements().get(0);
		
		assertEquals(statement.getClass(), ExpressionStatementImpl.class);
		
		ExpressionStatement expressionStatement = (ExpressionStatement) statement;
		
		assertEquals(expressionStatement.getExpression().getClass(), VariableDeclarationExpressionImpl.class);
		
		VariableDeclarationExpression variableDeclarationExpression = (VariableDeclarationExpression) expressionStatement.getExpression();
		
		assertEquals(variableDeclarationExpression.getName().getName(), "a");
		assertEquals(variableDeclarationExpression.getResolvedType().getClass(), SequenceTypeImpl.class);
		SequenceType sequenceType = (SequenceType) variableDeclarationExpression.getResolvedType();
		assertEquals(sequenceType.getContentType().getClass(), IntegerTypeImpl.class);
	}

}
