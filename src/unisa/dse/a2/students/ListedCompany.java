package unisa.dse.a2.students;

public class ListedCompany {

	/**
	 * The full name of the company
	 */
	private String name;
	
	public void getName() {
	}

	/**
	 * The listing code of the company
	 */
	private String code;
	
	public void getCode() {
	}

	/**
	 * Current price of the company after last trade
	 */
	private int currentPrice;
	
	public void getCurrentPrice() {
	}
	
	public ListedCompany(String code, String name, int currentPrice)
	{
	}
	
	/**
	 * Processing a trade should increase the current price of the company by 
	 *    quantity / 100
	 * A company's price CANNOT go below 1
	 * 
	 * @param quantity
	 * @return the price after adjustment
	 */
	public void processTrade(int quantity)
	{
	}
}
