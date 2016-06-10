package org.eclipse.epsilon.etl.ast2etl;

import org.eclipse.epsilon.common.parse.AST;
import org.eclipse.epsilon.eol.ast2eol.Ast2EolContext;
import org.eclipse.epsilon.eol.ast2eol.ExpressionOrStatementBlockCreator;
import org.eclipse.epsilon.eol.metamodel.Block;
import org.eclipse.epsilon.eol.metamodel.EolElement;
import org.eclipse.epsilon.eol.metamodel.Expression;
import org.eclipse.epsilon.eol.metamodel.ExpressionOrStatementBlock;
import org.eclipse.epsilon.etl.metamodel.Guard;
import org.eclipse.epsilon.etl.parse.EtlParser;

public class GuardCreator extends EtlElementCreator{

	@Override
	public boolean appliesTo(AST ast) {
		// TODO Auto-generated method stub
		return ast.getType() == EtlParser.GUARD;
	}

	@Override
	public EolElement create(AST ast, EolElement container,
			Ast2EolContext context) {
		Ast2EtlContext _context = (Ast2EtlContext) context;
		
		Guard guard = _context.getEtlFactory().createGuard();
		
		this.setAssets(ast, guard, container);
		
		AST childAst = ast.getFirstChild();
		if (childAst!=null) {
			
			ExpressionOrStatementBlock condition = (ExpressionOrStatementBlock) _context.getEtlElementCreatorFactory().createDomElement(childAst, guard, _context, ExpressionOrStatementBlockCreator.class);
			
//			ExpressionOrStatementBlock condition = _context.getEolFactory().createExpressionOrStatementBlock();
//			if (childAst.getType() == EtlParser.BLOCK) {
//				condition.setBlock((Block)_context.getEtlElementCreatorFactory().createDomElement(childAst, condition, _context));
//				//guard.setCondition((Block)_context.getEtlElementCreatorFactory().createDomElement(childAst, guard, _context));
//			}
//			else {
//				condition.setExpression((Expression)_context.getEtlElementCreatorFactory().createDomElement(childAst, condition, _context));
////				guard.setCondition((Expression)_context.getEtlElementCreatorFactory().createDomElement(childAst, guard, _context));
//			}
			guard.setCondition(condition);
		}

		return guard;
	}

}
