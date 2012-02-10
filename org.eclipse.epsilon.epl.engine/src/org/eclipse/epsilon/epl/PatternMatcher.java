package org.eclipse.epsilon.epl;

import java.util.List;

import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.execute.Return;
import org.eclipse.epsilon.eol.execute.context.Frame;
import org.eclipse.epsilon.eol.execute.context.FrameType;
import org.eclipse.epsilon.eol.execute.context.IEolContext;
import org.eclipse.epsilon.eol.execute.context.Variable;
import org.eclipse.epsilon.epl.combinations.BoundedCombinationGenerator;
import org.eclipse.epsilon.epl.combinations.CombinationGenerator;
import org.eclipse.epsilon.epl.combinations.CompositeCombinationGenerator;
import org.eclipse.epsilon.epl.combinations.CompositeCombinationValidator;

public class PatternMatcher {
	
	protected Frame frame = null;
	
	public void match(final Pattern pattern, final IEolContext context) throws EolRuntimeException {
		
		CompositeCombinationGenerator<Object> generator = new CompositeCombinationGenerator<Object>();
		
		for (Component component : pattern.getComponents()) {
			generator.addCombinationGenerator(createCombinationGenerator(component, context));
		}
		
		
		generator.setValidator(new CompositeCombinationValidator<Object>() {
			
			@Override
			public boolean isValid(List<List<Object>> combination) {
				frame = context.getFrameStack().enter(FrameType.PROTECTED, pattern.getAst());
				boolean result = true;
				int i = 0;
				Component component = null;
				for (List<Object> values : combination) {
					component = pattern.getComponents().get(i);
					frame.put(Variable.createReadOnlyVariable(component.getName(), getVariableValue(values, component)));
					i++;
				}
				if (component.getGuard() != null) {
					Return ret = null;
					try {
						ret = (Return) context.getExecutorFactory().executeBlockOrExpressionAst(component.getGuard().getAst().getFirstChild(), context);
						if (ret.getValue() instanceof Boolean) result = (Boolean) ret.getValue();
					} catch (EolRuntimeException e) {
						e.printStackTrace();
						result = false;
					}
				}
				context.getFrameStack().leave(pattern.getAst());
				return result;
			}
		});
		
		List<List<Object>> candidate = generator.getNext();
		while (candidate != null) {
			
			if (pattern.getMatchAst() != null) {
			
				frame = context.getFrameStack().enter(FrameType.PROTECTED, pattern.getAst());
				
				int i = 0;
				for (Component component : pattern.getComponents()) {
					frame.put(Variable.createReadOnlyVariable(component.getName(), getVariableValue(candidate.get(i), component)));
					i++;
				}
				
				Object result = context.getExecutorFactory().executeAST(pattern.getMatchAst(), context);
				if (result instanceof Boolean) {
					if ((Boolean)result == true) context.getExecutorFactory().executeAST(pattern.getDoAst(), context);
					else context.getExecutorFactory().executeAST(pattern.getNoMatchAst(), context);
				}
				
				context.getFrameStack().leave(pattern.getAst());
			}
			
			candidate = generator.getNext();
		}
		
	}
	
	protected Object getVariableValue(List<Object> combination, Component component) {
		if (component.getCardinality().isOne()) {
			if (combination.size() > 0) return combination.get(0);
			else return null;
		}
		else {
			return combination;
		}
	}
	
	protected CombinationGenerator<Object> createCombinationGenerator(Component component, IEolContext context) throws EolRuntimeException {
		Cardinality cardinality = component.getCardinality();
		CombinationGenerator<Object> combinationGenerator = null;
		combinationGenerator = new BoundedCombinationGenerator<Object>(component.getInstances(context), cardinality.getLowerBound(), cardinality.getUpperBound(), true);
		return combinationGenerator;
	}
	
}
