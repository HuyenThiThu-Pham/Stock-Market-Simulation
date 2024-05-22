package unisa.dse.a2.junit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import unisa.dse.a2.students.StockBroker;
import unisa.dse.a2.students.Trade;

/**
 * @author james.walsh@unisa.edu.au
 *
 */
public class TradeTest extends DSUnitTesting {

	private static String ID = "unisa.dse.a2.junit.Trade:";
	
	StockBroker broker;

	@Before
	public void setUp() {
		broker = new StockBroker("Test Broker");
	}

	@Test
	public void testTrade_IDConstructor() {
		Marks.getInstance().marks.put(ID + "Trade_IDConstructor", 1f);

		Trade t1 = new Trade(broker, 123);
		Trade t2 = new Trade(broker, 123);

		assertEquals("Calling equals on same trade object with itself should be equal", t1, t1);

		assertEquals("Calling equals on different trade object should not be equal", t1, t2);
	}

	@Test
	public void testTrade_FullConstructor() {
		Marks.getInstance().marks.put(ID + "Trade_FullConstructor", 2f);

		Trade t = new Trade(broker, "DALL", 1000);

		assertEquals("Company code should be DALL", "DALL", t.getCompanyCode());

		assertEquals("Share quantity should be 1000", 1000, t.getShareQuantity());
	}

	/**
	 * This first test for compareTo will examine the comparison of objects purely based on the
	 * value returned by their getCreated() method. It doesn't take into account any watchlist priorities.
	 * Make sure you pass these tests first, before then moving onto passing the testTrade_CompareToPriority tests
	 */
	@Test
	public void testTrade_CompareTo() {
		
		Marks.getInstance().marks.put(ID + "Trade_CompareTo", 5f);

		StockBroker dodgeBroker = new StockBroker("Test Broker");
		
		Trade t1 = new Trade(dodgeBroker, "DALL", 1000);
		Trade t2 = new Trade(dodgeBroker, "TSLA", 1000);
		Trade t3 = new Trade(dodgeBroker, "MSFT", 1000);

		assertEquals("Should return -1 for compareTo() when comparing two trades NOT on watchlist when base trade was created first", -1, t1.compareTo(t2));

		assertEquals("Should return 1 for compareTo() when comparing two trades NOT on watchlist when base trade was created second", 1, t3.compareTo(t2));

		assertEquals("Should return 0 for compareTo() when comparing two trades with equal creation times", 0, t2.compareTo(t2));
	}

	/**
	 * These are a second set of tests that after you're passing testTrade_CompareTo(), you can 
	 * now modify compareTo to change the sort order for companies that are on a watchlist.
	 */
	@Test
	public void testTrade_CompareToPriority() {
		
		Marks.getInstance().marks.put(ID + "Trade_CompareToPriority", 5f);

		StockBroker dodgeBroker = new StockBroker("Test Broker");
		dodgeBroker.addWatchlist("DALL");
		
		Trade t1 = new Trade(dodgeBroker, "DALL", 1000);
		Trade t2 = new Trade(dodgeBroker, "TSLA", 1000);
		Trade t3 = new Trade(dodgeBroker, "MSFT", 1000);

		assertEquals("Company on broker's watchlist should return 1 for compareTo() when base trade is on watchlist", 1, t1.compareTo(t2));

		assertEquals("Company on broker's watchlist should return -1 for compareTo() when parameter trade is on watchlist", -1, t2.compareTo(t1));

		assertEquals("Company on broker's watchlist should return 0 for compareTo() when comparing against self", 0, t1.compareTo(t1));

		assertEquals("Should return -1 for compareTo() when comparing two trades NOT on watchlist when base trade was created first", -1, t2.compareTo(t3));

		assertEquals("Should return 1 for compareTo() when comparing two trades NOT on watchlist when parameter trade was created first", 1, t3.compareTo(t2));
	}
}
