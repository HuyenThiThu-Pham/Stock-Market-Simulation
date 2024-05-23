package unisa.dse.a2.students;

import unisa.dse.a2.interfaces.ListGeneric;

/**
 * @author simont
 *
 */
public class DSEListGeneric<T> implements ListGeneric<T> {
	
	public NodeGeneric<T> head;
	private NodeGeneric<T> tail;
	
	// Blank constructor
	public DSEListGeneric() {
		head = null;
		tail = null;
		
	}
	
	// Constructor accepting one Node
	public DSEListGeneric(NodeGeneric<T> head_) {
		this.head = head;
		this.tail = head;
	}
	
	//Takes a list then adds each element into a new list
	public DSEListGeneric(DSEListGeneric<T> other) { // Copy constructor. 
		this();
        if (other.head != null) {
            NodeGeneric<T> current = other.head;
            while (current != null) {
                add(current.get());
                current = current.next;
            }
        }
	}

	//remove and return the item at the parameter's index
	public T remove(int index) {
		if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }
        NodeGeneric<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        T toReturn = current.get();

        if (current == head) {
            head = head.next;
            if (head != null) {
                head.prev = null;
            } else {
                tail = null; // The list is now empty
            }
        } else if (current == tail) {
            tail = tail.prev;
            if (tail != null) {
                tail.next = null;
            } else {
                head = null; // The list is now empty
            }
        } else {
            current.prev.next = current.next;
            current.next.prev = current.prev;
        }

        return toReturn;

	}

	//returns the index of the String parameter 
	public int indexOf(String obj) {
	}
	
	//returns item at parameter's index
	public void get(int index) {
	}

	//checks if there is a list
	public boolean isEmpty() {
	}

	//return the size of the list
	public int size() {
	}
	
	//Take each element of the list a writes them to a string 
	@Override
	public String toString() {
	}

	//add the parameter item at of the end of the list
	public boolean add(Object obj) {
	}

	//add item at parameter's index
	public boolean add(int index, Object obj) {
	}

	//searches list for parameter's String return true if found
	public boolean contains(Object obj) {
	}

	//removes the parameter's item form the list
	public boolean remove(Object obj) {
	}
	
	@Override
	public int hashCode() {
		return 0;
	}

	@Override
	public boolean equals(Object other) {
		return true;
	}
	
}
