/**
 * 
 */
package edu.ncsu.csc216.stp.model.tests;

import static org.junit.jupiter.api.Assertions.*;
import edu.ncsu.csc216.stp.model.test_plans.TestPlan;



import org.junit.jupiter.api.Test;

/**
 * This JUnit test class tests the methods of the TestCase class
 * @author Riya Gunda
 *
 */
class TestCaseTest {

	/**
	 * Test method for {@link edu.ncsu.csc216.stp.model.tests.TestCase#TestCase(java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	void testTestCase() {
		TestCase tc1;
		try {
			tc1 = new TestCase("", "Pass", "This is a test case.", "Results");
			fail();
		} catch(IllegalArgumentException e) {
			// do nothing
		}
		
		try {
			tc1 = new TestCase(null, "Pass", "This is a test case.", "Results");
			fail();
		} catch(IllegalArgumentException e) {
			// do nothing
		}
		
		tc1 = new TestCase("rgunda", "Pass", "This is a test case.", "Results");
		assertEquals("rgunda", tc1.getTestCaseId());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.stp.model.tests.TestCase#getTestCaseId()}.
	 */
	@Test
	void testGetTestCaseId() {
		TestCase tc = new TestCase("rgunda", "Pass", "This is a test case.", "Results");
		assertEquals("rgunda", tc.getTestCaseId());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.stp.model.tests.TestCase#getTestType()}.
	 */
	@Test
	void testGetTestType() {
		TestCase tc = new TestCase("rgunda", "Pass", "This is a test case.", "Results");
		assertEquals("Pass", tc.getTestType());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.stp.model.tests.TestCase#setTestType()}.
	 */
	@Test
	void testSetTestType() {
		TestCase tc;
		try {
			tc = new TestCase("rgunda", "", "This is a test case.", "Results");
			fail();
		} catch(IllegalArgumentException e) {
			// do nothing
		}
		
		try {
			tc = new TestCase("rgunda", null, "This is a test case.", "Results");
			fail();
		} catch(IllegalArgumentException e) {
			// do nothing
		}
		
		tc = new TestCase("rgunda", "Pass", "This is a test case.", "Results");
		assertEquals("rgunda", tc.getTestCaseId());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.stp.model.tests.TestCase#getTestDescription()}.
	 */
	@Test
	void testGetTestDescription() {
		TestCase tc = new TestCase("rgunda", "Pass", "This is a test case.", "Results");
		assertEquals("This is a test case.", tc.getTestDescription());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.stp.model.tests.TestCase#setTestDescription(java.lang.String)}.
	 */
	@Test
	void testSetTestDescription() {
		TestCase tc;
		try {
			tc = new TestCase("rgunda", "Pass", "", "Results");
			fail();
		} catch(IllegalArgumentException e) {
			// do nothing
		}
		try {
			tc = new TestCase("rgunda", "Pass", null, "Results");
			fail();
		} catch(IllegalArgumentException e) {
			// do nothing
		}
		
		tc = new TestCase("rgunda", "Pass", "This is a test case.", "Results");
		assertEquals("rgunda", tc.getTestCaseId());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.stp.model.tests.TestCase#getExpectedResults()}.
	 */
	@Test
	void testGetExpectedResults() {
		TestCase tc = new TestCase("rgunda", "Pass", "This is a test case.", "Results");
		assertEquals("Results", tc.getExpectedResults());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.stp.model.tests.TestCase#setExpectedResults(java.lang.String)}.
	 */
	@Test
	void testSetExpectedResults() {
		TestCase tc;
		try {
			tc = new TestCase("rgunda", "Pass", "This is a test case.", "");
			fail();
		} catch(IllegalArgumentException e) {
			// do nothing
		}
		try {
			tc = new TestCase("rgunda", "Pass", "This is a test case.", null);
			fail();
		} catch(IllegalArgumentException e) {
			// do nothing
		}
		tc = new TestCase("rgunda", "Pass", "This is a test case.", "Results");
		assertEquals("rgunda", tc.getTestCaseId());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.stp.model.tests.TestCase#addTestResult(boolean, java.lang.String)}.
	 */
	@Test
	void testAddTestResult() {
		TestCase tc = new TestCase("rgunda", "Pass", "This is a test case.", "Results");
		tc.addTestResult(true, "Results");
		assertEquals("- PASS: Results\n", tc.getActualResultsLog());
		tc.addTestResult(false, "Results 2");
		assertEquals("- PASS: Results\n- FAIL: Results 2\n", tc.getActualResultsLog());
		
		try {
			tc.addTestResult(true, "");
			fail();
		} catch(Exception e) {
			// do nothing
		}
		
		try {
			tc.addTestResult(true, null);
			fail();
		} catch(Exception e) {
			// do nothing
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.stp.model.tests.TestCase#isTestCasePassing()}.
	 */
	@Test
	void testIsTestCasePassing() {
		TestCase tc = new TestCase("rgunda", "Pass", "This is a test case.", "Results");
		assertFalse(tc.isTestCasePassing());
		tc.addTestResult(true, "Results");
		assertTrue(tc.isTestCasePassing());
		tc.addTestResult(false, "Results 2");
		assertEquals("- PASS: Results\n- FAIL: Results 2\n", tc.getActualResultsLog());
		
		assertFalse(tc.isTestCasePassing());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.stp.model.tests.TestCase#getStatus()}.
	 */
	@Test
	void testGetStatus() {
		TestCase tc = new TestCase("rgunda", "Pass", "This is a test case.", "Results");
		assertEquals("FAIL", tc.getStatus());
		tc.addTestResult(true, "Results");
		assertEquals("PASS", tc.getStatus());
		tc.addTestResult(false, "Results 2");
		assertEquals("FAIL", tc.getStatus());
	}


	/**
	 * Test method for {@link edu.ncsu.csc216.stp.model.tests.TestCase#setTestPlan(edu.ncsu.csc216.stp.model.test_plans.TestPlan)}.
	 */
	@Test
	void testSetTestPlan() {
		TestCase tc = new TestCase("rgunda", "Pass", "This is a test case.", "Results");
		TestPlan tp = new TestPlan("Plan 1");
		tc.setTestPlan(tp);
		assertEquals("Plan 1", tc.getTestPlan().getTestPlanName());
		try {
			tc.setTestPlan(null);
			fail();
		} catch(IllegalArgumentException e) {
			// do nothing
		}
	}


	/**
	 * Test method for {@link edu.ncsu.csc216.stp.model.tests.TestCase#toString()}.
	 */
	@Test
	void testToString() {
		TestCase tc = new TestCase("rgunda", "Pass", "This is a test case.", "Results");
		tc.addTestResult(false, "Results");
		assertEquals("# rgunda Pass\n * This is a test case.\n * - FAIL: Results\n", tc.toString());
	}

}
