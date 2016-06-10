package org.eclipse.epsilon.eol.ast2eol.test;

import static org.junit.Assert.*;

import org.eclipse.epsilon.eol.metamodel.AssignmentStatement;
import org.eclipse.epsilon.eol.metamodel.DivideOperatorExpression;
import org.eclipse.epsilon.eol.metamodel.EolElement;
import org.eclipse.epsilon.eol.metamodel.EolProgram;
import org.eclipse.epsilon.eol.metamodel.ExpressionStatement;
import org.eclipse.epsilon.eol.metamodel.VariableDeclarationExpression;
import org.eclipse.epsilon.eol.metamodel.impl.AssignmentStatementImpl;
import org.eclipse.epsilon.eol.metamodel.impl.BooleanTypeImpl;
import org.eclipse.epsilon.eol.metamodel.impl.DivideOperatorExpressionImpl;
import org.eclipse.epsilon.eol.metamodel.impl.EolProgramImpl;
import org.eclipse.epsilon.eol.metamodel.impl.ExpressionStatementImpl;
import org.eclipse.epsilon.eol.metamodel.impl.IntegerExpressionImpl;
import org.eclipse.epsilon.eol.metamodel.impl.VariableDeclarationExpressionImpl;
import org.junit.Test;

public class DivideOperatorExpressionCreatorTest {

	@Test
	public void test() {
		EolElement eolElement = AST2EolElementProducer.parseAST("var a = 1 / 3; ");
		
		assertEquals(eolElement.getClass(), EolProgramImpl.class);
		EolProgram program = (EolProgram) eolElement;
		assertEquals(program.getBlock().getStatements().get(0).getClass(), AssignmentStatementImpl.class);
		
		AssignmentStatement assignmentStatement = (AssignmentStatement) program.getBlock().getStatements().get(0);
		
		assertEquals(assignmentStatement.getLhs().getClass(), VariableDeclarationExpressionImpl.class);
		
		assertEquals(assignmentStatement.getRhs().getClass(), DivideOperatorExpressionImpl.class);
		
		DivideOperatorExpression divideOperatorExpression = (DivideOperatorExpression) assignmentStatement.getRhs();
		
		assertEquals(divideOperatorExpression.getLhs().getClass(), IntegerExpressionImpl.class);
		assertEquals(divideOperatorExpression.getRhs().getClass(), IntegerExpressionImpl.class);
		
	}

}
