package unisa.dse.a2.junit;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import unisa.dse.a2.students.DSEListGeneric;
import unisa.dse.a2.students.StockBroker;
import unisa.dse.a2.students.Trade;

/**
 * @author james.walsh@unisa.edu.au
 *
 */
public class StockBrokerTest extends DSUnitTesting {

	private static String ID = "unisa.dse.a2.junit.StockBroker:";
	

	@Before
	public void setUp() {
	}

	@Test
	public void testStockBroker_IDConstructor() {
		Marks.getInstance().marks.put(ID + "StockBroker_IDConstructor", 1f);

		StockBroker sarah = new StockBroker("Sarah Securities");

		assertEquals("Unexpected broker name", "Sarah Securities", sarah.getName());
	}

	@Test
	public void testStockBroker_Equals() {
		Marks.getInstance().marks.put(ID + "StockBroker_Equals", 1f);

		StockBroker sarah = new StockBroker("Sarah Securities");
		StockBroker bob = new StockBroker("Bobs Bargains");

		assertEquals("Calling equals on same StockBroker object with itself should be equal", sarah, sarah);

		assertNotEquals("Calling equals on different StockBroker object should not be equal", sarah, bob);
	}

	@Test
	public void testStockBroker_GetWatchList() {
		
		Marks.getInstance().marks.put(ID + "StockBroker_GetWatchList", 1f);

		StockBroker dodgeBroker = new StockBroker("Test Broker");
		
		DSEListGeneric<String> originalList = dodgeBroker.getWatchlist();
		assertEquals("Unmodified watchlist should have 0 size", 0, originalList.size());
		
		originalList.add("DALL");

		assertEquals("getWatchList should return a deep copy of the watchlist, modifying returned list changed side of broker's list", 0, dodgeBroker.getWatchlist().size());
	}

	@Test
	public void testStockBroker_AddWatchList() {
		
		Marks.getInstance().marks.put(ID + "StockBroker_AddWatchList", 1f);

		StockBroker dodgeBroker = new StockBroker("Test Broker");
		
		dodgeBroker.addWatchlist("DALL");

		DSEListGeneric<String> modifiedList = dodgeBroker.getWatchlist();
		
		assertEquals("Added item to watchlist but size of getWatchlist() did not increase", 1, modifiedList.size());
	}

	@Test
	public void testStockBroker_PlaceOrder() {
		
		Marks.getInstance().marks.put(ID + "StockBroker_PlaceOrder", 1f);

		StockBroker dodgeBroker = new StockBroker("Test Broker");

		assertFalse("Placing order should return false when adding null trade", dodgeBroker.placeOrder(null));
		
		Trade t1 = new Trade(dodgeBroker, "DALL", 1000);
		Trade t2 = new Trade(dodgeBroker, "TSLA", 1000);
		
		assertTrue("Placing order should return true when trade added to queue", dodgeBroker.placeOrder(t1));
		
		assertTrue("Placing order should return true when subsequent trade added to queue", dodgeBroker.placeOrder(t2));

		assertFalse("Placing order should return false when adding trade that's already in the queue", dodgeBroker.placeOrder(t1));

		assertFalse("Placing order should return false when adding trade that's already in the queue", dodgeBroker.placeOrder(t2));
	}

	@Test
	public void testStockBroker_GetPendingTradeCount() {
		
		Marks.getInstance().marks.put(ID + "StockBroker_GetPendingTradeCount", 2f);

		StockBroker dodgeBroker = new StockBroker("Test Broker");

		assertEquals("Pending trades for new broker should be 0", 0, dodgeBroker.getPendingTradeCount());
		
		Trade t1 = new Trade(dodgeBroker, "DALL", 1000);
		Trade t2 = new Trade(dodgeBroker, "TSLA", 1000);
		
		dodgeBroker.placeOrder(t1);

		assertEquals("Pending trades for broker should be 1", 1, dodgeBroker.getPendingTradeCount());
		
		dodgeBroker.placeOrder(t2);

		assertEquals("Pending trades for broker should be 2", 2, dodgeBroker.getPendingTradeCount());
		
		dodgeBroker.placeOrder(t1);

		assertEquals("Pending trades shouldn't increase when adding duplicate trade order", 2, dodgeBroker.getPendingTradeCount());
	}

	@Test
	public void testStockBroker_GetNextTrade() {
		
		Marks.getInstance().marks.put(ID + "StockBroker_GetNextTrade", 2f);

		StockBroker dodgeBroker = new StockBroker("Test Broker");

		assertEquals("Pending trades for new broker should be 0", 0, dodgeBroker.getPendingTradeCount());
		
		Trade t1 = new Trade(dodgeBroker, "DALL", 1000);
		Trade t2 = new Trade(dodgeBroker, "TSLA", 1000);
		
		dodgeBroker.placeOrder(t1);

		assertEquals("Pending trades for broker should be 1", 1, dodgeBroker.getPendingTradeCount());
		
		dodgeBroker.placeOrder(t2);

		assertEquals("Pending trades for broker should be 2", 2, dodgeBroker.getPendingTradeCount());
		
		dodgeBroker.placeOrder(t1);

		assertEquals("Pending trades shouldn't increase when adding duplicate trade order", 2, dodgeBroker.getPendingTradeCount());
	}
	
}
