package org.eclipse.epsilon.eol.ast2eol;

import org.eclipse.epsilon.common.parse.AST;
import org.eclipse.epsilon.eol.ast2eol.context.Ast2EolContext;
import org.eclipse.epsilon.eol.metamodel.*;

public class MapTypeCreator extends TypeCreator{

	public EOLElement create(AST ast, EOLElement container,
			Ast2EolContext context) {
		MapType type = context.getEolFactory().createMapType();
		this.setAssets(ast, type, container);
		return type;
	}
	
	@Override
	public String getType() {
		return "Map";
	}
 
}
