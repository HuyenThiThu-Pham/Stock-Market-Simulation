package unisa.dse.a2.interfaces;

import java.util.Iterator;

/**
 * @author simont
 *
 */
public interface ListGeneric<T> extends Iterable<T>{
	
	/**
	 * Inserts the specified element at the specified position in this list. 
	 *  Shifts the element currently at that position (if any) and any subsequent 
	 *  elements to the right (adds one to their indices).
	 * @param index Index at which to add
	 * @param obj The object to add
	 * @return True if insertion was successful
	 * 
	 * @throws NullPointerException if the given object is null
	 * @throws IndexOutOfBoundsException if the index is out of range
	 */
	public boolean add(int index, T obj);
	
	/**
	 * Returns true iff the given object is contained in the list. 
	 * 
	 * @param obj The object whose presence is to be tested
	 * @return True if the list contains the given object
	 * 
	 * @throws NullPointerException if the specified element is null
	 */
	public boolean contains(T obj);
	
	/**
	 * Remove the first instance of the given object from the list, if it exists
	 * @param obj The object to remove
	 * @return True if the object was removed 
	 * 
	 * @throws NullPointerException if the specified object is null
	 */
	public boolean remove(T obj);
	
	/**
	 * Remove the object at the specified index from the list, if it exists. 
	 * @param index The index to remove
	 * @return The object previously at the specified index
	 *	
	 * @throws IndexOutOfBoundsException if the specified index is out of range
	 */
	public T remove(int index);
	
	/**
	 * Get the object at the specified index, if it exists. 
	 * @param index The index to retrieve 
	 * @return The object at the specified index, if it exists, and null if it does not exist
	 */
	public T get(int index);

	
	/**
	 * Returns the first index of the specified object, or -1 if the object does not exist
	 * in the list. 
	 * @param node
	 * @return The index of the specified node, or -1 if it is not contained in the list. 
	 */
	public int indexOf(T node);
	
	
	/**
	 * Appends the specified element to the end of this list.
	 * @param obj The object to add. 
	 * @return True if the object has been added to the list. 
	 * 
	 * @throws NullPointerException if the specified object is null
	 */
	public boolean add(T obj);
	
	
	/**
	 * Returns true if this list contains no elements. 
	 * @return True if the list is empty. 
	 */
	public boolean isEmpty();
	
	
	/**
	 * Returns the number of elements in this list. 
	 * @return The number of elements in this list. 
	 */
	public int size();
	
	/**
	 * Returns a string containing the toString() 
	 * 	for each object in this list. 
	 * @return The concatenated toString() for each element in this list
	 */
	@Override
	public String toString();
	
	/**
	 * Compares this list with the specified object for equality. 
	 * The equality comparison must be value-based rather than the default 
	 * 	(reference based). 
	 * 
	 * @param obj The object to compare against. 
	 * @return True if the specified object is value-comparatively equal to this list
	 */
	@Override
	public boolean equals(Object obj);
	
	/**
	 * Returns the hashCode for this list. 
	 * (This method must satisfy the constraint that if List l1.equals(List l2), 
	 * 	then l1.hashCode() == l2.hashCode() must also be true. 
	 * @return The hashCode of this list. 
	 */
	@Override
	public int hashCode();

	Iterator<T> iterator();
}