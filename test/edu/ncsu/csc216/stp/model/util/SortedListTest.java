/**
 * 
 */
package edu.ncsu.csc216.stp.model.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
/**
 * This JUnit test class tests the methods of the SortedList class
 * @author Riya Gunda
 *
 */
class SortedListTest {

	/**
	 * Test method for {@link edu.ncsu.csc216.stp.model.util.SortedList#add(java.lang.Comparable)}.
	 */
	@Test
	void testAdd() {
		SortedList<String> list = new SortedList<String>();
		assertEquals(0, list.size());	
		list.add("Saanvi");
		list.add("Riya");
		assertEquals("Riya", list.get(0));	
		
		list.add("Apple");
		assertEquals("Apple", list.get(0));
		
		list.add("Aaple");
		assertEquals("Aaple", list.get(0));
		
		try {
			list.add(null);
			fail();
		} catch(NullPointerException e) {
			// do nothing
		}
		
		try {
			list.add("Riya");
			fail();
		} catch(IllegalArgumentException e) {
			// do nothing
		}
		
		list.add("Toads");
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.stp.model.util.SortedList#remove(int)}.
	 */
	@Test
	void testRemove() {
		SortedList<String> list = new SortedList<String>();
		assertEquals(0, list.size());	
		list.add("Saanvi");
		list.add("Riya");		
		list.add("Apple");
		assertEquals(3, list.size());
		list.remove(0);
		assertEquals("Riya", list.get(0));
		
		try {
			list.remove(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			// do nothing
		}
		
		try {
			list.remove(2);
			fail();
		} catch (IndexOutOfBoundsException e) {
			// do nothing
		}
		
		try {
			list.remove(5);
			fail();
		} catch (IndexOutOfBoundsException e) {
			// do nothing
		}
		
		list.add("Toads");
		list.add("Jaguar");
		list.remove(1);
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.stp.model.util.SortedList#contains(java.lang.Comparable)}.
	 */
	@Test
	void testContains() {
		SortedList<String> list = new SortedList<String>();
		assertEquals(0, list.size());	
		list.add("Saanvi");
		list.add("Riya");		
		list.add("Apple");
		assertTrue(list.contains("Riya"));
		assertFalse(list.contains("Pizza"));
	}

	

}
