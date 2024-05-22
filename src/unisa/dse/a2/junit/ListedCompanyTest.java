package unisa.dse.a2.junit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import unisa.dse.a2.students.ListedCompany;

/**
 * @author james.walsh@unisa.edu.au
 *
 */
public class ListedCompanyTest extends DSUnitTesting {

	private static String ID = "unisa.dse.a2.junit.ListedCompany:";
	

	@Before
	public void setUp() {
	}

	@Test
	public void testListedCompany_Constructor() {
		Marks.getInstance().marks.put(ID + "ListedCompany_Constructor", 1f);

		ListedCompany lc = new ListedCompany("PEAR", "Pear Computer Limited", 50);

		assertEquals("Code is not 'PEAR'", "PEAR", lc.getCode());
		assertEquals("Name is not 'Pear Computer Limited'", "Pear Computer Limited", lc.getName());
		assertEquals("Price is not 50" , 50, lc.getCurrentPrice());
	}

	@Test
	public void testListedCompany_ProcessTrade() {
		Marks.getInstance().marks.put(ID + "ListedCompany_ProcessTrade", 3f);

		ListedCompany lc = new ListedCompany("PEAR", "Pear Computer Limited", 50);

		assertEquals("Price is not 50 after processing trade" , 50, lc.getCurrentPrice());
		
		lc.processTrade(1000);
		assertEquals("Price is not 60 after processing trade" , 60, lc.getCurrentPrice());

		lc.processTrade(0);
		assertEquals("Price is not 60 after processing trade" , 60, lc.getCurrentPrice());

		lc.processTrade(-5000);
		assertEquals("Price is not 10 after processing trade" , 10, lc.getCurrentPrice());

		lc.processTrade(-5000);
		assertEquals("Price is not 1 after processing trade, should not go below 1 in price" , 1, lc.getCurrentPrice());

		lc.processTrade(100);
		assertEquals("Price is not 11 after processing trade" , 2, lc.getCurrentPrice());
	}
}
