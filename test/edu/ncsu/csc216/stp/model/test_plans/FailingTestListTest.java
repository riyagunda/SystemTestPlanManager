/**
 * 
 */
package edu.ncsu.csc216.stp.model.test_plans;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.stp.model.tests.TestCase;

/**
 * This JUnit test class tests the methods of the FailingTestList class
 * @author Riya Gunda
 *
 */
class FailingTestListTest {


	/**
	 * Test method for {@link edu.ncsu.csc216.stp.model.test_plans.FailingTestList#addTestCase(edu.ncsu.csc216.stp.model.tests.TestCase)}.
	 */
	@Test
	void testAddTestCase() {
		FailingTestList ftl = new FailingTestList();
		TestCase tc = new TestCase("rgunda", "Failed", "This is a test case.", "Results");
		ftl.addTestCase(tc);
		ftl.addTestResult(0, false, "tbd");
		assertEquals(1, ftl.getNumberOfFailingTests());
		assertEquals("rgunda", ftl.getTestCase(0).getTestCaseId());
		
		try {
			TestCase tc1 = new TestCase("rgunda", "Passing", "This is a test case.", "Results");
			tc1.addTestResult(true, "tbd");
			ftl.addTestCase(tc1);
			fail();
		} catch(IllegalArgumentException e) {
			// do nothing
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.stp.model.test_plans.FailingTestList#getTestCasesAsArray()}.
	 */
	@Test
	void testGetTestCasesAsArray() {
		TestCase tc = new TestCase("rgunda", "Fail", "This is a test case.", "Results");
		FailingTestList ftl = new FailingTestList();
		ftl.addTestCase(tc);
		String ret = "";
		for(int i = 0; i < ftl.getTestCasesAsArray().length; i++) {
			ret += ftl.getTestCasesAsArray()[i][0];
			ret += ftl.getTestCasesAsArray()[i][1];
			ret += ftl.getTestCasesAsArray()[i][2];
		}
		assertEquals("rgundaFail", ret);
		
		TestPlan tp = new TestPlan("Plan 1");
		tc.setTestPlan(tp);
		ret = "";
		for(int i = 0; i < ftl.getTestCasesAsArray().length; i++) {
			ret += ftl.getTestCasesAsArray()[i][0];
			ret += ftl.getTestCasesAsArray()[i][1];
			ret += ftl.getTestCasesAsArray()[i][2];
		}
		assertEquals("rgundaFailPlan 1", ret);
	}


	/**
	 * Test method for {@link edu.ncsu.csc216.stp.model.test_plans.FailingTestList#clearTests()}.
	 */
	@Test
	void testClearTests() {
		TestCase tc = new TestCase("rgunda", "Fail", "This is a test case.", "Results");
		FailingTestList ftl = new FailingTestList();
		ftl.addTestCase(tc);
		ftl.clearTests();
		assertEquals(0, ftl.getTestCases().size());
		assertEquals(0, ftl.getNumberOfFailingTests());
	}

}
