package unisa.dse.a2.marking;

import unisa.dse.a2.students.ListedCompany;
import unisa.dse.a2.students.SecuritiesExchange;
import unisa.dse.a2.students.StockBroker;
import unisa.dse.a2.students.Trade;
import unisa.dse.a2.students.UntradedCompanyException;

public class Driver {

	SecuritiesExchange exchange;

	public static void main(String[] args) {

		Driver d = new Driver();

	}

	public Driver() {
		
		exchange = new SecuritiesExchange("ASX");
		
		StockBroker harry = new StockBroker("Honest Harry Broking");
		exchange.addBroker(harry);
		
		StockBroker dave = new StockBroker("Dodge Dave Broking");
		exchange.addBroker(dave);
		
		ListedCompany dall = new ListedCompany("DALL", "Dall Computers Limited", 1000);
		ListedCompany gs = new ListedCompany("GME", "GameStonk", 100);
		ListedCompany tisla = new ListedCompany("TSLA", "Tisla Intergalactic", 9000);
		ListedCompany wiki = new ListedCompany("WIKI", "Wikipedia", 0);
		
		exchange.addCompany(dall);
		exchange.addCompany(gs);
		exchange.addCompany(tisla);
		
		harry.placeOrder(new Trade(harry, "DALL", 100));
		dave.placeOrder(new Trade(dave, "TSLA", 100));
		
		try
		{
			exchange.processTradeRound();
			
			for (int i = 0; i < exchange.announcements.size(); i++)
			{
				System.out.println(exchange.announcements.get(i));
			}
		} 
		catch (UntradedCompanyException x)
		{
			System.out.println(x.getMessage());
		}
		
		
	}

}
