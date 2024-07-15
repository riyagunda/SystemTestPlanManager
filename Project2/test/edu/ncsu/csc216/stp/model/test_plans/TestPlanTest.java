/**
 * 
 */
package edu.ncsu.csc216.stp.model.test_plans;

import static org.junit.jupiter.api.Assertions.*;
import edu.ncsu.csc216.stp.model.tests.TestCase;


import org.junit.jupiter.api.Test;

/**
 * This JUnit test class tests the methods of the TestPlan class
 * @author Riya Gunda
 *
 */
class TestPlanTest {
	/**
	 * Test method for {@link edu.ncsu.csc216.stp.model.test_plans.TestPlan#TestPlan(java.lang.String)}.
	 */
	@Test
	void testTestPlan() {
		TestPlan tp1;
		try {
			tp1 = new TestPlan("");
			fail();
		} catch(IllegalArgumentException e) {
			// do nothing
		}
		
		try {
			tp1 = new TestPlan(null);
			fail();
		} catch(IllegalArgumentException e) {
			// do nothing
		}
		
		try {
			tp1 = new TestPlan("Failing Tests");
			fail();
		} catch(IllegalArgumentException e) {
			// do nothing
		}
		
		tp1 = new TestPlan("Plan 1");
		assertEquals("Plan 1", tp1.getTestPlanName());
	}
	
	/**
	 * Test method for {@link edu.ncsu.csc216.stp.model.test_plans.TestPlan#addTestCase(edu.ncsu.csc216.stp.model.tests.TestCase)}.
	 */
	@Test
	void testAddTestCase() {
		TestCase tc = new TestCase("rgunda", "Pass", "This is a test case.", "Results");
		TestPlan tp = new TestPlan("Plan 1");
		tp.addTestCase(tc);
		assertEquals("Plan 1", tc.getTestPlan().getTestPlanName());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.stp.model.test_plans.TestPlan#getTestCasesAsArray()}.
	 */
	@Test
	void testGetTestCasesAsArray() {
		TestCase tc = new TestCase("rgunda", "Pass", "This is a test case.", "Results");
		TestPlan tp = new TestPlan("Plan 1");
		tp.addTestCase(tc);
		String ret = "";
		for(int i = 0; i < tp.getTestCasesAsArray().length; i++) {
			ret += tp.getTestCasesAsArray()[i][0];
			ret += tp.getTestCasesAsArray()[i][1];
			ret += tp.getTestCasesAsArray()[i][2];
		}
		assertEquals("rgundaPassFAIL", ret);
	}


	/**
	 * Test method for {@link edu.ncsu.csc216.stp.model.test_plans.TestPlan#compareTo(edu.ncsu.csc216.stp.model.test_plans.TestPlan)}.
	 */
	@Test
	void testCompareTo() {
		TestPlan tp = new TestPlan("Plan 1");
		TestPlan tp1 = new TestPlan("Shopping test plan");
		assertEquals(-3, tp.compareTo(tp1));
	}

	@Test
	void testAbstractRemove() {
		TestPlan tp = new TestPlan("Plan 1");
		TestCase tc = new TestCase("rgunda", "Pass", "This is a test case.", "Results");
		TestCase tc1 = new TestCase("rgunda", "Fail", "This is test case 2.", "Results 2");
		TestCase tc2 = new TestCase("rgunda", "Pass", "This is test case 3.", "Results 3");
		tp.addTestCase(tc);
		tp.addTestCase(tc1);
		tp.addTestCase(tc2);
		tp.removeTestCase(0);
		assertEquals("# rgunda Fail\n * This is test case 2.\n * ", tp.getTestCase(0).toString());
	}
	
	@Test
	void testAbstractTestPlanAddTestResult() {
		TestPlan tp = new TestPlan("Plan 1");
		TestCase tc = new TestCase("rgunda", "Pass", "This is a test case.", "Results");
		tp.addTestCase(tc);
		tp.addTestResult(0, true, "Results from the test case");
		assertTrue(tc.isTestCasePassing());
	}
}
