/*******************************************************************************
 * Copyright (c) 2014 The University of York.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Louis Rose - initial API and implementation
 ******************************************************************************/
package org.eclipse.epsilon.emc.web.test.acceptance;

import org.eclipse.epsilon.eol.exceptions.models.EolModelElementTypeNotFoundException;
import org.junit.Test;

public class Basic extends WebModelAcceptanceTest {

	@Test
	public void hasCorrectNumberOfElements() throws EolModelElementTypeNotFoundException {
		model.assertEquals(4, "Module.all.size()");
	}
	
	@Test
	public void elementsHaveCorrectNames() throws Throwable {
		model.setVariable("names", "Module.all.fullname");
		
		model.assertTrue("names.includes('ADVT - Advanced Topics in Interactive Technologies')");
		model.assertTrue("names.includes('FESC - Foundation in Electronics, Signals & Circuits')");
		model.assertTrue("names.includes('SWIN - Swarm Intelligence')");
		model.assertTrue("names.includes('TPOP - Theory & Practice of Programming')");
	}
	
}
