package org.eclipse.epsilon.eol.analysis.optimisation.loading.impl;

import metamodel.connectivity.abstractmodel.EMetamodelDriver;

import org.eclipse.epsilon.eol.analysis.optimisation.loading.context.EffectiveMetamodel;
import org.eclipse.epsilon.eol.analysis.optimisation.loading.context.EffectiveType;
import org.eclipse.epsilon.eol.analysis.optimisation.loading.context.LoadingOptimisationAnalysisContext;
import org.eclipse.epsilon.eol.metamodel.Expression;
import org.eclipse.epsilon.eol.metamodel.MethodCallExpression;
import org.eclipse.epsilon.eol.metamodel.ModelElementType;
import org.eclipse.epsilon.eol.metamodel.NameExpression;
import org.eclipse.epsilon.eol.metamodel.visitor.EolVisitorController;
import org.eclipse.epsilon.eol.metamodel.visitor.MethodCallExpressionVisitor;
import org.eclipse.epsilon.eol.visitor.resolution.type.tier1.context.TypeResolutionContext;

public class MethodCallExpressionLoadingOptimisationAnalyser extends MethodCallExpressionVisitor<TypeResolutionContext, Object>{

	@Override
	public Object visit(MethodCallExpression methodCallExpression,
			TypeResolutionContext context,
			EolVisitorController<TypeResolutionContext, Object> controller) {
				
		//visit the contents first
		controller.visitContents(methodCallExpression, context);
		
		//get the method name
		String methodString = methodCallExpression.getMethod().getName();
		
		//get the target
		Expression target = methodCallExpression.getTarget();
		
		//get the context
		LoadingOptimisationAnalysisContext leContext = (LoadingOptimisationAnalysisContext) context;
		
		//if target is name expression
		if (target instanceof NameExpression) {
			
			//get the target
			NameExpression nameExpression = (NameExpression) target;
			
			//if resolved type is model element type;
			if (target.getResolvedType() instanceof ModelElementType) {
				
				//get the target type
				ModelElementType targetType = (ModelElementType) target.getResolvedType();
				
				//get the model string
				String modelString = targetType.getModelName();
				
				//get the element string
				String elementString = targetType.getElementName();
				
				//get the driver
				EMetamodelDriver driver = context.getMetaModel(modelString);
				
				
				//if there is no resolved content, it should be a Model Element name
				if (nameExpression.getResolvedContent() == null) {
					
					//if property is keyword(all or allInstances)
					if (isKeyword(methodString)) {
						
						//get the target name
						String targetString = nameExpression.getName();
						
						//if target contains "!" get the type name only
						if (targetString.contains("!")) {
							targetString = targetString.substring(targetString.indexOf("!")+1, targetString.length());
						}
						
						//if driver is not null
						if (driver != null) {
							
							//if driver contains the type
							if (driver.containsMetaClass(elementString)) {
								
								//add the effective metamodel
								EffectiveMetamodel effectiveMetamodel = leContext.addEffectiveMetamodel(modelString);
								
								EffectiveType effectiveType = null;
								
								if (methodString.equals("allOfType")) {
									//add the effective type
									effectiveType = effectiveMetamodel.addToAllOfType(targetString);
								}
								else {
									//add the effective type
									effectiveType = effectiveMetamodel.addToAllOfKind(targetString);
								}
								
								//register effectiveType
								leContext.registerEffectiveTypeWithObject(methodCallExpression, effectiveType);
							}
						}
					}
				}
				else {
					//this should happen when user creates dynamic EClasses, we are not interested in these situations
				}
			}
		}
		
		return null;
	}
	
	public boolean isKeyword(String s)
	{
		if (s.equals("all") ||
				s.equals("allOfType") ||
				s.equals("allOfKind") ||
				s.equals("allInstances")) {
			return true;
		}
		else {
			return false;
		}
		
	}


}
