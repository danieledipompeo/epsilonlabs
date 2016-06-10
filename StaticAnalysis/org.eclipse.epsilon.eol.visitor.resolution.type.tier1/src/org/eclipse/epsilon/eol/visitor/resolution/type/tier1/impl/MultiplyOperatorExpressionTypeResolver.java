package org.eclipse.epsilon.eol.visitor.resolution.type.tier1.impl;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.epsilon.eol.metamodel.AnyType;
import org.eclipse.epsilon.eol.metamodel.BooleanType;
import org.eclipse.epsilon.eol.metamodel.Expression;
import org.eclipse.epsilon.eol.metamodel.IntegerType;
import org.eclipse.epsilon.eol.metamodel.MultiplyOperatorExpression;
import org.eclipse.epsilon.eol.metamodel.PrimitiveType;
import org.eclipse.epsilon.eol.metamodel.RealType;
import org.eclipse.epsilon.eol.metamodel.StringType;
import org.eclipse.epsilon.eol.metamodel.Type;
import org.eclipse.epsilon.eol.metamodel.visitor.EolVisitorController;
import org.eclipse.epsilon.eol.metamodel.visitor.MultiplyOperatorExpressionVisitor;
import org.eclipse.epsilon.eol.visitor.resolution.type.tier1.context.TypeResolutionContext;

public class MultiplyOperatorExpressionTypeResolver extends MultiplyOperatorExpressionVisitor<TypeResolutionContext, Object>{

	@Override
	public Object visit(MultiplyOperatorExpression multiplyOperatorExpression,
			TypeResolutionContext context,
			EolVisitorController<TypeResolutionContext, Object> controller) {
		
		controller.visitContents(multiplyOperatorExpression, context);
		
		Expression lhs = multiplyOperatorExpression.getLhs();
		Expression rhs = multiplyOperatorExpression.getRhs();
		
		Type lhsType = lhs.getResolvedType();
		Type rhsType = rhs.getResolvedType();
		
		Type type = context.getEolFactory().createIntegerType();
		multiplyOperatorExpression.setResolvedType(type);
		context.setAssets(type, multiplyOperatorExpression);
		
		
		if(lhsType == null){
			context.getLogBook().addError(multiplyOperatorExpression.getLhs(), "Expression does not have a type");
			return null;
		}
		
		if(rhsType == null)
		{
			context.getLogBook().addError(multiplyOperatorExpression.getRhs(), "Expression does not have a type");
			return null;
		}
		
		if (lhsType instanceof AnyType) {
			context.getLogBook().addError(rhs, "Expression is of type Any");
			return null;
		}
		
		if (rhsType instanceof AnyType) {
			context.getLogBook().addError(rhs, "Expression is of type Any");
			return null;
		}

		
		if (lhsType instanceof PrimitiveType && rhsType instanceof PrimitiveType) {
			if (lhsType instanceof IntegerType && rhsType instanceof IntegerType) {
				return null;
			}
			else {
				if (lhsType instanceof StringType || rhsType instanceof StringType) {
					if(lhsType instanceof StringType)
					{
						context.getLogBook().addError(multiplyOperatorExpression.getLhs(), "Expression should be numeral");
					}
					
					if(rhsType instanceof StringType)
					{
						context.getLogBook().addError(multiplyOperatorExpression.getRhs(), "Expression should be numeral");
					}
					return null;
				}
				
				else if (lhsType instanceof BooleanType || rhsType instanceof BooleanType) {
					if(lhsType instanceof BooleanType)
					{
						context.getLogBook().addError(multiplyOperatorExpression.getLhs(), "Expression should be numeral");
					}
					
					if(rhsType instanceof BooleanType)
					{
						context.getLogBook().addError(multiplyOperatorExpression.getRhs(), "Expression should be numeral");
					}
					return null;
				}
				
				if (lhsType instanceof IntegerType && rhsType instanceof RealType) {
					type = context.getEolFactory().createRealType(); //if any is real, create a real type
				}
				
				if (rhsType instanceof RealType && rhsType instanceof IntegerType) {
					type = context.getEolFactory().createRealType(); //if any is real, create a real type
				}
				
				if (rhsType instanceof RealType && rhsType instanceof RealType) {
					type = context.getEolFactory().createRealType(); //if any is real, create a real type
				}
				
				context.setAssets(type, multiplyOperatorExpression);
				multiplyOperatorExpression.setResolvedType(type);
				return null;
			}
		}
		else {
			if (lhsType instanceof IntegerType || lhsType instanceof RealType) {
				context.getLogBook().addError(rhs, "Expression should be numeral");
				type = EcoreUtil.copy(lhsType);
				context.setAssets(type, multiplyOperatorExpression);
				multiplyOperatorExpression.setResolvedType(type);
				return null;
			}
			
			else if (rhsType instanceof IntegerType || rhsType instanceof RealType) {
				context.getLogBook().addError(lhs, "Expression should be numeral");
				type = EcoreUtil.copy(rhsType);
				context.setAssets(type, multiplyOperatorExpression);
				multiplyOperatorExpression.setResolvedType(type);
				return null;
			}
			
			if(!(lhsType instanceof PrimitiveType))
			{
				context.getLogBook().addError(multiplyOperatorExpression.getLhs(), "Expression should be numeral");
			}
			
			if(!(rhsType instanceof PrimitiveType))
			{
				context.getLogBook().addError(multiplyOperatorExpression.getRhs(), "Expression should be numeral");
			}
		}
		
		context.setAssets(type, multiplyOperatorExpression);
		multiplyOperatorExpression.setResolvedType(type);
		return null;
	}

}
