/*******************************************************************************
 * Copyright (c) 2011-2015 The University of York.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Konstantinos Barmpis - initial API and implementation
 ******************************************************************************/
package org.eclipse.epsilon.labs.indexed.emf;

import java.util.HashSet;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.epsilon.eol.execute.operations.AbstractOperation;
import org.eclipse.epsilon.eol.execute.operations.declarative.IAbstractOperationContributor;
import org.eclipse.epsilon.eol.models.IModel;
import org.eclipse.epsilon.eol.models.IModelElement;
import org.eclipse.epsilon.eol.models.Model;

public class OptimisableCollection extends HashSet<EObject> implements
		IAbstractOperationContributor, IModelElement {

	protected Model model;
	protected EClass type = null;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OptimisableCollection(Model m, EClass t) {

		model = m;

		if (type == null)
			type = t;

	}

	@Override
	public AbstractOperation getAbstractOperation(String name) {
		if ("select".equals(name)) {
			return new OptimisableCollectionSelectOperation();
		} else
			return null;
	}

	@Override
	public IModel getOwningModel() {
		return model;
	}

}
