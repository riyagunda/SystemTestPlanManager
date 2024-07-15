package edu.ncsu.csc216.stp.model.tests;

/**
 * This class represents the result of a test case.
 * @author Riya Gunda
 *
 */
public class TestResult {
	/**
	 * Constant for the string "Pass"
	 */
	public static final String PASS = "PASS";
	
	/**
	 * Constant for the string "Fail"
	 */
	public static final String FAIL = "FAIL";
	
	/**
	 * Private variable for the whether the test cases passes or not
	 */
	private boolean passing;
	
	/** 
	 * Private string variable for the results from the test case
	 */
	private String actualResults;
	
	/**
	 * Constructor for this class that initializes the values for passing and actualResults
	 * @param passing if the test case passes or not
	 * @param actualResults produced by the test case
	 */	
	public TestResult(boolean passing, String actualResults) {
		setPassing(passing);
		setActualResults(actualResults);
	}
	
	/**
	 * Returns the actual results produced by the test case
	 * @return actual results
	 */
	public String getActualResults() {
		return actualResults;
	}
	
	/**
	 * Sets the value for the actualResults variable
	 * @param actualResults of the test case
	 * @throws IllegalArgumentException with the message "Invalid test results." if the parameter
	 * is null or an empty string
	 */
	private void setActualResults(String actualResults) {
		if(actualResults == null || "".equals(actualResults)) {
			throw new IllegalArgumentException("Invalid test results.");
		}
		this.actualResults = actualResults;
	}
	
	/**
	 * Returns the value of isPassing
	 * @return true if the test case passes
	 */
	public boolean isPassing() {
		return passing;
	}
	
	/**
	 * Sets the value for whether the test case is passing or not
	 * @param passing the value to set
	 */
	private void setPassing(boolean passing) {
		this.passing = passing;
	}
	
	/**
	 * Returns string representation of the classes return values
	 * @return String of fields and states
	 */
	public String toString() {
		String passer = "";
		if(passing) {
			passer = PASS;
		} else {
			passer = FAIL;
		}
		return passer + ": " + actualResults;
	}
}
