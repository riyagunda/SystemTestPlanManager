/**
 * 
 */
package edu.ncsu.csc216.stp.model.util;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

/**
 * This JUnit test class tests the methods of the Log class
 * @author Riya Gunda
 *
 */
public class LogTest {

	/**
	 * Test method for {@link edu.ncsu.csc216.stp.model.util.Log#Log()}.
	 */
	@Test
	void testLog() {
		Log<String> list = new Log<String>();
		assertEquals(0, list.size());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.stp.model.util.Log#add(java.lang.Comparable)}.
	 */
	@Test
	void testAdd() {
		Log<String> list = new Log<String>();
		
		try {
			list.add(null);
			fail();
		} catch (NullPointerException e) {
			// do nothing
		}
		
		list.add("String 1");
		assertEquals(1, list.size());
		assertEquals("String 1", list.get(0));
		
		list.add("String 2");
		list.add("String 3");
		list.add("String 4");
		list.add("String 5");
		list.add("String 6");
		list.add("String 7");
		list.add("String 8");
		list.add("String 9");
		list.add("String 10");
		list.add("String 11");
	}
	
	/** Tests the get() method */
	@Test
	void testGet() {
		Log<String> list = new Log<String>();
		list.add("String 2");
		list.add("String 3");
		list.add("String 4");
		list.add("String 5");
		
		try {
			list.get(-2);
			fail();
		} catch(IndexOutOfBoundsException e) {
			// DO NOTHING
		}
		
		try {
			list.get(4);
			fail();
		} catch(IndexOutOfBoundsException e) {
			// DO NOTHING
		}
	}

}
