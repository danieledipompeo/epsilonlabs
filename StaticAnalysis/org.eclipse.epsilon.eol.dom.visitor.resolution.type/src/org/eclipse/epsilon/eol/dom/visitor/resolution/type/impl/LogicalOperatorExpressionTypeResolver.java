package org.eclipse.epsilon.eol.dom.visitor.resolution.type.impl;

import org.eclipse.epsilon.eol.dom.AndOperatorExpression;
import org.eclipse.epsilon.eol.dom.AnyType;
import org.eclipse.epsilon.eol.dom.BinaryOperatorExpression;
import org.eclipse.epsilon.eol.dom.BooleanType;
import org.eclipse.epsilon.eol.dom.EqualsOperatorExpression;
import org.eclipse.epsilon.eol.dom.Expression;
import org.eclipse.epsilon.eol.dom.GreaterThanOperatorExpression;
import org.eclipse.epsilon.eol.dom.GreaterThanOrEqualToOperatorExpression;
import org.eclipse.epsilon.eol.dom.ImpliesOperatorExpression;
import org.eclipse.epsilon.eol.dom.IntegerType;
import org.eclipse.epsilon.eol.dom.LessThanOperatorExpression;
import org.eclipse.epsilon.eol.dom.LessThanOrEqualToOperatorExpression;
import org.eclipse.epsilon.eol.dom.ModelElementType;
import org.eclipse.epsilon.eol.dom.NotEqualsOperatorExpression;
import org.eclipse.epsilon.eol.dom.OrOperatorExpression;
import org.eclipse.epsilon.eol.dom.PrimitiveExpression;
import org.eclipse.epsilon.eol.dom.PrimitiveType;
import org.eclipse.epsilon.eol.dom.RealType;
import org.eclipse.epsilon.eol.dom.Type;
import org.eclipse.epsilon.eol.dom.XorOperatorExpression;
import org.eclipse.epsilon.eol.dom.ast2dom.AnyTypeCreator;
import org.eclipse.epsilon.eol.dom.visitor.BinaryOperatorExpressionVisitor;
import org.eclipse.epsilon.eol.dom.visitor.EolVisitorController;
import org.eclipse.epsilon.eol.dom.visitor.resolution.type.context.TypeResolutionContext;

public class LogicalOperatorExpressionTypeResolver extends BinaryOperatorExpressionVisitor<TypeResolutionContext, Object>{

	@Override
	public boolean appliesTo(BinaryOperatorExpression binaryOperatorExpression,
			TypeResolutionContext context) {
		return binaryOperatorExpression instanceof AndOperatorExpression ||
				binaryOperatorExpression instanceof OrOperatorExpression ||
				binaryOperatorExpression instanceof ImpliesOperatorExpression ||
				binaryOperatorExpression instanceof XorOperatorExpression ||
				binaryOperatorExpression instanceof GreaterThanOperatorExpression ||
				binaryOperatorExpression instanceof GreaterThanOrEqualToOperatorExpression ||
				binaryOperatorExpression instanceof EqualsOperatorExpression ||
				binaryOperatorExpression instanceof NotEqualsOperatorExpression ||
				binaryOperatorExpression instanceof LessThanOperatorExpression ||
				binaryOperatorExpression instanceof LessThanOrEqualToOperatorExpression;				
	}

	@Override
	public Object visit(BinaryOperatorExpression binaryOperatorExpression,
			TypeResolutionContext context,
			EolVisitorController<TypeResolutionContext, Object> controller) {
		controller.visitContents(binaryOperatorExpression, context);
		
		BooleanType type = context.getEolFactory().createBooleanType(); //set type first, this allows minor-error in expressions n statements
		
		if (isLogicalOperator(binaryOperatorExpression)) {
			if(binaryOperatorExpression.getLhs().getResolvedType() instanceof BooleanType)
			{
				if (binaryOperatorExpression.getRhs().getResolvedType() instanceof BooleanType) {
					
				}
				else {
					context.getLogBook().addError(binaryOperatorExpression.getRhs(), "Expression is not of type Boolean");
				}
			}
			else {
				context.getLogBook().addError(binaryOperatorExpression.getLhs(), "Expression is not of type Boolean");
				//handle lhs not boolean 
			}
		}
		else if (isComparativeOperator(binaryOperatorExpression)) {
			Expression lhs = binaryOperatorExpression.getLhs();
			Expression rhs = binaryOperatorExpression.getRhs();
			
			Type lhsType = lhs.getResolvedType();
			Type rhsType = rhs.getResolvedType();
			
			if (lhsType instanceof IntegerType || lhsType instanceof AnyType || lhsType instanceof RealType)
			{
				if (rhsType instanceof IntegerType || rhsType instanceof RealType) {
					
				}
				else {
					context.getLogBook().addError(rhs, "Expression should be of either type Integer or type Real");
				}				
			}
			else {
				context.getLogBook().addError(lhs, "Expression should be of either type Integer or type Real");
			}
		}
		else if(isEqualityComparisonOperator(binaryOperatorExpression))
		{
			Expression lhs = binaryOperatorExpression.getLhs();
			Expression rhs = binaryOperatorExpression.getRhs();
			
			Type lhsType = lhs.getResolvedType();
			Type rhsType = rhs.getResolvedType();
			
			if (lhsType instanceof PrimitiveType && rhsType instanceof PrimitiveType) {
				if (lhsType instanceof IntegerType || lhsType instanceof AnyType || lhsType instanceof RealType)
				{
					if (rhsType instanceof IntegerType || rhsType instanceof RealType) {
						
					}
					else {
						context.getLogBook().addError(rhs, "Expression should be of either type Integer or type Real");
					}				
				}
				else {
					context.getLogBook().addError(lhs, "Expression should be of either type Integer or type Real");
				}
			}
			else if (lhsType.getClass().getSimpleName().equals(rhsType.getClass().getSimpleName()) && (!(rhsType instanceof AnyType))) {
				if (!context.getTypeUtil().isEqualOrGeneric(rhsType, lhsType)) {
					context.getLogBook().addError(rhs, "type incompatibility: assigning " + rhsType.getClass().getSimpleName() + " to " + lhsType.getClass().getSimpleName());
				}
			}			
		}
		
		context.setAssets(type, binaryOperatorExpression);
		return null;
	}
	
	
	public boolean isLogicalOperator(BinaryOperatorExpression op)
	{
		boolean result = false;
		if (op instanceof AndOperatorExpression ||
				op instanceof OrOperatorExpression ||
				op instanceof ImpliesOperatorExpression ||
				op instanceof XorOperatorExpression) {
			result = true;
		}
		return result;
	}
	
	public boolean isComparativeOperator(BinaryOperatorExpression op)
	{
		boolean result = false;
		if (op instanceof GreaterThanOperatorExpression ||
				op instanceof GreaterThanOrEqualToOperatorExpression ||
				//op instanceof EqualsOperatorExpression ||
				//op instanceof NotEqualsOperatorExpression ||
				op instanceof LessThanOperatorExpression ||
				op instanceof LessThanOrEqualToOperatorExpression) {
			result = true;
		}
		return result;
	}
	
	public boolean isEqualityComparisonOperator(BinaryOperatorExpression op)
	{
		boolean result = false;
		if (op instanceof EqualsOperatorExpression ||
				op instanceof NotEqualsOperatorExpression) {
			result = true;
		}
		return result;
	}

}