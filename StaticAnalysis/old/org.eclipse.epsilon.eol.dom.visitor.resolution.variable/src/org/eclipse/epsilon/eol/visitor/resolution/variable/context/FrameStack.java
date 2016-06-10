package org.eclipse.epsilon.eol.visitor.resolution.variable.context;

import java.util.LinkedList;

import org.eclipse.epsilon.eol.metamodel.*;

public class FrameStack {

	LinkedList<Frame> frames;
	
	public FrameStack()
	{
		frames = new LinkedList<Frame>();
	} 
	
	public void pop()
	{
		frames.getLast().dispose();
		frames.removeLast();
	}
	
	public void push(EolElement entryPoint, boolean isUnprotected)
	{
		frames.addLast(new Frame(entryPoint, isUnprotected));
	}
	
	public void addOperation(OperationDefinition operation)
	{
		//operations.add(operation);
	}
	
	public Frame getTop()
	{
		return frames.getLast();
	}
	
	public void putVariable(VariableDeclarationExpression var)
	{
		String name = var.getName().getName();
		Variable variable = new Variable(name, var);
		getTop().put(variable);
	}
	
	public boolean variableExists(String name)
	{
		boolean result = false;
		for(int i = frames.size() - 1; i >= 0; i --)
		{
			Frame frame = frames.get(i);
			if(frame.getType() == FrameType.PROTECTED)
			{
				if(frame.contains(name))
				{
					result = true;
				}
				break;
			}
			else {
				if(frame.contains(name))
				{
					result = true;
					break;
				}
			}
		}
		return result;
	}
	
	public Variable getVariable(String name)
	{
		Variable result = null;
		for(int i = frames.size()-1; i >= 0; i --)
		{
			Frame frame = frames.get(i);
			if(frame.getType() == FrameType.PROTECTED)
			{
				if(frame.contains(name))
				{
					result = frame.get(name);
				}
				break;
			}
			else {
				if(frame.contains(name))
				{
					result = frame.get(name);
					break;
				}
			}
		}
		return result;
	}
}
