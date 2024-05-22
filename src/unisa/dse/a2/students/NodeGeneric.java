package unisa.dse.a2.students;

/**
 * ===========================================================
 * 
 * You shouldn't need to make any changes in this class
 * 
 * We'll be using our own version for marking
 * 
 * ===========================================================
 * 
 */
public class NodeGeneric<T> {

	public NodeGeneric<T> next;
	public NodeGeneric<T> prev;
	
	private T t;

	public NodeGeneric(NodeGeneric<T> next, NodeGeneric<T> prev, T node) {
		this.next = next;
		this.prev = prev;
		this.t = node;
	}

	public T get() {
		return t;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other)
			return true;
		if (other == null)
			return false;
		if (!(other instanceof NodeGeneric<?>))
			return false;

		return t.equals(((NodeGeneric<T>)other).get());
	}

	@Override
	public int hashCode() {
		if ( t == null )
			return 0;
		return t.hashCode();
	}

}
