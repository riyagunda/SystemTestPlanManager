/**
 * 
 */
package edu.ncsu.csc216.stp.model.io;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.stp.model.test_plans.TestPlan;
import edu.ncsu.csc216.stp.model.util.ISortedList;
import edu.ncsu.csc216.stp.model.util.SortedList;

/**
 * This JUnit test class tests the methods of the TestPlanWriter class.
 * @author Riya Gunda
 *
 */
class TestPlanWriterTest {

	@Test
	void testValid() {
		File file = new File("test-files/test-plans0.txt");
		ISortedList<TestPlan> plans = TestPlanReader.readTestPlansFile(file);
		assertEquals("PackScheduler", plans.get(0).getTestPlanName());

		File fileWrite = new File("test-files/tester.txt");
		TestPlanWriter.writeTestPlanFile(fileWrite, plans); 
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.stp.model.io.TestPlanWriter#writeTestPlanFile(java.io.File, edu.ncsu.csc216.stp.model.util.ISortedList)}.
	 */
	@Test
	void testWriteTestPlanFileInvalid() {	
		ISortedList<TestPlan> testPlans = new SortedList<TestPlan>();
		TestPlan p1 = new TestPlan("Plan 1");
		testPlans.add(p1);
		 
		 
		File file1 = new File("/home/sesmith5/actual_student_records.txt");
		Exception exception = assertThrows(IllegalArgumentException.class, 
				() -> TestPlanWriter.writeTestPlanFile(file1, testPlans));
		assertEquals("Unable to save file", exception.getMessage());
	}

}
