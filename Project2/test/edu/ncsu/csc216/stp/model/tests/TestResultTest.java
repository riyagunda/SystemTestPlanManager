/**
 * 
 */
package edu.ncsu.csc216.stp.model.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * This JUnit test class tests the methods of the TestResult class
 * @author Riya Gunda
 *
 */
class TestResultTest {

	/**
	 * Test method for {@link edu.ncsu.csc216.stp.model.tests.TestResult#TestResult(boolean, java.lang.String)}.
	 */
	@Test
	void testTestResult() {
		TestResult tr1;
		try {
			tr1 = new TestResult(true, null);
			fail();
		} catch(IllegalArgumentException e) {
			// Do nothing
		}
		
		try {
			tr1 = new TestResult(true, "");
			fail();
		} catch(IllegalArgumentException e) {
			// Do nothing
		}
		tr1 = new TestResult(true, "Results");
		assertTrue(tr1.isPassing());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.stp.model.tests.TestResult#getActualResults()}.
	 */
	@Test
	void testGetActualResults() {
		TestResult tr1 = new TestResult(true, "Results");
		assertEquals("Results", tr1.getActualResults());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.stp.model.tests.TestResult#isPassing()}.
	 */
	@Test
	void testIsPassing() {
		TestResult tr1 = new TestResult(true, "Results");
		assertTrue(tr1.isPassing());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.stp.model.tests.TestResult#toString()}.
	 */
	@Test
	void testToString() {
		TestResult tr1 = new TestResult(true, "Results");
		assertEquals("PASS: Results", tr1.toString());
		TestResult tr2 = new TestResult(false, "Results");
		assertEquals("FAIL: Results", tr2.toString());
	}

}
