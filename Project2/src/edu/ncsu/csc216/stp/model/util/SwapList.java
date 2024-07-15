	/**
 * 
 */
package edu.ncsu.csc216.stp.model.util;

/**
 * The SwapList class represents a collection of elements of type E that implements the ISwapList 
 * interface. It allows for adding elements, removing elements by index, moving elements within the 
 * list, and getting the size of the list.
 * @author Riya Gunda
 *
 * @param <E> type of elements in SwapList
 */
public class SwapList<E> implements ISwapList<E> {

	/**
	 * The initial capacity of the list.
	 */
	private static final int INITIAL_CAPACITY = 10;
	
	/**
	 * The array to store the elements in the list.
	 */
	private E[] list;
	
	/**
	 * The current size of the list.
	 */
	private int size;
	
	/**
	 * Constructs a new SwapList object.
	 */
	public SwapList() {
		size = 0;
		list = (E[]) new Object[INITIAL_CAPACITY];
	}
	
	/**
	 * Adds the element to the end of the list.
	 * @param element element to add
	 * @throws NullPointerException if element is null
	 * @throws IllegalArgumentException if element cannot be added 
	 */
	@Override
	public void add(E element) {
		if(element == null) {
			throw new NullPointerException("Cannot add null element.");
		}
		checkCapacity(size + 1); // CHECK
		
		list[size] = element;
		size++;
		
		checkCapacity(size);
	}
	
	/**
	 * This private helper method checks if the specified capacity is valid for the list
	 * @param capacity of the list to be checked
	 */
	@SuppressWarnings("unchecked")
	private void checkCapacity(int capacity) {
		if(capacity >= list.length) {
			E[] temp = (E[]) new Object[list.length * 2];
			for (int i = 0; i < list.length; i++) {
				temp[i] = list[i];
			}
			list = temp;
		}
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

		E removed = list[idx];

		if (idx == size() - 1) {
			list[idx] = null;
			size--;
			return removed;
		} else {
			for (int i = idx; i < size(); i++) {
				list[i] = list[i + 1];
			}
		}
	
		list[size() - 1] = null;
		size--;
		return removed;
	}
	
	/**
	 * This method checks if the given index is a valid index for the list
	 * @param idx of the element to be checked
	 */
	private void checkIndex(int idx) {
		if(idx < 0 || idx >= size()) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}
	}

	/**
	 * Moves the element at the given index to index-1.  If the element is
	 * already at the front of the list, the list is not changed.
	 * @param idx index of element to move up
	 * @throws IndexOutOfBoundsException if the idx is out of bounds
	 * 		for the list
	 */
	@Override
	public void moveUp(int idx) {
		checkIndex(idx);
		if(idx != 0) {
			E temp = list[idx - 1];
			list[idx - 1] = list[idx];
			list[idx] = temp;
		}
	}

	/**
	 * Moves the element at the given index to index+1.  If the element is
	 * already at the end of the list, the list is not changed.
	 * @param idx index of element to move down
	 * @throws IndexOutOfBoundsException if the idx is out of bounds
	 * 		for the list
	 */
	@Override
	public void moveDown(int idx) {
		checkIndex(idx);
		if(idx < size - 1) {
			E temp = list[idx];
			list[idx] = list[idx + 1];
			list[idx + 1] = temp;
		}
	}

	/**
	 * Moves the element at the given index to index 0.  If the element is
	 * already at the front of the list, the list is not changed.
	 * @param idx index of element to move to the front
	 * @throws IndexOutOfBoundsException if the idx is out of bounds
	 * 		for the list
	 */
	@Override
	public void moveToFront(int idx) {
		checkIndex(idx);
		if(idx > 0) {
			E temp = list[idx];
			list[idx] = null;
			for(int i = idx; i > 0; i--) {
				list[i] = list[i - 1];
			}
			list[0] = temp;
		}
	}

	/**
	 * Moves the element at the given index to size-1.  If the element is
	 * already at the end of the list, the list is not changed.
	 * @param idx index of element to move to the back
	 * @throws IndexOutOfBoundsException if the idx is out of bounds
	 * 		for the list
	 */
	@Override
	public void moveToBack(int idx) {
		checkIndex(idx);
		if(idx != list.length - 1) {
			E temp = list[idx];
			for(int i = idx; i < size - 1; i++) {
				list[i] = list[i + 1];
			}
			list[size - 1] = temp;
		}
	}

	/**
	 * Returns the element at the given index.
	 * @param idx index of the element to retrieve
	 * @return element at the given index
	 * @throws IndexOutOfBoundsException if the idx is out of bounds
	 * 		for the list
	 */
	@Override
	public E get(int idx) {
		checkIndex(idx);
		return list[idx];
	}

	/**
	 * Returns the number of elements in the list.
	 * @return number of elements in the list
	 */
	@Override
	public int size() {
		return size;
	}

}
