package unisa.dse.a2.junit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import unisa.dse.a2.interfaces.List;
import unisa.dse.a2.interfaces.ListGeneric;
import unisa.dse.a2.students.DSEListGeneric;
import unisa.dse.a2.students.NodeGeneric;
import unisa.dse.a2.students.Trade;

/**
 * @author james.walsh@unisa.edu.au
 *
 */
public class ListGenericTest extends DSUnitTesting {

	private static String ID = "unisa.dse.a2.junit.ListGeneric:";
	NodeGeneric<Trade> chain1;
	NodeGeneric<Trade> chain2;
	NodeGeneric<Trade> chain3;

	DSEListGeneric<Trade> l;

	@Before
	public void setUp() {
		{
			chain1 = new NodeGeneric<Trade>(null, null, new Trade(null, 1));
		}
		{
			NodeGeneric<Trade> c21 = new NodeGeneric<Trade>(null, null, new Trade(null, 1));
			NodeGeneric<Trade> c22 = new NodeGeneric<Trade>(null, null, new Trade(null, 1));

			chain2 = c21;
			c21.next = c22; c22.prev = c21;
		}
		{
			NodeGeneric<Trade> c31 = new NodeGeneric<Trade>(null, null, new Trade(null, 1));
			NodeGeneric<Trade> c32 = new NodeGeneric<Trade>(null, null, new Trade(null, 2));
			NodeGeneric<Trade> c33 = new NodeGeneric<Trade>(null, null, new Trade(null, 3));

			chain3 = c31;
			c31.next = c32; c32.prev = c31;
			c32.next = c33; c33.prev = c32;
		}
		
		l = new DSEListGeneric<Trade>();
	}

	@Test
	public void testList_CopyConstructor() {
		Marks.getInstance().marks.put(ID + "List_CopyConstructor", 1f);
		/* The copy constructor implementation requires a deep copy. 
		 * That is, after copying the object changes to the original should not impact the copied object.
		 */
		DSEListGeneric<Trade> s = new DSEListGeneric<Trade>();
		s.add(new Trade(null, 0));
		s.add(new Trade(null, 1));

		DSEListGeneric<Trade> other = new DSEListGeneric<Trade>(s);

		assertEquals("Copy constructor should create a List of equal size to the copied List", 2, other.size());
		for ( int i = 1 ; i >= 0 ; --i ) {
			assertEquals("Copy constructor did not copy the List correctly", new Trade(null, i), other.remove(i));
		}

		s.add(new Trade(null, 2));
		assertEquals("Changes to original list should not impact the second list", 1, 1);

	}

	@Test
	public void testList_NodeGeneric() {
		Marks.getInstance().marks.put(ID+"List_NodeGeneric", 1f);

		l = new DSEListGeneric<Trade>(chain1);
		assertEquals("Constructor that accepts a NodeGeneric should set the size of the list appropriately", 1, l.size());

		l = new DSEListGeneric<Trade>(chain2);
		assertEquals("Constructor that accepts a NodeGeneric should set the size of the list appropriately", 2, l.size());
		
		l = new DSEListGeneric<Trade>(chain3);
		assertEquals("Constructor that accepts a NodeGeneric should set the size of the list appropriately", 3, l.size());

		assertEquals("Constructor that accepts a NodeGeneric should set the given NodeGeneric to the head of the list", chain3, l.head);
	}
	
	@Test
	public final void testSize() {
		Marks.getInstance().marks.put(ID+"Size", 1f);
		assertEquals("List.size() should be 0 for an empty list.", 0, l.size());
		
		l.add(new Trade(null, 0));
		assertEquals("List.size() should be 1 for a list with one element.", 1, l.size());	
		
		l.add(new Trade(null, 1));
		int s= l.size();
		assertEquals("List.size() should be 1 for a list with two elements.", 2, s);	
		
		l.add(new Trade(null, 2));
		s= l.size();
		assertEquals("List.size() should be 1 for a list with three elements.", 3, s);	

	}
	
	@Test
	public void testRemoveIntBeginning() {
		Marks.getInstance().marks.put(ID+"RemoveIntBeginning", 1f);
		DSEListGeneric<Trade> l = new DSEListGeneric<Trade>();
		l.add(new Trade(null, 0));
		l.add(new Trade(null, 1));
		l.add(new Trade(null, 2));

		Trade t = l.remove(0);
		assertNotNull("List.remove(int) should not return null when elements exist at that index in the list", t);
		assertEquals("Remove did not remove from the list correctly", new Trade(null, 0), t);
		assertEquals("Remove did not remove from the list correctly", new Trade(null, 1), l.remove(0));
	}
	@Test
	public void testRemoveIntEnd() {
		Marks.getInstance().marks.put(ID+"RemoveIntEnd", 1f);
		Marks.getInstance().marks.put("List:CopyConstructor", 0.1f);
		DSEListGeneric<Trade> l = new DSEListGeneric<Trade>();
		l.add(new Trade(null, 0));
		l.add(new Trade(null, 1));
		l.add(new Trade(null, 2));


		Trade t = l.remove(2);
		assertNotNull("List.remove(int) should not return null when elements exist at that index in the list", t);
		assertEquals("Remove did not remove from the list correctly", new Trade(null, 2), t);
		assertEquals("Remove did not remove from the list correctly", new Trade(null, 1), l.remove(1));
	}

	@Test
	public final void testToString() {
		Marks.getInstance().marks.put(ID+"ToString", 1f);
		
		assertTrue("List.toString() should return a concatenated string with a single space between the elements, " +
			"with no trailing whitespace. Expected [], got: [" + l.toString() + "]", l.toString().equals(""));
		l.add(new Trade(null, 0));
		assertTrue("List.toString() should return a concatenated string with a single space between the elements, " +
				"with no trailing whitespace. Expected [0], got: [" + l.toString() + "]", 
				l.toString().equals("0"));
		
		l.add(new Trade(null, 1));
		assertTrue("List.toString() should return a concatenated string with a single space between the elements, " +
				"with no trailing whitespace. Expected [0 1], got: [" + l.toString() + "]", 
				l.toString().equals("0 1"));
		
		l.add(new Trade(null, 2));
		assertTrue("List.toString() should return a concatenated string with a single space between the elements, " +
				"with no trailing whitespace. Expected [0 1 2], got: [" + l.toString() + "]", 
				l.toString().equals("0 1 2"));
		
		
	}
	
	@Test
	public void testAddIntTrade() {
		Marks.getInstance().marks.put(ID+"AddIntTrade", 1f);
		ListGeneric<Trade> list = new DSEListGeneric<Trade>();

		list.add(new Trade(null, 1));
		list.add(0, new Trade(null, 0));

		Trade n = list.get(0);
		assertEquals("List.add(int, T) should insert the specified T at the specified index.", new Trade(null, 0), n);

		list.add(2, new Trade(null, 2));
		n = list.get(2);
		assertEquals("List.add(int, T) should insert the specified T at the specified index.", new Trade(null, 2), n);

		list.add(2, new Trade(null, 3));
		assertEquals("List.add(int, T) should insert the specified T at the specified index.", new Trade(null, 3), list.get(2));

		assertEquals(4, list.size());
	}

	@Test
	public void testAdd() {
		
		Marks.getInstance().marks.put(ID+"Add", 1f);
		
		assertTrue("List returned false when trying to add an element.", l.add(new Trade(null, 0)));
		String s = l.toString();
		assertTrue("List didn't insert the specified T. " 
				+ "List.add(T), or List.toString() is broken.", 
				"0".equals(s));
		
		assertEquals(l.head, new NodeGeneric<Trade>(null, null, new Trade(null, 0)));
		
		// Add a second time: adding to an empty list is different than adding to a non-empty list. 
		l.add(new Trade(null, 1));
		s = l.toString();
		assertTrue("List didn't insert the specified T. List.add(T), or List.toString() is broken.", 
				"0 1".equals(s));

	}
	
	/**
	 *  method for {@link List# contains(java.lang.Object)}.
	 */
	@Test
	public void testContains() {
		Marks.getInstance().marks.put(ID+"Contains",1f);

		ListGeneric<Trade> list = new DSEListGeneric<Trade>();
		for ( int i = 0 ; i < 3 ; ++i ) 
			list.add(new Trade(null, i));

		for ( int i = 0 ; i < 3 ; ++i ) 
			assertTrue("List.contains(Trade) should return true when the T is in the list.", list.contains(new Trade(null, i)));


		list = new DSEListGeneric<Trade>();
		list.add(new Trade(null, 0));
		list.add(new Trade(null, 1));
		list.add(new Trade(null, 2));

		assertFalse("List.contains() should return false when the T is not in the list", list.contains(new Trade(null, 4)));
		assertFalse("List.contains() should return false when the T is not in the list", list.contains(new Trade(null, -1)));
	}

	/**
	 *  method for {@link List#get(int)}.
	 */
	@Test
	public void testGet() {
		Marks.getInstance().marks.put(ID+"Get", 1f);

		l = new DSEListGeneric<Trade>(chain3);
		int s = l.size();
		assertEquals("List.size() should be working for this test", 3, s);
		
		// remember 0 indexing. 
		Trade n = l.get(4);
		assertNull("List.get() should  return null when the object isn't in the list", n);
		n = l.get(0);
		assertEquals("List.get() isn't returning the correct item.", new Trade(null, 1), n);
		n = l.get(1);
		assertEquals("List.get() isn't returning the correct item.", new Trade(null, 2), n);
		n = l.get(2);
		assertEquals("List.get() isn't returning the correct item.", new Trade(null, 3), n);
		
		assertEquals("List.get() shouldn't change the size of the list", 3, l.size());
		
		assertNull("List.get() shouldn't work on negative indexes", l.get(-1));
	
	}


	@Test
	public void testIndexOf() {
		Marks.getInstance().marks.put(ID + "IndexOf", 1f);
		
		l = new DSEListGeneric<Trade>(chain3);
		
		assertEquals("List.indexOf() should return the correct index", 0, l.indexOf(new Trade(null, 1)));
		assertEquals("List.indexOf() should return the correct index", 1, l.indexOf(new Trade(null, 2)));
		assertEquals("List.indexOf() should return the correct index", 2, l.indexOf(new Trade(null, 3)));
		
		assertEquals("List.indexOf() should return the correct index", -1, l.indexOf(new Trade(null, -1)));
	}

	@Test
	public final void testIsEmpty() {
		Marks.getInstance().marks.put(ID + "IsEmpty", 1f);
		assertTrue("List.isEmpty() should return true when list contains no elements", l.isEmpty());
		
		l.add(new Trade(null, 0));
		assertFalse("List.isEmpty() should return false when list contains elements", l.isEmpty());
	}
	
	@Test
	public void testRemoveInt() {
		Marks.getInstance().marks.put(ID + "RemoveInt",1f);
		ListGeneric<Trade> list = new DSEListGeneric<Trade>();

		list.add(new Trade(null, 0));
		list.add(new Trade(null, 1));
		list.add(new Trade(null, 2));
		list.add(new Trade(null, 3));

		assertEquals("List.add() should add Ts to the list. (List: [" + list.toString() + "])", 4, list.size());

		assertEquals("List.get() should return the T at the specified index. (List: [" + list.toString() + "])", 
				new Trade(null, 1), list.get(1));
		list.remove(1);
		assertEquals("List.remove(int) should decrement the size by one. (List: [" + list.toString() + "])", 3, list.size());

		assertEquals("List.get() should return the T at the specified index. (List: [" + list.toString() + "])", 
				new Trade(null, 2), list.get(1));

	}

	/**
	 *  method for {@link List# remove(java.lang.Object)}.
	 */
	@Test
	public void testRemoveT_Beginning() {
		Marks.getInstance().marks.put(ID + "RemoveT_Beginning", 1f);

		ListGeneric<Trade> list = new DSEListGeneric<Trade>();
		list.add(new Trade(null, 0));
		list.add(new Trade(null, 1));
		list.add(new Trade(null, 2));

		assertTrue("List.remove() should return true when removing an existing object", list.remove(new Trade(null, 0)));
		assertEquals("List.size() should decrement after a remove().", 2, list.size());
		assertTrue("List.remove() should remove the given object", list.toString().equals("1 2"));
	}

	/**
	 *  method for {@link List# remove(java.lang.Object)}.
	 */
	@Test
	public void testRemoveT_End() {
		Marks.getInstance().marks.put(ID + "RemoveT_End", 1f);

		ListGeneric<Trade> list = new DSEListGeneric<Trade>();
		list.add(new Trade(null, 0));
		list.add(new Trade(null, 1));
		list.add(new Trade(null, 2));

		assertTrue("List.remove() should return true when removing an existing object", list.remove(new Trade(null, 2)));
		assertEquals("List.size() should decrement after a remove().", 2, list.size());
		assertTrue("List.remove() should remove the given object", list.toString().equals("0 1"));
	}

	/**
	 *  method for {@link List# remove(java.lang.Object)}.
	 */
	@Test
	public void testRemoveT_Middle() {
		Marks.getInstance().marks.put(ID+"RemoveT_Middle", 1f);

		ListGeneric<Trade> list = new DSEListGeneric<Trade>();
		list.add(new Trade(null, 0));
		list.add(new Trade(null, 1));
		list.add(new Trade(null, 2));

		assertTrue("List.remove() should return true when removing an existing object", list.remove(new Trade(null, 1)));
		assertEquals("List.size() should decrement after a remove().", 2, list.size());
		assertTrue("List.remove() should remove the given object", list.toString().equals("0 2"));
	}

	@Test
	public void testRemoveT_Single() {
		Marks.getInstance().marks.put(ID+"RemoveT_Single", 1f);

		ListGeneric<Trade> list = new DSEListGeneric<Trade>();
		list.add(new Trade(null, 0));
		list.add(new Trade(null, 1));
		list.add(new Trade(null, 2));
		list.add(new Trade(null, 1));

		assertTrue("List.remove(Trade) should remove the first instance of the specified Trade.", list.remove(new Trade(null, 1)));
		assertTrue("List.remove(Trade) should only remove one instance of the specified Trade!", list.toString().equals("0 2 1"));
	}
}
