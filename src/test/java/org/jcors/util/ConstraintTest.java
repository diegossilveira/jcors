package org.jcors.util;

import org.junit.Test;

public class ConstraintTest {

	private static final String ERROR_MESSAGE = "fail";

	@Test
	public void testEnsureNotNullShouldAcceptNotNullObject() {
		
		Constraint.ensureNotNull(new Object(), ERROR_MESSAGE);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testEnsureNotNullShouldRejectNullObject() {
		
		Constraint.ensureNotNull(null, ERROR_MESSAGE);
	}

	@Test
	public void testEnsureNotEmptyShouldAcceptNotEmptyString() {
		
		Constraint.ensureNotEmpty("testing", ERROR_MESSAGE);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testEnsureNotEmptyShouldRejectNullString() {
		
		Constraint.ensureNotEmpty(null, ERROR_MESSAGE);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testEnsureNotEmptyShouldRejectEmptyString() {
		
		Constraint.ensureNotEmpty("", ERROR_MESSAGE);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testEnsureNotEmptyShouldRejectBlankString() {
		
		Constraint.ensureNotEmpty("  ", ERROR_MESSAGE);
	}

	@Test
	public void testEnsureTrueShouldAcceptTrueCondition() {
		
		Constraint.ensureTrue(true, ERROR_MESSAGE);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testEnsureTrueShouldRejectFalseCondition() {
		
		Constraint.ensureTrue(false, ERROR_MESSAGE);
	}

}
