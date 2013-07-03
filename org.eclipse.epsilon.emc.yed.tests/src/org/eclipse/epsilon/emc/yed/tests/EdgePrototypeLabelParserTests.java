package org.eclipse.epsilon.emc.yed.tests;

import static org.junit.Assert.*;

import org.eclipse.epsilon.emc.yed.EdgePrototypeLabelParser;
import org.eclipse.epsilon.emc.yed.SlotPrototype;
import org.junit.Test;

public class EdgePrototypeLabelParserTests {
	
	@Test
	public void testSimpleName() {
		SlotPrototype prototype = parse("field");
		assertEquals("field", prototype.getName());
		assertEquals(false, prototype.isMany());
	}
	
	@Test
	public void testNameAndMultiplicity() {
		SlotPrototype prototype = parse("fields *");
		assertEquals("fields", prototype.getName());
		assertEquals(true, prototype.isMany());
	}
	
	public SlotPrototype parse(String label) {
		return new EdgePrototypeLabelParser(label).getPrototype();
	}
	
}
