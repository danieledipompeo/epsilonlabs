package org.eclipse.epsilon.eol.visitor.resolution.type.tier1.operationDefinitionUtil;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.epsilon.eol.ast2eol.util.Ast2EolUtil;
import org.eclipse.epsilon.eol.metamodel.EOLProgram;
import org.eclipse.epsilon.eol.metamodel.OperationDefinition;
import org.eclipse.epsilon.eol.metamodel.Type;
import org.eclipse.epsilon.eol.metamodel.VariableDeclarationExpression;

public class StandardLibraryOperationDefinitionContainer extends OperationDefinitionContainer{

	protected Ast2EolUtil ast2EolUtil = new Ast2EolUtil();
	public StandardLibraryOperationDefinitionContainer() {
		init();
	}
	
	public void registerOperation(String filename)
	{
		
		URL path = this.getClass().getResource(filename);
		URL absolutePath = null;
		try {
			absolutePath = FileLocator.toFileURL(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		EOLProgram program = ast2EolUtil.createEOLProgramFromPath(absolutePath.getPath());
		for(OperationDefinition operationDefinition : program.getOperations())
		{
			Type contextType = operationDefinition.getContextType(); //get the contextType
			ArrayList<Type> argTypes = new ArrayList<Type>(); //prepare argTypes
			
			for(VariableDeclarationExpression v: operationDefinition.getParameters()) //process each arg type
			{
				argTypes.add(v.getResolvedType()); //resolve and collect argument types
			}
			
			if (!containsOperation(operationDefinition.getName().getName(), contextType, argTypes)) {
				putOperation(operationDefinition);
			}
		}
	}
	
	
	public void init()
	{
		registerOperation("operationTypeAny.eol");
		registerOperation("operationTypeCollection.eol");
		registerOperation("operationTypeInteger.eol");
		registerOperation("operationTypeModelElement.eol");
		registerOperation("operationTypeReal.eol");
		registerOperation("operationTypeString.eol");
	}
	
	
	public Ast2EolUtil getAst2DomUtil()
	{
		return ast2EolUtil;
	}
	
	public static void main(String[] args) {
		//TypeResolutionContext context = TypeResolutionContext.getInstance();
		
		StandardLibraryOperationDefinitionContainer a = new StandardLibraryOperationDefinitionContainer();
		System.out.println(a.getOperations().size());
	}
	
	public ArrayList<OperationDefinition> getOperations()
	{
		return operations;
	}

}
