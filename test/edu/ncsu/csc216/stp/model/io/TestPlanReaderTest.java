/**
 * 
 */
package edu.ncsu.csc216.stp.model.io;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.io.File;

import edu.ncsu.csc216.stp.model.test_plans.TestPlan;
import edu.ncsu.csc216.stp.model.util.ISortedList;

/**
 * This JUnit test class tests the methods of the TestPlanReader class.
 * @author Riya Gunda
 *
 */
class TestPlanReaderTest {

	/**
	 * Test method to test readTestPlansFile() method
	 */
	@Test
	void testReadTestPlansFile() {
		File file = new File("test-files/test-plans0.txt");
		ISortedList<TestPlan> plans = TestPlanReader.readTestPlansFile(file);
		assertEquals("PackScheduler", plans.get(0).getTestPlanName());

		File fileWrite = new File("test-files/tester.txt");
		TestPlanWriter.writeTestPlanFile(fileWrite, plans); 	
	}

	@Test
	void testReadTestPlansFileInvalid() {
		ISortedList<TestPlan> plans;
		try {
			File file = new File("test-files/test-plans100.txt");
			plans = TestPlanReader.readTestPlansFile(file);
			assertEquals(null, plans.get(0));
			fail();
		} catch(IllegalArgumentException e) {
			// do nothing
		}
	}
}
