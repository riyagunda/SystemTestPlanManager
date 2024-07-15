/**
 * 
 */
package edu.ncsu.csc216.stp.model.manager;

import static org.junit.jupiter.api.Assertions.*;


import java.io.File;
import edu.ncsu.csc216.stp.model.tests.TestCase;

import org.junit.jupiter.api.Test;

/**
 * This JUnit test class tests the methods of the TestPlanManager class
 * @author Riya Gunda
 *
 */
class TestPlanManagerTest {

	/**
	 * Test method for {@link edu.ncsu.csc216.stp.model.manager.TestPlanManager#TestPlanManager()}.
	 */
	@Test
	void testTestPlanManager() {
		TestPlanManager tpm = new TestPlanManager();
		assertEquals(1, tpm.getTestPlanNames().length);
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.stp.model.manager.TestPlanManager#loadTestPlans(java.io.File)}.
	 */
	@Test
	void testLoadTestPlans() {
		TestPlanManager tpm = new TestPlanManager();
		File file = new File("test-files/test-plans0.txt");
		tpm.loadTestPlans(file);
		assertEquals(3, tpm.getTestPlanNames().length);
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.stp.model.manager.TestPlanManager#saveTestPlans(java.io.File)}.
	 */
	@Test
	void testSaveTestPlans() {
		
		File file = new File("test-files/tester.txt");
		
		File file1 = new File("/home/sesmith5/actual_student_records.txt");
		
		TestPlanManager tpm = new TestPlanManager();
		tpm.saveTestPlans(file);
		Exception exception = assertThrows(IllegalArgumentException.class, 
				() -> tpm.saveTestPlans(file1));
		assertEquals("Unable to save file", exception.getMessage());
		
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.stp.model.manager.TestPlanManager#isChanged()}.
	 */
	@Test
	void testIsChanged() {
		TestPlanManager tpm = new TestPlanManager();
		assertFalse(tpm.isChanged());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.stp.model.manager.TestPlanManager#addTestPlan(java.lang.String)}.
	 */
	@Test
	void testAddTestPlan() {
		TestPlanManager tpm = new TestPlanManager();
		tpm.addTestPlan("Plan 1");
		
		try {
			tpm.addTestPlan("Failing Tests");
			fail();
		} catch(IllegalArgumentException e) {
			// do nothing
		}
		
		try {
			tpm.addTestPlan("Plan 1");
			fail();
		} catch(IllegalArgumentException e) {
			// do nothing
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.stp.model.manager.TestPlanManager#getTestPlanNames()}.
	 */
	@Test
	void testGetTestPlanNames() {
		TestPlanManager tpm = new TestPlanManager();
		tpm.addTestPlan("Plan 1");
		tpm.addTestPlan("Plan 2");
		String planNames = "";
		for(int i = 0; i < tpm.getTestPlanNames().length; i++) {
			planNames += tpm.getTestPlanNames()[i];
		}
		assertEquals("Failing TestsPlan 1Plan 2", planNames);
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.stp.model.manager.TestPlanManager#setCurrentTestPlan(java.lang.String)}.
	 */
	@Test
	void testSetCurrentTestPlan() {
		TestPlanManager tpm = new TestPlanManager();
		tpm.addTestPlan("Plan 1");
		tpm.setCurrentTestPlan("Plan 1");
		assertEquals("Plan 1", tpm.getCurrentTestPlan().getTestPlanName());
		
		tpm.setCurrentTestPlan("Plan 2");
		assertEquals("Failing Tests", tpm.getCurrentTestPlan().getTestPlanName());		
	}


	/**
	 * Test method for {@link edu.ncsu.csc216.stp.model.manager.TestPlanManager#editTestPlan(java.lang.String)}.
	 */
	@Test
	void testEditTestPlan() {
		TestPlanManager tpm = new TestPlanManager();
		tpm.addTestPlan("Plan 1");
		tpm.addTestPlan("Plan 3");
		tpm.setCurrentTestPlan("Plan 1");
		tpm.editTestPlan("Plan");
		assertEquals("Plan", tpm.getCurrentTestPlan().getTestPlanName());
		
		try {
			tpm.editTestPlan("Failing Tests");
			fail();
		} catch(IllegalArgumentException e) {
			// do nothing
		}
		
		tpm.setCurrentTestPlan("Failing Tests");
		try {
			tpm.editTestPlan("Plan 2");
			fail();
		} catch(IllegalArgumentException e) {
			// do nothing
		}
		
		tpm.addTestPlan("Plan 4");
		try {
			tpm.editTestPlan("Plan 3");
			fail();
		} catch(IllegalArgumentException e) {
			// do nothing
		}		
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.stp.model.manager.TestPlanManager#removeTestPlan()}.
	 */
	@Test
	void testRemoveTestPlan() {
		TestPlanManager tpm = new TestPlanManager();
		tpm.addTestPlan("Plan 1");
		tpm.addTestPlan("Plan 2");
		tpm.addTestPlan("Plan 3");
		tpm.addTestPlan("Plan 4");
		tpm.addTestPlan("Plan 5");
		tpm.setCurrentTestPlan("Plan 1");
		tpm.removeTestPlan();
		assertEquals(5, tpm.getTestPlanNames().length);
		
		tpm.setCurrentTestPlan("Plan 4");
		tpm.removeTestPlan();
		assertEquals(4, tpm.getTestPlanNames().length);
		
		tpm.setCurrentTestPlan("Failing Tests");
		try {
			tpm.removeTestPlan();
			fail();
		} catch(IllegalArgumentException e) {
			// do nothing
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.stp.model.manager.TestPlanManager#addTestCase(edu.ncsu.csc216.stp.model.tests.TestCase)}.
	 */
	@Test
	void testAddTestCase() {
		TestPlanManager tpm = new TestPlanManager();
		tpm.addTestPlan("Plan 1");
		tpm.setCurrentTestPlan("Plan 1");
		
		TestCase tc = new TestCase("rgunda", "Pass", "This is the test case.", "Results");
		tpm.addTestCase(tc);
		assertTrue(tpm.isChanged());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.stp.model.manager.TestPlanManager#addTestResult(int, boolean, java.lang.String)}.
	 */
	@Test
	void testAddTestResult() {
		TestPlanManager tpm = new TestPlanManager();
		tpm.addTestPlan("Plan 1");
		tpm.setCurrentTestPlan("Plan 1");
		
		TestCase tc = new TestCase("rgunda", "Pass", "This is the test case.", "Results");
		tpm.addTestCase(tc);
		tpm.addTestResult(0, true, "Results 1");
		assertTrue(tc.isTestCasePassing());
		
		try {
			tpm.addTestResult(-1, true, "Results 1");
			fail();
		} catch(IllegalArgumentException e) {
			// do nothing
		}
		
		try {
			tpm.addTestResult(1, true, "Results 1");
			fail();
		} catch(IllegalArgumentException e) {
			// do nothing
		}
	}


}
