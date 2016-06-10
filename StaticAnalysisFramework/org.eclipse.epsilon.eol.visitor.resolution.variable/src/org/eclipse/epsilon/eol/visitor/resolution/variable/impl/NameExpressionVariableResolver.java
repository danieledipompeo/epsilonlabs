package org.eclipse.epsilon.eol.visitor.resolution.variable.impl;

import java.util.ArrayList;

import org.eclipse.epsilon.eol.metamodel.NameExpression;
import org.eclipse.epsilon.eol.metamodel.VariableDeclarationExpression;
import org.eclipse.epsilon.eol.metamodel.visitor.EolVisitorController;
import org.eclipse.epsilon.eol.metamodel.visitor.NameExpressionVisitor;
import org.eclipse.epsilon.eol.problem.LogBook;
import org.eclipse.epsilon.eol.problem.imessages.IMessage_VariableResolution;
import org.eclipse.epsilon.eol.visitor.resolution.variable.context.PluralVariable;
import org.eclipse.epsilon.eol.visitor.resolution.variable.context.SimpleVariable;
import org.eclipse.epsilon.eol.visitor.resolution.variable.context.Variable;
import org.eclipse.epsilon.eol.visitor.resolution.variable.context.VariableResolutionContext;

public class NameExpressionVariableResolver extends NameExpressionVisitor<VariableResolutionContext, Object>{

	@Override
	public Object visit(NameExpression nameExpression,
			VariableResolutionContext context,
			EolVisitorController<VariableResolutionContext, Object> controller) {
		//get the variable from the stack
		Variable v = context.getStack().getVariable(nameExpression.getName());
		if(v != null)
		{
			//if v is a instnace of the simple variable
			if (v instanceof SimpleVariable) {
				//get the variable declaration from the variable
				VariableDeclarationExpression vde = (VariableDeclarationExpression) ((SimpleVariable) v).getVariable();
				//set the resolved content to the variable
				nameExpression.setResolvedContent(vde);
				vde.getReferences().add(nameExpression);
				//if the container of the name expression is a assignment statement
			}
			//if v is not a simple variable, then it should be defined in the model declaration statement
			else {
				ArrayList<VariableDeclarationExpression> content = new ArrayList<VariableDeclarationExpression>();

				//for all of the variable declaration expressions in the plural variable, add to the content arraylist
				for(VariableDeclarationExpression vde: ((PluralVariable)v).getValues())
				{
					content.add(vde);
					vde.getReferences().add(nameExpression);
				}
				//set the content array list to the name expression
				nameExpression.setResolvedContent(content);
			}
		}
		else {
			if (context.isReservedWord(nameExpression.getName())) {
				
			}
			else {
				LogBook.getInstance().addError(nameExpression, IMessage_VariableResolution.bindMessage(IMessage_VariableResolution.X_CANNOT_BE_RESOLVED, nameExpression.getName()));	
			}
			
		}
		return null;
	}

}
