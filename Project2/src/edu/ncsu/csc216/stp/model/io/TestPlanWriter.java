
package edu.ncsu.csc216.stp.model.io;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.PrintStream;

import edu.ncsu.csc216.stp.model.test_plans.TestPlan;
import edu.ncsu.csc216.stp.model.util.ISortedList;

/**
 * This class writes the given information into a file in proper format
 * @author Riya Gunda
 *
 */
public class TestPlanWriter {
	
	
	/**
	 * This method writes the contents of the given list to the given file.
	 * @param fileName of the file to be written to
	 * @param testPlanList list of test plans to be written
	 * @throws IllegalArgumentException with the message "Unable to save file." if the file cannot
	 * be saved
	 */
	public static void writeTestPlanFile(File fileName, ISortedList<TestPlan> testPlanList) {
		try {
			PrintStream fileWriter = new PrintStream(fileName);
			for (int i = 0; i < testPlanList.size(); i++) {
				TestPlan testPlan = testPlanList.get(i);
				fileWriter.println("! " + testPlan.getTestPlanName());

				for (int j = 0; j < testPlan.getTestCases().size(); j++) {
					fileWriter.println("# " + testPlan.getTestCases().get(j).getTestCaseId() + ","
							+ testPlan.getTestCases().get(j).getTestType());
					fileWriter.println("* " + testPlan.getTestCases().get(j).getTestDescription());
					fileWriter.println("* " + testPlan.getTestCases().get(j).getExpectedResults());
					fileWriter.println("- " + "PASS: actual results"); // CHANGE THIS
				}
			}
			fileWriter.close();
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to save file");
		}
	}
}
