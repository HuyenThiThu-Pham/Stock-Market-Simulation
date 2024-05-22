package unisa.dse.a2.students;

/**
 * ===========================================================
 * 
 * You shouldn't need to make any changes in this class
 * 
 * We'll be using our own version for marking
 * 
 * Please see NodeGeneric for a "generic" version of this class to be used by NodeGeneric
 * 
 * ===========================================================
 * 
 */
public class Node {

	public Node next;
	public Node prev;
	
	private String t;

	public Node(Node next, Node prev, String token) {
		this.next = next;
		this.prev = prev;
		this.t = token;
	}

	public String getString() {
		return t;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other)
			return true;
		if (other == null)
			return false;
		if (!(other instanceof Node))
			return false;

		return t.equals(((Node)other).getString());
	}

	@Override
	public int hashCode() {
		if ( t == null )
			return 0;
		return t.hashCode();
	}

}
