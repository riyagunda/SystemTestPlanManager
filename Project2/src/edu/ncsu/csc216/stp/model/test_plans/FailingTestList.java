/**
 * 
 */
package edu.ncsu.csc216.stp.model.test_plans;

import edu.ncsu.csc216.stp.model.tests.TestCase;
import edu.ncsu.csc216.stp.model.util.ISwapList;

/**
 * Represents the failing test cases in a test plan
 * @author Riya Gunda
 *
 */
public class FailingTestList extends AbstractTestPlan {
	/** Constant for the name of the list of failing tests */
	public static final String FAILING_TEST_LIST_NAME = "Failing Tests";
	
	/**
	 * Constructs the FailingTestList with the above constant as the name
	 */
	public FailingTestList() {
		super(FAILING_TEST_LIST_NAME);
	}
	
	/**
	 * Overrides the method in the parent class to check if the test case is failing before
	 * it is added to the list
	 * @param t test case to be added
	 * @throws IllegalArgumentException with the message "Cannot add passing test case." if the test case
	 * is not failing
	 */
	@Override
	public void addTestCase(TestCase t) {
		if(t.getStatus().equals("PASS")) {
			throw new IllegalArgumentException("Cannot add passing test case.");
		}
		
		super.addTestCase(t);
	}

	/**
	 * Overrides the methos in the parent class to make sure the name matches the expected name of the 
	 * test plan. The name is set to the constant value if the names match.
	 * @param planName to change to
	 * @throws IllegalArgumentException with the message "The Failing Tests list cannot be edited." if 
	 * the names do not match
	 */
	@Override
	public void setTestPlanName(String planName) {
		if(planName.toLowerCase().equals(FAILING_TEST_LIST_NAME.toLowerCase())) {
			super.setTestPlanName(planName);
		} else {
			throw new IllegalArgumentException("The Failing Tests list cannot be edited.");
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
			if(getTestCases().get(i).getTestPlan() == null) {
				tableArr[i][2] = "";
			} else {
				tableArr[i][2] = getTestCases().get(i).getTestPlan().getTestPlanName();
			}
		}
		return tableArr;
	}
	
	/**
	 * Clears the list of failing tests
	 */
	public void clearTests() {
		ISwapList<TestCase> testCases = getTestCases();
	    for (int i = testCases.size() - 1; i >= 0; i--) {
	        testCases.remove(i);
	    }
	}
}
