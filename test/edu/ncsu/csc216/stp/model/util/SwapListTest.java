/**
 * 
 */
package edu.ncsu.csc216.stp.model.util;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * This JUnit test class tests the methods of the SwapList class
 * @author Riya Gunda
 *
 */
class SwapListTest {

	/**
	 * Test method for {@link edu.ncsu.csc216.stp.model.util.SwapList#SwapList()}.
	 */
	@Test
	void testSwapList() {
		SwapList<String> list = new SwapList<String>();
		assertEquals(0, list.size());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.stp.model.util.SwapList#add(java.lang.Comparable)}.
	 */
	@Test
	void testAdd() {
		SwapList<String> list = new SwapList<String>();
		list.add("Riya");
		assertEquals("Riya", list.get(0));
		assertEquals(1, list.size());
		
		try {
			list.add(null);
			fail();
		} catch(NullPointerException e) {
			// Do nothing
		}
		
		list.add("String 2");
		list.add("String 3");
		list.add("String 4");
		list.add("String 5");
		list.add("String 6");
		list.add("String 7");
		list.add("String 8");
		list.add("String 9");
		list.add("String 10");
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.stp.model.util.SwapList#remove(int)}.
	 */
	@Test
	void testRemove() {
		SwapList<String> list = new SwapList<String>();
		list.add("Riya");
		assertEquals("Riya", list.get(0));
		assertEquals(1, list.size());
		
		try {
			list.remove(-3);
			fail();
		} catch(IndexOutOfBoundsException e) {
			// Do nothing
		}
		
		try {
			list.remove(1);
			fail();
		} catch(IndexOutOfBoundsException e) {
			// Do nothing
		}
		
		list.remove(0);
		list.add("Riya");
		list.add("Gunda");
		list.add("Saanvi");
		list.remove(1);
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.stp.model.util.SwapList#moveUp(int)}.
	 */
	@Test
	void testMoveUp() {
		SwapList<String> list = new SwapList<String>();
		list.add("Riya");
		list.add("Gunda");
		list.add("Saanvi");
		list.add("Food");
		list.moveUp(2);
		assertEquals("Saanvi", list.get(1));
		assertEquals("Gunda", list.get(2));
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.stp.model.util.SwapList#moveDown(int)}.
	 */ 
	@Test
	void testMoveDown() {
		SwapList<String> list = new SwapList<String>();
		list.add("Riya");
		list.add("Gunda");
		list.add("Saanvi");
		list.add("Food");
		list.moveDown(1);
		assertEquals("Saanvi", list.get(1));
		assertEquals("Gunda", list.get(2));
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.stp.model.util.SwapList#moveToFront(int)}.
	 */
	@Test
	void testMoveToFront() {
		SwapList<String> list = new SwapList<String>();
		list.add("Riya");
		list.add("Gunda");
		list.add("Saanvi");
		list.add("Food");
		list.moveToFront(2);
		assertEquals("Saanvi", list.get(0));
		assertEquals("Riya", list.get(1));
		assertEquals("Gunda", list.get(2));
		assertEquals("Food", list.get(3));
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.stp.model.util.SwapList#moveToBack(int)}.
	 */
	@Test
	void testMoveToBack() {
		SwapList<String> list = new SwapList<String>();
		list.add("Riya");
		list.add("Gunda");
		list.add("Saanvi");
		list.add("Food");
		list.moveToBack(1);
		assertEquals("Gunda", list.get(3));
		assertEquals("Food", list.get(2));
	}
}
