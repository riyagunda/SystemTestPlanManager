/**
 * 
 */
package edu.ncsu.csc216.stp.model.test_plans;

import edu.ncsu.csc216.stp.model.tests.TestCase;

/**
 * Represents a test plan that contains a collection of test cases. This class extend AbstractTestPlan 
 * and implement Comparable
 * @author Riya Gunda
 *
 */
public class TestPlan extends AbstractTestPlan implements Comparable<TestPlan> {
	
	/** Constructs the name of the test plan 
	 * @param planName of the test plan
	 * @throws IllegalArgumentException if the name is "Failing Tests"
	 */
	public TestPlan(String planName) { 
		super(planName);
		if (planName.toLowerCase().equals(FailingTestList.FAILING_TEST_LIST_NAME.toLowerCase())) {
			throw new IllegalArgumentException("Invalid name.");
		}
	}

	/**
	 * Abstract method that return an array of the test cases in the test plan
	 * The array contains the test case id, test typ, and status
	 * @return 2D string array of test cases
	 */
	@Override
	public String[][] getTestCasesAsArray() {
		String[][] tableArr = new String[getTestCases().size()][3];
		for(int i = 0; i < getTestCases().size(); i++) {
			tableArr[i][0] = getTestCases().get(i).getTestCaseId();
			tableArr[i][1] = getTestCases().get(i).getTestType();
			tableArr[i][2] = getTestCases().get(i).getStatus();
		}
		return tableArr;
	}
	
	/**
	 * Overrides the method in the parent class so that it adds a test case to the end of the list and
	 * sets the test cases test plan to the current test plan
	 */
	@Override
	public void addTestCase(TestCase caseName) {
		super.addTestCase(caseName);
		caseName.setTestPlan(this);
	}

	/**
	 * Compares the names of the test plans
	 */
	@Override
	public int compareTo(TestPlan o) {
		return this.getTestPlanName().compareToIgnoreCase(o.getTestPlanName());
	}
}
