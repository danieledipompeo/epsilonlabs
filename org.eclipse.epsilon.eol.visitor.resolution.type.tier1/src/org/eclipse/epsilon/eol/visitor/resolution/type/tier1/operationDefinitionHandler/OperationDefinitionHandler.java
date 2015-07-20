package org.eclipse.epsilon.eol.visitor.resolution.type.tier1.operationDefinitionHandler;

import java.util.ArrayList;

import org.eclipse.epsilon.eol.metamodel.FeatureCallExpression;
import org.eclipse.epsilon.eol.metamodel.OperationDefinition;
import org.eclipse.epsilon.eol.metamodel.Type;
import org.eclipse.epsilon.eol.visitor.resolution.type.tier1.context.TypeResolutionContext;

public abstract class OperationDefinitionHandler {

	protected TypeResolutionContext context;
	
	public OperationDefinitionHandler(TypeResolutionContext context)
	{
		this.context = context;
	}
	
	public abstract boolean appliesTo(String name, ArrayList<Type> argTypes);
	
	public abstract OperationDefinition handle(FeatureCallExpression featureCallExpression, Type contextType, ArrayList<Type> argTypes);
}
