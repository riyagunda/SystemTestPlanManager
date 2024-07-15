/**
 * 
 */
package edu.ncsu.csc216.stp.model.util;


/**
 * The SortedList class represents a sorted collection of elements of type E that implements the 
 * ISortedList interface and allows for adding elements, removing elements by index, checking if an 
 * element is present, retrieving elements by index, and getting the size of the list.
 *
 * @author Riya Gunda
 * @param <E> type of elements in the SortedList
 */
public class SortedList<E extends Comparable<E>> implements ISortedList<E> {
	
	/**
	 * The ListNode class represents a node in the linked list 
	 */
	public class ListNode {
		/**
		 * The data stored in the node.
		 */
		public E data;
		
		/**
		 * The reference to the next node in the linked list
		 */
		public ListNode next;
		
		/**
		 * Constructs a new ListNode object with the specified data and next node
		 * @param data to be stored in the node
		 * @param next the reference to the next node 
		 */
		public ListNode(E data, ListNode next) {
			this.data = data;
			this.next = next;
		}
	}

	/**
	 * The current size of the sorted list.
	 */
	private int size;
	
	/**
	 * The reference to the front node of the linked list.
	 */
	private ListNode front;
	
	/**
	 * Constructs a new SortedList object.
	 */
	public SortedList() {
		this.front = null;
		this.size = 0;
	}
	
	/**
	 * Adds the element to the list in sorted order.
	 * @param element element to add
	 * @throws NullPointerException if element is null
	 * @throws IllegalArgumentException if element cannot be added 
	 */
	@Override
	public void add(E element) {
		if(element == null) {
			throw new NullPointerException("Cannot add null element.");
		}
		
		for(int i = 0; i < size; i++) {
			if(element.equals(get(i))) {
				throw new IllegalArgumentException("Cannot add duplicate element.");
			}
		}
		
		if(front == null || element.compareTo(front.data) < 0) {
			front = new ListNode(element, front);
		} else {
			ListNode current = front;
			while(current.next != null && current.next.data.compareTo(element) < 0) {
				current = current.next;
			}
			current.next = new ListNode(element, current.next);
		}
		this.size++;
	}

	/**
	 * Returns the element from the given index.  The element is
	 * removed from the list.
	 * @param idx index to remove element from
	 * @return element at given index
	 * @throws IndexOutOfBoundsException if the idx is out of bounds
	 * 		for the list
	 */
	@Override
	public E remove(int idx) {
		checkIndex(idx);
		ListNode back = front;		
		ListNode removed = null;
		
		if(idx == 0) {
			removed = front;
			front = front.next;
		} else {
			ListNode current = front;
			for(int i = 0; i < idx - 1; i++) {
				current = current.next;
			}
			back = current;
			removed = back.next;
			back.next = removed.next;
		}		
		size--;
		
		return (E) removed.data;
	}
	
	private void checkIndex(int idx) {
		if(idx < 0 || idx >= size()) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}
	}

	@Override
	public boolean contains(E element) {
		for(int i = 0; i < size; i++) {
			if(element.equals(get(i))) {
				return true;
			}
		}
		return false;
	}

	@Override
	public E get(int idx) {
		checkIndex(idx);
	
		ListNode current = front;
		
		if(idx == 0) {
			return current.data;
		} 
		
		for(int i = 0; i < idx - 1; i++) {
			current = current.next;
		}
		
		return (E) current.next.data;
	}

	@Override
	public int size() {
		return size;
	}

}
