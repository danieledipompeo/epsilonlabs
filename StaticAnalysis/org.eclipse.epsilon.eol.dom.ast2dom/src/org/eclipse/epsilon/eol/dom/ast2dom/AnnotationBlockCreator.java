package org.eclipse.epsilon.eol.dom.ast2dom;

import org.eclipse.epsilon.common.parse.AST;
import org.eclipse.epsilon.eol.metamodel.*;
import org.eclipse.epsilon.eol.parse.EolParser;

public class AnnotationBlockCreator extends EolElementCreator{

	@Override
	public EolElement create(AST ast, EolElement container,
			Ast2DomContext context) {
		AnnotationBlock block = context.getEolFactory().createAnnotationBlock(); //create a AnnotationBlock
		this.setAssets(ast, block, container);
		
		for(AST child: ast.getChildren())
		{
			addAnnotation(child, block, context); //process children
		}
		
		return block;
	}
	
	public void addAnnotation(AST ast, AnnotationBlock block, Ast2DomContext context)
	{
		if(ast.getType() == EolParser.Annotation) //if the ast is of simple annotation
		{
			block.getSimpleAnnotations().add((SimpleAnnotation) context.getEolElementCreatorFactory().createDomElement(ast, block, context)); //process simple annotation
		}
		else if(ast.getType() == EolParser.EXECUTABLEANNOTATION) //if the ast is of executable annotation
		{
			block.getExecutableAnnotations().add((ExecutableAnnotation) context.getEolElementCreatorFactory().createDomElement(ast, block, context)); //process executable annotation
		}
	}

	@Override
	public boolean appliesTo(AST ast) {
		if(ast.getType() == EolParser.ANNOTATIONBLOCK)
		{
			return true;
		}
		else {
			return false;
		}
	}

}
