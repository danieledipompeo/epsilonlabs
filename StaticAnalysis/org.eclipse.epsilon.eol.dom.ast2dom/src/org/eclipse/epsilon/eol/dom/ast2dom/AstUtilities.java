package org.eclipse.epsilon.eol.dom.ast2dom;


import java.util.LinkedList;

import org.eclipse.epsilon.commons.parse.AST;

public class AstUtilities {

	public static int getChildrenCount(AST parent) {
		int count = 0;
		if (parent == null) return count;
		AST child = parent.getFirstChild();
		while (child != null){
			count++;
			child = child.getNextSibling();
		}
		return count;
	}
	
	public static AST getChildAt(AST parent, int index) {
		int count = 0;
		if (parent == null) return null;
		AST child = parent.getFirstChild();
		while (child != null){
			if (count == index) {
				return child;
			}
			else {
				child = child.getNextSibling();
				count++;
			}
		}
		return null;
	}
	
	public static LinkedList<AST> getChildren(AST parent){
		return getChildren(parent, -1);
	}
	
	public static LinkedList<AST> getChildrenBut(AST parent, int type){
		LinkedList<AST> children = new LinkedList<AST>();
		AST child = parent.getFirstChild();
		while (child != null){
			if (!(child.getType() == type)){
				children.add(child);
			}
			child = child.getNextSibling();
		}
		return children;
	}
	public static LinkedList<AST> getChildren(AST parent, int... type){
		LinkedList<AST> children = new LinkedList<AST>();
		
		if (parent != null) {
			AST child = parent.getFirstChild();
			while (child != null){
				for (int i=0;i<type.length;i++) {
					if (child.getType() == type[i] || type[i] == -1){
						children.add(child);
					}
				}
				child = child.getNextSibling();
			}
		}
		
		return children;
	}
	
	public static AST getChild(AST parent, int type){
		if (parent == null) return null;
		AST child = parent.getFirstChild();
		while (child != null){
			if (child.getType() == type){
				return child;
			}
			child = child.getNextSibling();
		}
		return null;
	}
	
	public static AST getFirstConcreteChild(AST parent){
		if (parent.getLine() > 0){
			return parent;
		}
		else {
			AST child = parent.getFirstChild();
			while (child != null){
				AST result = getFirstConcreteChild(child);
				if (result != null){
					return result;
				}
			}
			return null;
		}
	}
	
	public static int getParentType(AST child) {
		if (child.getParent() == null) return -1;
		return child.getParent().getType();
	}
}
