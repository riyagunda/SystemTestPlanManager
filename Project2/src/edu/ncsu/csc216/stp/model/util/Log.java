/**
 * 
 */
package edu.ncsu.csc216.stp.model.util;

/**
 * The Log class represents a collection of elements of type E that implements the ILog interface.
 * It allows for adding elements, retrieving elements by index, and getting the size of the log.
 * 
 * @author Riya Gunda
 * @param <E> type of elements stored in Log
 *
 */
public class Log<E> implements ILog<E> {
	
	/**
	 * The array to store the elements in the log.
	 */
	private E[] log;
	
	/**
	 * The current size of the log.
	 */
	private int size;
	
	/**
	 * The initial capacity of the log.
	 */
	private static final int INIT_CAPACITY = 10;
	
	/**
	 * Constructs a new Log object with an initial capacity of 10.
	 */
	@SuppressWarnings("unchecked")
	public Log() {
		size = 0;
		this.log = (E[]) new Object[INIT_CAPACITY];
	}

	/**
	 * Adds the element to the end of the list.
	 * @param element element to add
	 * @throws NullPointerException if element is null 
	 */
	@Override
	public void add(E element) {
		if(element == null) {
			throw new NullPointerException("Cannot add null element.");
		}
		log[size] = (E) element;
		size++;
		
		if (size() == log.length) {
			@SuppressWarnings("unchecked")
			E[] temp = (E[]) new Comparable[size * 2];
			for (int i = 0; i < log.length; i++) {
				temp[i] = log[i];
			}
			log = temp;
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
		if(idx < 0 || idx >= size) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}
		
		return log[idx];
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
