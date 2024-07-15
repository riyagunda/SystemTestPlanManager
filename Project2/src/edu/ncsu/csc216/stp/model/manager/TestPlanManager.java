/**
 * 
 */
package edu.ncsu.csc216.stp.model.manager;

import java.io.File;

import edu.ncsu.csc216.stp.model.tests.TestCase;
import edu.ncsu.csc216.stp.model.util.ISortedList;
import edu.ncsu.csc216.stp.model.util.SortedList;
import edu.ncsu.csc216.stp.model.io.TestPlanReader;
import edu.ncsu.csc216.stp.model.io.TestPlanWriter;
import edu.ncsu.csc216.stp.model.test_plans.AbstractTestPlan;
import edu.ncsu.csc216.stp.model.test_plans.FailingTestList;
import edu.ncsu.csc216.stp.model.test_plans.TestPlan;

/**
 * This class manages the functions performed on and by a test plan
 * @author Riya Gunda
 *
 */
public class TestPlanManager {
	/** Private boolean variable for keeping track of updates made to the test plan */
	private boolean isChanged;
	
	/** Private instance of AbstractTestPlan for the current test plan */
	private AbstractTestPlan currentTestPlan;
	
	/** Private instance of FailingTestList */
	private FailingTestList failingTestList;
	
	/** Private list of test plans */
	private ISortedList<TestPlan> testPlans;
	
	/** Parameterless constructor for this class 
	 * testPlans field is constructed as a SortedList, the failingTestList is constructed 
	 * and is set as the currentTestPlan. isChanged is initialized to false
	 * */
	public TestPlanManager() {
		clearTestPlans();
	} 
	
	/**
	 * This class loads all the test plans and their respective test cases from a file by calling
	 * the TestPlanReader class's methods and adds them to the list of test plans if it does not
	 * already exist in the list. The current test plan is then set to failingTestList.
	 * @param fileName of the file to be read from
	 */
	public void loadTestPlans(File fileName) {
		ISortedList<TestPlan> temp = TestPlanReader.readTestPlansFile(fileName);
		setCurrentTestPlan("Failing Tests");
		
		for(int i = 0; i < temp.size(); i++) {
			testPlans.add(temp.get(i));
		}
		
		isChanged = true;
	}
	
	/**
	 * This class saves all the test plans and their respective test cases to the given file by
	 * calling the TestPlanWriter class's methods. isChanged is updated to false.
	 * @param fileName of the file to be saved
	 */
	public void saveTestPlans(File fileName) {
		TestPlanWriter.writeTestPlanFile(fileName, testPlans);
		isChanged = false;
	}
	
	/**
	 * Updates the isChanged variable based on the updates made to the test plan
	 * @return true if the test plan has had changes made
	 */
	public boolean isChanged() {
		return isChanged;
	}
	
	/**
	 * Adds a testPlan with the given name to the list of test plans and updates the current test plane
	 * to the test plan that was just added
	 * @param planName for the test plan to be added
	 * @throws IllegalArgumentException with the message "Invalid name." if the test plans name is a
	 * duplicate of a plan that already exists or if it is "Failing Tests"
	 */
	public void addTestPlan(String planName) {
		if (planName.toLowerCase().equals(FailingTestList.FAILING_TEST_LIST_NAME.toLowerCase())) {
			throw new IllegalArgumentException("Invalid name.");
		}
		
		for(int i = 0; i < testPlans.size(); i++) {
			String temp = testPlans.get(i).getTestPlanName().toLowerCase();
			if(temp.equals(planName.toLowerCase())) {
				throw new IllegalArgumentException("Invalid name.");
			}
		}
		
		TestPlan newPlan = new TestPlan(planName);
		int curSize = currentTestPlan.getTestCases().size();
		testPlans.add(newPlan);
		currentTestPlan = newPlan;
		
		if(curSize + 1 == currentTestPlan.getTestCases().size()) {
			isChanged = true;
			getFailingTests();
		}
	}
	
	/**
	 * Returns an array of the names of the test plans with the failing ones at the top
	 * @return an array of Strings
	 */
	public String[] getTestPlanNames() {
		String[] names = new String[testPlans.size() + 1];
		names[0] = failingTestList.getTestPlanName();
		for(int i = 1; i <= testPlans.size(); i++) {
			names[i] = testPlans.get(i - 1).getTestPlanName();
		}
		return names;
	}
	
	/**
	 * Private helper method to maintain the order of the test cases
	 */
	private void getFailingTests() {
		failingTestList.clearTests();
		for (int i = 0; i < testPlans.size(); i++) {
	        for (int j = 0; j < testPlans.get(i).getTestCases().size(); j++) {
	            if (!testPlans.get(i).getTestCases().get(j).isTestCasePassing()) {
	                failingTestList.addTestCase(testPlans.get(i).getTestCases().get(j)); 
	            }
	        }
	    }
	}
	
	/**
	 * Sets the current test plan to the AbstractTestPlan with the given name. If a test plan with
	 * the given name is not found, the currentTestPlan is set to feilingTestList
	 * @param planName to change the test plan name into
	 */
	public void setCurrentTestPlan(String planName) {
		boolean flag = false;
		for(int i = 0; i < testPlans.size(); i++) {
			String temp = testPlans.get(i).getTestPlanName().toLowerCase();
			if(temp.equals(planName.toLowerCase())) {
				flag = true;
				currentTestPlan = testPlans.get(i);
				break;
			} 
		}
		if(!flag) {
			currentTestPlan = failingTestList;
		}
	}
	
	/**
	 * Returns the current test plan 
	 * @return the current test plan
	 */
	public AbstractTestPlan getCurrentTestPlan() {
		return currentTestPlan;
	}
	
	/**
	 * This method allows for the editing of the name of a test plan and updates isChanged to true.
	 * @param planName to change into
	 * @throws IllegalArgumentException if the currentTestPlan is an FailingTestList, if the name 
	 * is "Failing Tests", or if the name is a duplicate.
	 */
	public void editTestPlan(String planName) {
		if (currentTestPlan == failingTestList) {
			throw new IllegalArgumentException("The Failing Tests list may not be edited.");
		}
		
		if ("Failing tests".equalsIgnoreCase(planName)) {
			throw new IllegalArgumentException("Invalid name.");
		}
		
		for (int i = 0; i < testPlans.size(); i++) {
			if (testPlans.get(i).getTestPlanName().equals(planName)) {
				throw new IllegalArgumentException("Invalid name.");
			}
		}
		
		currentTestPlan.setTestPlanName(planName);
		isChanged = true;
	}
	
	/**
	 * This method deletes the current test plan and updates isChanged to true if the test plan
	 * was deleted
	 * @throws IllegalArgumentException with the message "The Failing Tests list may not be deleted."
	 * if the currentTestPlan is set to failingTestList
	 */
	public void removeTestPlan() {
		if (currentTestPlan == failingTestList) {
			throw new IllegalArgumentException("The Failing Tests list may not be deleted.");
		}
		
		int idx = 0;
		for(int i = 0; i < testPlans.size(); i++) {
			String name = currentTestPlan.getTestPlanName();
			if(name.equals(testPlans.get(i).getTestPlanName())) {
				idx = i;
				break;
			}
		}
		testPlans.remove(idx);
		currentTestPlan = failingTestList;
		isChanged = true;	
	}
	
	/**
	 * Adds a test case to the current test plan with the given name and isChanged is updated to true
	 * @param caseName of the test case to be added
	 */
	public void addTestCase(TestCase caseName) {
		if(currentTestPlan instanceof TestPlan) {
			int curSize = currentTestPlan.getTestCases().size();
			currentTestPlan.addTestCase(caseName);
			//currentTestPlan.getTestCases().get(curSize).getStatus();
			if(curSize + 1 == currentTestPlan.getTestCases().size()) {
				isChanged = true;
				getFailingTests(); 
			}
		}
	}
	
	/**
	 * Adds a test result to the test case at the given index in the current test plan.
	 * @param id of the test case
	 * @param result of the test case
	 * @param actualResult of the test case
	 */
	public void addTestResult(int id, boolean result, String actualResult) {
		if (id < 0 || id >= currentTestPlan.getTestCases().size()) {
	        throw new IllegalArgumentException("Invalid index.");
	    }
	    currentTestPlan.addTestResult(id, result, actualResult);
	    isChanged = true;
	    getFailingTests();
	}
	
	/**
	 * This method clears out the list of test plans and makes a new one and updates isChanges to false
	 */
	public void clearTestPlans() {
		testPlans = new SortedList<TestPlan>();
		failingTestList = new FailingTestList();
		currentTestPlan = failingTestList;
		isChanged = false;
	}
}
