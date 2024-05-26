package unisa.dse.a2.students;

import java.util.HashMap;
import java.util.Scanner;

import unisa.dse.a2.interfaces.ListGeneric;

public class SecuritiesExchange {

	/**
	 * Exchange name
	 */
	private String name;
	/**
	 * @return The name of the exchange.
	 */
	
	public String getName() {
		return name;
	}
	
	/**
	 * List of brokers on this exchange
	 */
	public ListGeneric<StockBroker> brokers;
	
	/**
	 * List of announcements of each trade processed
	 */
	public ListGeneric<String> announcements;
	
	/**
	 * HashMap storing the companies, stored based on their company code as the key
	 */
	public HashMap<String, ListedCompany> companies;

	/**
	 * Initialises the exchange ready to handle brokers, announcements, and companies
	 * @param name
	 */
	public SecuritiesExchange(String name)
	{
		this.name = name;
	    this.brokers = new DSEListGeneric<>();
	    this.announcements = new DSEListGeneric<>();
	    this.companies = new HashMap<>();
	}
	
	/**
	 * Adds the given company to the list of listed companies on the exchange
	 * @param company
	 * @return True if the company was successfully added, false otherwise
	 */
	public boolean addCompany(ListedCompany company)
	{
		if (company != null && !companies.containsKey(company.getCode())) {
	        companies.put(company.getCode(), company);
	        return true;
	    }
	    return false;
	}

	/**
	 * Adds the given broke to the list of brokers on the exchange
	 * @param company
	 * @return True if the broker was successfully added, false otherwise
     */
	 */
	public boolean addBroker(StockBroker broker)
	{
		if (broker != null) {
	        return brokers.add(broker);
	    }
	    return false;
	}
	
	/**
	 * Process the next trade provided by each broker, processing brokers starting from index 0 through to the end
	 * 
	 * If the exchange has three brokers, each with trades in their queue, then three trades will processed, one from each broker.
	 * 
	 * If a broker has no pending trades, that broker is skipped
	 * 
	 * Each processed trade should also make a formal announcement of the trade to the announcements list in the form a string:
	 * "Trade: QUANTITY COMPANY_CODE @ PRICE_BEFORE_TRADE via BROKERNAME", 
	 * e.g. "Trade: 100 DALL @ 99 via Honest Harry Broking" for a sale of 100 DALL shares if they were valued at $99
	 * Price shown should be the price of the trade BEFORE it's processed. Each trade should add its announcement at 
	 * the end of the announcements list
	 * 
	 * @return The number of successful trades completed across all brokers
	 * @throws UntradedCompanyException when traded company is not listed on this exchange
	 */
	public int processTradeRound() throws UntradedCompanyException
	{
		// Initialize the count of successful trades
	    int successfulTrades = 0;

	    // Loop through each broker in the list of brokers
	    for (StockBroker broker : brokers) {
	        // Get the next trade from the broker's queue
	        Trade trade = broker.getNextTrade();

	        // If there is a trade to process
	        if (trade != null) {
	            // Get the company code associated with the trade
	            String companyCode = trade.listedCompanyCode;

	            // Check if the company code exists in the companies map
	            if (!companies.containsKey(companyCode)) {
	                // If the company is not listed, throw an exception
	                throw new UntradedCompanyException(companyCode);
	            }

	            // Get the ListedCompany object from the map
	            ListedCompany company = companies.get(companyCode);

	            // Get the quantity of shares to be traded
	            int quantity = trade.getShareQuantity();

	            // Get the current price of the company's shares
	            int currentPrice = company.getCurrentPrice();

	            // Calculate the total price for the quantity of shares
	            int totalPrice = quantity * currentPrice;

	            // Create the announcement string for the trade
	            String announcement = "Trade: " + quantity + " " + companyCode + " @ " + currentPrice + " via " + broker.getName();

	            // Add the announcement to the announcements list
	            announcements.add(announcement);

	            // Increment the count of successful trades
	            successfulTrades++;
	        }
	    }
	    // Return the total number of successful trades
	    return successfulTrades;
	}
	
	/**
     * Runs the command-line interface for the exchange.
     * 
     * @param sc Scanner object for reading input
     * @return The number of successful trades processed
     */
	public int runCommandLineExchange(Scanner sc)
	{
		while (true) {
            System.out.println("Enter a command (addCompany, addBroker, processTrade, exit):");
            String command = sc.nextLine();

            switch (command.toLowerCase()) {
                case "addcompany":
                    System.out.println("Enter company code:");
                    String companyCode = sc.nextLine();
                    System.out.println("Enter company name:");
                    String companyName = sc.nextLine();
                    System.out.println("Enter company initial price:");
                    int initialPrice = Integer.parseInt(sc.nextLine());
                    ListedCompany company = new ListedCompany(companyCode, companyName, initialPrice);
                    addCompany(company);
                    System.out.println("Company added: " + companyName);
                    break;
                case "addbroker":
                    System.out.println("Enter broker name:");
                    String brokerName = sc.nextLine();
                    StockBroker broker = new StockBroker(brokerName);
                    addBroker(broker);
                    System.out.println("Broker added: " + brokerName);
                    break;
                case "processtrade":
                    try {
                        int tradesProcessed = processTradeRound();
                        System.out.println("Processed " + tradesProcessed + " trades.");
                    } catch (UntradedCompanyException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case "exit":
                    System.out.println("Exiting...");
                    return 0;
                default:
                    System.out.println("Invalid command. Try again.");
                    break;
            }
        }
		
	}
}
