/**
 * 
 */
package edu.ncsu.csc216.stp.model.test_plans;


import edu.ncsu.csc216.stp.model.tests.TestCase;
import edu.ncsu.csc216.stp.model.util.ISwapList;
import edu.ncsu.csc216.stp.model.util.SwapList;

/**
 * This class represents a test plan in the system. (FINISH)
 * @author Riya Gunda
 *
 */
public abstract class AbstractTestPlan {
	/** Private variable for the name of the test plan */
	private String testPlanName;
	
	/** Private instance of test cases */
	private ISwapList<TestCase> testCases;
	
	
	/**
	 * Parameterless constructor for this class. Sets the value for testPlanName and constructs
	 * a SwapList for the given test cases.
	 * @param testPlanName name of the test plan
	 * @throws IllegalArgumentException with the message "Invalid name." if the name is null or empty
	 */
	@SuppressWarnings("unchecked")
	public AbstractTestPlan(String testPlanName) {
		testCases = new SwapList<>();
		setTestPlanName(testPlanName);
	}
	
	/**
	 * This method sets the name for the test plan
	 * @param planName for the test plan of the system
	 * @throws IllegalArgumentException with the message "Invalid name." if the name is null or empty
	 */
	public void setTestPlanName(String planName) {
		if(planName == null || "".equals(planName)) {
			throw new IllegalArgumentException("Invalid name.");
		}
		this.testPlanName = planName;
	}
	
	/**
	 * Returns the name of the current test plan
	 * @return name of the test plan
	 */
	public String getTestPlanName() {
		return testPlanName;
	}
	
	/**
	 * Returns a list of the test cases for the current test plan
	 * @return list of test cases
	 */
	public ISwapList<TestCase> getTestCases() {
		return testCases;
	}
	
	/**
	 * Adds the given tets case to the end of the list
	 * @param t test case to be added to the list
	 * @throws NullPointerException if element is null
	 * @throws IllegalArgumentException if element cannot be added 
	 */
	public void addTestCase(TestCase t) {
		testCases.add(t);
	}
	
	/**
	 * Removes the test case in the list with the given id
	 * @param id of the test case to be removed
	 * @return the test case that was just removed
	 * @throws IndexOutOfBoundsException if the idx is out of bounds
	 * 		for the list
	 */
	public TestCase removeTestCase(int id) {
		return testCases.remove(id);
	}
	
	/**
	 * Returns the test case in the list with the given id
	 * @param id of the test case to return
	 * @return the test case with the given id
	 * @throws IndexOutOfBoundsException if the idx is out of bounds
	 * 		for the list
	 */
	public TestCase getTestCase(int id) {
		return testCases.get(id);
	}
	
	/**
	 * Counts and returns the number of failing test cases
	 * @return number of failing test cases
	 */
	public int getNumberOfFailingTests() {
		int failing = 0;
		for(int i = 0; i < testCases.size(); i++) {
			if(testCases.get(i).getStatus().equals("FAIL")) {
				failing++;
			}
		}
		return failing;
	}
	
	/**
	 * Sends the test result to the test result parameter in the TestCase at the given index
	 * @param id of the test case
	 * @param result of the test case
	 * @param name of the test case
	 * @throws IndexOutOfBoundsException if the idx is out of bounds
	 * 		for the list
	 * @throws IllegalArgumentException with the message "Invalid name." if the name is null or empty //TODO
	 */
	public void addTestResult(int id, boolean result, String name) {
		testCases.get(id).addTestResult(result, name);
	}
	
	/**
	 * Abstract method implemented in the sub-classes to return an array of the test cases in the
	 * test plan
	 * @return 2D string array of test cases
	 */
	public abstract String[][] getTestCasesAsArray();

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((testCases == null) ? 0 : testCases.hashCode());
		result = prime * result + ((testPlanName == null) ? 0 : testPlanName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		TestPlan temp = (TestPlan) obj;
		return temp.getTestPlanName().toLowerCase().equals(this.getTestPlanName().toLowerCase());
	}

	
	
}
