package unisa.dse.a2.junit;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import unisa.dse.a2.students.ListedCompany;
import unisa.dse.a2.students.SecuritiesExchange;
import unisa.dse.a2.students.StockBroker;
import unisa.dse.a2.students.Trade;
import unisa.dse.a2.students.UntradedCompanyException;

/**
 * @author james.walsh@unisa.edu.au
 *
 */
public class SecuritiesExchangeTest extends DSUnitTesting {

	private static String ID = "unisa.dse.a2.junit.SecuritiesExchange:";
	

	@Before
	public void setUp() {
	}

	@Test
	public void testSecuritiesExchange_Constructor() {
		Marks.getInstance().marks.put(ID + "SecuritiesExchange_Constructor", 1f);

		SecuritiesExchange asx = new SecuritiesExchange("ASX");

		assertEquals("Unexpected exchange name", "ASX", asx.getName());

		assertEquals("Exchange should not have any announcements", 0, asx.announcements.size());

		assertEquals("Exchange should not have any listed companies", 0, asx.companies.size());

		assertEquals("Exchange should not have any brokers", 0, asx.brokers.size());
	}

	@Test
	public void testSecuritiesExchange_AddCompany() {
		Marks.getInstance().marks.put(ID + "SecuritiesExchange_AddCompany", 1f);

		SecuritiesExchange asx = new SecuritiesExchange("ASX");
		ListedCompany lc = new ListedCompany("PEAR", "Pear Computer Limited", 50);
		
		assertTrue("AddCompany should return true when unlisted company is added", asx.addCompany(lc));

		assertEquals("Exchange companies doesn't list the added company", 1, asx.companies.size());
		
		assertFalse("AddCompany should return false when null added", asx.addCompany(null));
		
		assertFalse("AddCompany should return false when listed company is added", asx.addCompany(lc));

	}

	@Test
	public void testSecuritiesExchange_AddBroker() {
		Marks.getInstance().marks.put(ID + "SecuritiesExchange_AddBroker", 1f);

		SecuritiesExchange asx = new SecuritiesExchange("ASX");
		StockBroker alex = new StockBroker("Alex Aces");
		
		assertTrue("AddCompany should return true when unlisted company is added", asx.addBroker(alex));

		assertEquals("Exchange companies doesn't list the added company", 1, asx.brokers.size());
		
		assertFalse("AddCompany should return false when null added", asx.addBroker(null));
		
		assertFalse("AddCompany should return false when listed company is added", asx.addBroker(alex));

	}

	@Test
	public void testSecuritiesExchange_ProcessTradeRound() throws UntradedCompanyException, FileNotFoundException {
		Marks.getInstance().marks.put(ID + "SecuritiesExchange_ProcessTradeRound", 1f);

		SecuritiesExchange asx = new SecuritiesExchange("ASX");
		ListedCompany pear = new ListedCompany("PEAR", "Pear Computer Limited", 50);
		ListedCompany orange = new ListedCompany("ORNG", "Orange Computer Limited", 100);
		asx.addCompany(pear);
		asx.addCompany(orange);
		StockBroker alex = new StockBroker("Alex Aces");
		StockBroker dave = new StockBroker("Dodge Daves");
		asx.addBroker(alex);
		asx.addBroker(dave);
		
		assertEquals("No trades should processed", 0, asx.processTradeRound());
		
		Trade alexT1 = new Trade(alex, "PEAR", 100);
		alex.placeOrder(alexT1);
		Trade alexT2 = new Trade(alex, "ORNG", -600);
		alex.placeOrder(alexT2);
		Trade alexT3 = new Trade(alex, "ORNG", 200);
		alex.placeOrder(alexT3);

		Trade daveT1 = new Trade(dave, "ORNG", 300);
		dave.placeOrder(daveT1);
		Trade daveT2 = new Trade(dave, "PEAR", -100);
		dave.placeOrder(daveT2);
		
		assertEquals("Two trades should processed in first round", 2, asx.processTradeRound());
		
		assertEquals("PEAR price incorrect", 51, pear.getCurrentPrice());
		assertEquals("ORNG price incorrect", 103, orange.getCurrentPrice());

		assertEquals("Two trades should processed in second round", 2, asx.processTradeRound());
		
		assertEquals("PEAR price incorrect", 50, pear.getCurrentPrice());
		assertEquals("ORNG price incorrect", 97, orange.getCurrentPrice());

		assertEquals("One trade should processed in third round", 1, asx.processTradeRound());

		assertEquals("ORNG price incorrect", 99, orange.getCurrentPrice());
		
		assertEquals("No trades should processed in fourth round", 0, asx.processTradeRound());

		Trade daveT3 = new Trade(dave, "TSLA", 800);
		dave.placeOrder(daveT3);

		assertThrows( UntradedCompanyException.class, () -> { asx.processTradeRound(); } );

		dave.addWatchlist("PEAR");
		
		Trade daveT6 = new Trade(dave, "PEAR", -100);
		dave.placeOrder(daveT6);
		Trade daveT7 = new Trade(dave, "ORNG", 900);
		dave.placeOrder(daveT7);

		assertEquals("One trade should processed in fifth round", 1, asx.processTradeRound());
		
		assertEquals("ORNG price incorrect", 108, orange.getCurrentPrice());

		assertEquals("One trade should processed in sixth round", 1, asx.processTradeRound());
		
		assertEquals("PEAR price incorrect", 49, pear.getCurrentPrice());
		
		File text = new File("TestCommands.txt");
	     
        Scanner sc = new Scanner(text);
		
		asx.runCommandLineExchange(sc);
		
	}
}
