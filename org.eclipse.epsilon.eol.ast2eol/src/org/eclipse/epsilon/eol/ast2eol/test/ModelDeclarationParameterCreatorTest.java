package org.eclipse.epsilon.eol.ast2eol.test;

import static org.junit.Assert.assertEquals;

import org.eclipse.epsilon.eol.metamodel.EOLElement;
import org.eclipse.epsilon.eol.metamodel.EOLProgram;
import org.eclipse.epsilon.eol.metamodel.ModelDeclarationParameter;
import org.eclipse.epsilon.eol.metamodel.ModelDeclarationStatement;
import org.eclipse.epsilon.eol.metamodel.NameExpression;
import org.eclipse.epsilon.eol.metamodel.StringExpression;
import org.eclipse.epsilon.eol.metamodel.impl.EOLProgramImpl;
import org.eclipse.epsilon.eol.metamodel.impl.NameExpressionImpl;
import org.eclipse.epsilon.eol.metamodel.impl.StringExpressionImpl;
import org.junit.Test;

public class ModelDeclarationParameterCreatorTest {

	@Test
	public void test() {
		EOLElement eolElement = AST2EolElementProducer.parseAST("model Core driver EMF {nsuri = \"org.amma.dsl.jdt.core\"};");
		
		assertEquals(eolElement.getClass(), EOLProgramImpl.class);
		
		EOLProgram program = (EOLProgram) eolElement;
		assertEquals(program.getBlock().getStatements().size(), 0);
		
		assertEquals(program.getModelDeclarations().size(), 1);
		
		ModelDeclarationStatement modelDeclarationStatement = program.getModelDeclarations().get(0);
		assertEquals(modelDeclarationStatement.getName().getName().getName(), "Core");
		assertEquals(modelDeclarationStatement.getAlias().size(), 0);
		assertEquals(modelDeclarationStatement.getDriver().getName(), "EMF");
		assertEquals(modelDeclarationStatement.getParameters().size(), 1);

		ModelDeclarationParameter parameter = (ModelDeclarationParameter) modelDeclarationStatement.getParameters().get(0);
		assertEquals(parameter.getKey().getClass(), NameExpressionImpl.class);
		NameExpression key = (NameExpression) parameter.getKey();
		
		assertEquals(parameter.getValue().getClass(), StringExpressionImpl.class);
		StringExpression value = (StringExpression) parameter.getValue();
		
		assertEquals(key.getName(), "nsuri");
		assertEquals(value.getValue(), "org.amma.dsl.jdt.core");

	}

}
