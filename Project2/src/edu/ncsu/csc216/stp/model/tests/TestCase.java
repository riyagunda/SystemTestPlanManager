/**
 * 
 */
package edu.ncsu.csc216.stp.model.tests;

import edu.ncsu.csc216.stp.model.test_plans.TestPlan;

import edu.ncsu.csc216.stp.model.util.Log;
/**
 * This class represents and initializes a test plan
 * @author Riya Gunda
 *
 */
public class TestCase {
	
	/** Private variable for the id of the test case */
	private String testCaseId;
	
	/** Private variable for the type of test case */
	private String testType;
	
	/** Private variable for the description for the test */
	private String testDescription;
	
	/** Private variable for the expected results for the test */
	private String expectedResults;
	
	/** Private variable for the test plan this test case is to be added to */
	private TestPlan testPlan;
	
	/** Private list for the results of test cases */
	private Log<TestResult> testResults;
	
	/**
	 * This constructor initializes the values for test case's id, type, description and expected 
	 * results
	 * @param id of the test case
	 * @param type of the test case
	 * @param description of the test case
	 * @param expectedResults of the test case
	 */
	public TestCase(String id, String type, String description, String expectedResults) {
		testResults = new Log<TestResult>();
		setTestCaseId(id);
		setTestType(type);
		setTestDescription(description);
		setExpectedResults(expectedResults);
	}
	
	/**
	 * Returns the id of test case
	 * @return test case id
	 */
	public String getTestCaseId() {
		return testCaseId;
	}
	
	/**
	 * Sets the id of the test case
	 * @param testCaseId id for the test case
	 * @throws IllegalArgumentException with the message "Invalid test information." if the parameter
	 * is null or empty
	 */
	private void setTestCaseId(String testCaseId) {
		if(testCaseId == null || "".equals(testCaseId)) {
			throw new IllegalArgumentException("Invalid test information.");
		}
		this.testCaseId = testCaseId;
	}
	
	/**
	 * Returns the type of test case
	 * @return test case type
	 */
	public String getTestType() {
		return testType;
	}
	
	/**
	 * Sets the type of the test case
	 * @param testCasetype type for the test case
	 * @throws IllegalArgumentException with the message "Invalid test information." if the parameter
	 * is null or empty
	 */
	public void setTestType(String testCasetype) {
		if(testCasetype == null || "".equals(testCasetype)) {
			throw new IllegalArgumentException("Invalid test information.");
		}
		this.testType = testCasetype;
	}
	
	/**
	 * Returns the description of test case
	 * @return test case description
	 */
	public String getTestDescription() {
		return testDescription;
	}
	
	/**
	 * Sets the description of the test case
	 * @param testDescription description for the test case
	 * @throws IllegalArgumentException with the message "Invalid test information." if the parameter
	 * is null or empty
	 */
	public void setTestDescription(String testDescription) {
		if(testDescription == null || "".equals(testDescription)) {
			throw new IllegalArgumentException("Invalid test information.");
		}
		this.testDescription = testDescription;
	}
	
	/**
	 * Returns the expected results of test case
	 * @return test case expected results
	 */
	public String getExpectedResults() {
		return expectedResults;
	}
	
	/**
	 * Sets the expected results of the test case
	 * @param expectedResults expected results for the test case
	 * @throws IllegalArgumentException with the message "Invalid test information." if the parameter
	 * is null or empty
	 */
	public void setExpectedResults(String expectedResults) {
		if(expectedResults == null || "".equals(expectedResults)) {
			throw new IllegalArgumentException("Invalid test information.");
		}
		this.expectedResults = expectedResults;
	}
	
	/**
	 * Creates a test result with the given parameters and adds it to the end of the testResults.
	 * @param result of the test
	 * @param expectedResults of the test
	 * @throws IllegalArgumentException if the test result cannot be constructed
	 */
	public void addTestResult(boolean result, String expectedResults) {
		try {
			TestResult tr = new TestResult(result, expectedResults);
			testResults.add(tr);
		} catch (Exception e) {
			throw new IllegalArgumentException("Invalid test results.");
		}
	}
	
	/**
	 * Returns true if the last TestResult in the Log is passing and if the TestResults Log 
	 * is empty, false is returned.
	 * @return true if the case passes
	 */
	public boolean isTestCasePassing() {
		if(testResults.size() == 0) {
			return false;
		}
		int size = testResults.size() - 1;
		return testResults.get(size).isPassing();

	}
	
	/**
	 * Returns the status of the test case as PASS or FAIL
	 * @return the status
	 */
	public String getStatus() {
		if (testResults.size() == 0) {
	        return TestResult.FAIL;
	    } 
		return testResults.get(testResults.size() - 1).isPassing() ? TestResult.PASS : TestResult.FAIL;
	}
	
	/**
	 * Returns the string representation of the test case results using toString()
	 * @return results of the test
	 */
	public String getActualResultsLog() {
		String results = "";
		for(int i = 0; i < testResults.size(); i++) {
			results += "- " + testResults.get(i).toString() + "\n";
		}
		return results;
	}
	
	/**
	 * Sets the current test plan to the given test plan
	 * @param t the test plan to shift to
	 * @throws IllegalArgumentException with the message "Invalid test plan." if the parameter is
	 * null
	 */
	public void setTestPlan(TestPlan t) {
		if(t == null) {
			throw new IllegalArgumentException("Invalid test plan.");
		}
		testPlan = t;
	}
	
	/**
	 * Returns the current test plan
	 * @return current test plan
	 */
	public TestPlan getTestPlan() {
		return testPlan;
	}
	
	/**
	 * Concatinates the given fields and states into string format
	 * @return String form of the class
	 */
	public String toString() {
		return "# " + testCaseId + " " + testType + "\n * " + testDescription + "\n * " + getActualResultsLog();   
	
	}
}
