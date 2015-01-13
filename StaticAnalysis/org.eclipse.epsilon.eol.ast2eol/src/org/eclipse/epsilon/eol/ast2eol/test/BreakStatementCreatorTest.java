package org.eclipse.epsilon.eol.ast2eol.test;

import static org.junit.Assert.*;

import org.eclipse.epsilon.eol.metamodel.EolElement;
import org.eclipse.epsilon.eol.metamodel.EolProgram;
import org.eclipse.epsilon.eol.metamodel.ExpressionStatement;
import org.eclipse.epsilon.eol.metamodel.VariableDeclarationExpression;
import org.eclipse.epsilon.eol.metamodel.impl.BooleanTypeImpl;
import org.eclipse.epsilon.eol.metamodel.impl.BreakStatementImpl;
import org.eclipse.epsilon.eol.metamodel.impl.EolProgramImpl;
import org.eclipse.epsilon.eol.metamodel.impl.ExpressionStatementImpl;
import org.eclipse.epsilon.eol.metamodel.impl.VariableDeclarationExpressionImpl;
import org.junit.Test;

public class BreakStatementCreatorTest {

	@Test
	public void test() {
		EolElement eolElement = AST2EolElementProducer.parseAST("break; ");
		
		
		assertEquals(eolElement.getClass(), EolProgramImpl.class);
		EolProgram program = (EolProgram) eolElement;
		assertEquals(program.getBlock().getStatements().get(0).getClass(), BreakStatementImpl.class);
		
	}

}