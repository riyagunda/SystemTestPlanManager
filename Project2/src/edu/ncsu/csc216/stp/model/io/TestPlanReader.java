package edu.ncsu.csc216.stp.model.io;

import java.io.File;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.ncsu.csc216.stp.model.util.ISortedList;
import edu.ncsu.csc216.stp.model.util.SortedList;
import edu.ncsu.csc216.stp.model.test_plans.AbstractTestPlan;
import edu.ncsu.csc216.stp.model.test_plans.TestPlan;
import edu.ncsu.csc216.stp.model.tests.TestCase;
import edu.ncsu.csc216.stp.model.tests.TestResult;

/**
 * This class reads the given file and sorts the informaation into test plans, test cases, etc.
 * @author Riya Gunda
 *
 */
public class TestPlanReader {
	

	/**
	 * This method reads the whole file into a string variable and divides into lines for the
	 * private helper methods to process the rest.
	 * @param fileName of the file to be read
	 * @return a sorted list of the data retrieved from the file
	 * @throws IllegalArgumentException with the message "Unable to load file." if the file cannot be 
	 * found
	 */
	public static ISortedList<TestPlan> readTestPlansFile(File fileName) {
		ISortedList<TestPlan> testPlans = new SortedList<TestPlan>();

        try {
            StringBuilder fileContent = new StringBuilder();

            try (Scanner fileScanner = new Scanner(new FileInputStream(fileName))) {
                while (fileScanner.hasNextLine()) {
                    fileContent.append(fileScanner.nextLine()).append("\n");
                }
            }
            
            if (!fileContent.toString().trim().startsWith("!")) {
                throw new IllegalArgumentException("Unable to load file.");
            }
            
            try (Scanner testPlanScanner = new Scanner(fileContent.toString())) {
                testPlanScanner.useDelimiter("\\r?\\n?[!]");

                while (testPlanScanner.hasNext()) {
                    String testPlanToken = testPlanScanner.next();
                    TestPlan testPlan = processTestPlan(testPlanToken);
                    if (testPlan != null) {
                    	boolean flag = true;
                    	for(int i = 0; i < testPlans.size(); i++) {
                    		if(testPlans.get(i).getTestPlanName() == testPlan.getTestPlanName()) {
                    			flag = false;
                    			break;
                    		}
                    	}
                    	if(flag) {
                    		testPlans.add(testPlan);
                    	}
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Unable to load file."); 
        }

        return testPlans;
	}
	
	/**
	 * This method reads lines that need to be processed as Test plans and passes the rest to
	 * the private helper method to divide test cases.
	 * @param line to be read and processed
	 * @return the TestPlan object with the name from the line it just read
	 */
	private static TestPlan processTestPlan(String line) {
	    try (Scanner testPlanScanner = new Scanner(line)) {
	        testPlanScanner.useDelimiter("\\r?\\n?\\s*[!]");
	        String testPlanName = testPlanScanner.nextLine().substring(1).trim();
	        TestPlan testPlan = new TestPlan(testPlanName);

	        testPlanScanner.useDelimiter("\\r?\\n?[#]");

	        while (testPlanScanner.hasNext()) {
	            String testCaseToken = testPlanScanner.next().substring(1).trim();
	            if (!testCaseToken.isEmpty()) {
	                TestCase testCase = processTest(testPlan, testCaseToken);
	                if (testCase != null) {
	                    if (testPlan.getTestCases() == null || testPlan.getTestCases().size() == 0) {
	                        testPlan.addTestCase(testCase);
	                    } else {
	                        testPlan.addTestCase(testCase);
	                    }
	                } 
	            }
	        } 
	        return testPlan;
	    } catch (Exception e) {
	        return null;
	    }
	}

	/**
	 * This method processes the test cases and its test results
	 * @param name of the test plan
	 * @param testCase to process
	 * @return the test case that was just read from the file
	 */
	private static TestCase processTest(AbstractTestPlan name, String testCase) {
	    try (Scanner taskScanner = new Scanner(testCase)) {
	        taskScanner.useDelimiter("\\r?\\n?\\s*[*]");
	        String taskLine = taskScanner.nextLine();

	        try (Scanner line = new Scanner(taskLine)) {
	            line.useDelimiter(",");

	            String id;
	            String type;
	            
	            if(line.hasNext()) {
					id = line.next();
				} else {
					line.close();
					return null;
				}
	            
	            if(line.hasNext()) {
					type = line.next();
				} else {
					line.close();
					return null;
				}

	            taskScanner.useDelimiter("\\r?\\n?\\s*[*]");
	            String desc = taskScanner.next();
	            String exp = taskScanner.next();
	            

	            String temp = exp;
	            exp = "";
	            for(int i = 0; i < temp.length(); i++) {
	            	if(temp.charAt(i) != '-') {
	            		exp += temp.charAt(i);
	            	} else {
	            		break;
	            	}
	            } 
	            
	            
	            // Handle results 
	            Scanner resultScanner = new Scanner(temp);
	            resultScanner.useDelimiter("\\r?\\n?\\s*[-]");
	            TestCase t = new TestCase(id, type, desc, exp);
	            String result = "";
	            
	            while(resultScanner.hasNext()) {
	            	result = resultScanner.next();
	            	if(result.substring(1, 5).equals("PASS")) {
	            		t.addTestResult(true, result.substring(6));
	            	} else if(result.substring(1, 5).equals("FAIL")) {
	            		t.addTestResult(false, result.substring(6));
	            	}
	            } 
	            
	             
	            	            
	            return t;
	        }
	    } catch (Exception e) {
	        return null;
	    }
	}
}

