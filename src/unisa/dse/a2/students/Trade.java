package unisa.dse.a2.students;

public class Trade implements Comparable<Trade> {

	/*
	 * Don't modify the tradeID or created values, as they're used to simplify the tests
	 * You may be required to use the "created" value in some parts of your code.
	 */
	private long tradeId = -1;
	private long created;  // Time stamp of when the trade was created

	/**
	 * @return 
	 * @return Track the moment in time this Trade was created
	 */
	public long getCreated()
	{
		return created;
	}
	
	private String listedCompanyCode;

	/**
	 * @return 
	 * @return The company's code
	 */
	public String getCompanyCode() {
		return listedCompanyCode;
	}
	
	private int shareQuantity;

	/**
	 * @return 
	 * @return The quantity of shares to trade
	 */
	public int getShareQuantity() {
		return shareQuantity;
	}

	private StockBroker broker;

	/**
	 * @return 
	 * @return The broker associated with this trade
	 */
	public StockBroker getStockBroker() {
		return broker;
	}


	/***
	 * Do not modify this constructor, it is used for testing purposes only
	 */
	public Trade(StockBroker broker, int id)
	{
		created = System.nanoTime(); //do not change this
		tradeId = id; //do not change this
		try { Thread.sleep(100); } catch (Exception x) {}
	}
	
	/***
	 * Constructor to create a new trade with the associated broker, company, and share quantity
	 * DO NOT change the current created and tradeId code
	 * 
	 * @param broker
	 * @param listedCompanyCode
	 * @param shareQuantity
	 */
	public Trade(StockBroker broker, String listedCompanyCode, int shareQuantity)
	{
		this.broker = broker;
		this.listedCompanyCode = listedCompanyCode;
		this.shareQuantity = shareQuantity;
		created = System.nanoTime(); //Record creation time- do not change this
		tradeId = System.nanoTime(); //Use current time as trade ID for uniqueness- do not change this
		try { Thread.sleep(100); } catch (Exception x) {} // Ensure unique time stamp
		
	}
	
	/**
	 * Compares one trade to another trade
	 * 
	 * If we have two trades, A and B, and we examine the company in each trade:
	 *  - if A and B are BOTH on their broker's watchLists, they are equal (return 0)
	 *  - if A is on their brokers list, but B is not on B's brokers list (return 1)
	 *  - if B is on their brokers list, but A is not on A's brokers list (return -1)
	 *  - Otherwise, if neither trade is on their broker's list, then compare 
	 *  		the "created" field, returning -1 if "this" is smaller, 0 if equal, 
	 *  		or 1 if greater
	 * @param t 
	 * @return The ordering priority of the trade
	 */
	public int compareTo(Trade t)
	{
		// Check if both trades are on their broker's watchlists
        boolean thisInWatchlist = this.broker.getWatchlist().contains(this.listedCompanyCode);
        boolean otherInWatchlist = t.getStockBroker().getWatchlist().contains(t.getCompanyCode());

        if (thisInWatchlist && otherInWatchlist) {
            return 0; // Both trades are in the watchlists
        } else if (thisInWatchlist) {
            return 1; // Only this trade is in the watchlist
        } else if (otherInWatchlist) {
            return -1; // Only the other trade is in the watchlist
        } else {
            // Neither trade is in the watchlists, compare by creation time
            return Long.compare(this.created, t.getCreated());
            
        }
	}
	

	/***
	 * Do not modify this toString, it is used for testing purposes
	 */
	@Override
	public String toString() {
		return ""+tradeId;
	}

	/***
	 * Do not modify this equals, it is used for testing purposes
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Trade other = (Trade) obj;
		if (tradeId != other.tradeId)
			return false;
		return true;
	}
	
}
