package unisa.dse.a2.marking;

import java.util.HashMap;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import unisa.dse.a2.junit.ListGenericTest;
import unisa.dse.a2.junit.ListTest;
import unisa.dse.a2.junit.ListedCompanyTest;
import unisa.dse.a2.junit.Marks;
import unisa.dse.a2.junit.SecuritiesExchangeTest;
import unisa.dse.a2.junit.StockBrokerTest;
import unisa.dse.a2.junit.TradeTest;
import unisa.dse.a2.students.ListedCompany;

/**
 * @author simont
 * @author james.walsh@unisa.edu.au
 *
 */
public class AssignmentMarker {

	private static java.util.ArrayList<Failure> failures;


	private static void testrunner(String name, Class c) {

		Result test = JUnitCore.runClasses(c);
		failures.addAll(test.getFailures());

		for ( int i = 0 ; i < test.getFailures().size() ; ++i ) {
			String testID = test.getFailures().get(i).getDescription().getClassName() + ":" + 
					test.getFailures().get(i).getDescription().getMethodName();
			testID = testID.replaceAll("Test", ""). replaceAll("test", ""); // Strip the word "test" from the identifying string. 
		}
	}

	// Simple test information
	private static void runATest(String name, Class c) {
		System.out.println("\n" + name);
		for ( int i = 0 ; i < name.length() ; ++i ) 
			System.out.print("-");
		System.out.println();

		testrunner(name, c);
	}

	public static void main(String[] args) {

		boolean runList = true;
		boolean runGenericList = true;
		boolean runLC = true;
		boolean runTrade = true;
		boolean runBroker = true;
		boolean runExchange = true;
		
		failures = new java.util.ArrayList<Failure>();

		float listPass = 0, listLost = 0;
		float listGenericPass = 0, listGenericLost = 0;
		float lcPass = 0, lcLost = 0;
		float tradePass = 0, tradeLost = 0;
		float brokerPass = 0, brokerLost = 0;
		float exchangePass = 0, exchangeLost = 0;
		
		
		System.out.println("Data Structures Essentials Assignment #1:\n\tGameStonks.\n");

		System.out.println("-----------------------------");

		if ( runList ) {
			runATest("Testing the List class...", ListTest.class);

			System.out.print("Summary: ");
			{
				float gained = 0.0f;
				for ( int i = 0 ; i < Marks.getInstance().passed.size(); ++i ) {
					if ( Marks.getInstance().passed.get(i).contains("List:") )
					{
						Marks m = Marks.getInstance();
						String s = m.passed.get(i);
						HashMap<String, Float> map = m.marks;
						gained += map.get(s);
					}
				}

				float lost = 0.0f;
				for ( int i = 0 ; i < Marks.getInstance().failed.size(); ++i ) {
					if ( Marks.getInstance().failed.get(i).contains("List:") )
						lost += Marks.getInstance().marks.get(Marks.getInstance().failed.get(i));
				}
				listPass = gained;
				listLost = lost;
				System.out.println(gained + " marks gained, " + lost + " marks lost.");
			}
		}
		
		if ( runGenericList ) {
			runATest("Testing the List Generic class...", ListGenericTest.class);

			System.out.print("Summary: ");
			{
				float gained = 0.0f;
				for ( int i = 0 ; i < Marks.getInstance().passed.size(); ++i ) {
					if ( Marks.getInstance().passed.get(i).contains("ListGeneric:") )
					{
						Marks m = Marks.getInstance();
						String s = m.passed.get(i);
						HashMap<String, Float> map = m.marks;
						gained += map.get(s);
					}
				}

				float lost = 0.0f;
				for ( int i = 0 ; i < Marks.getInstance().failed.size(); ++i ) {
					if ( Marks.getInstance().failed.get(i).contains("ListGeneric:") )
						lost += Marks.getInstance().marks.get(Marks.getInstance().failed.get(i));
				}
				listGenericPass = gained;
				listGenericLost = lost;
				System.out.println(gained + " marks gained, " + lost + " marks lost.");
			}
		}

		if ( runLC ) {
			runATest("Testing the ListedCompany class..", ListedCompanyTest.class);

			System.out.print("Summary: ");
			{
				float gained = 0.0f;
				for ( int i = 0 ; i < Marks.getInstance().passed.size(); ++i ) {
					if ( Marks.getInstance().passed.get(i).contains("ListedCompany:") )
						gained += Marks.getInstance().marks.get(Marks.getInstance().passed.get(i));
				}

				float lost = 0.0f;
				for ( int i = 0 ; i < Marks.getInstance().failed.size(); ++i ) {
					if ( Marks.getInstance().failed.get(i).contains("ListedCompany:") )
					{
						Marks m = Marks.getInstance();
						String s = m.failed.get(i);
						lost += m.marks.get(s);
					}
				}
				lcPass = gained;
				lcLost = lost;
				System.out.println(gained + " marks gained, " + lost + " marks lost.");
			}

		}

		
		if ( runLC ) {
			runATest("Testing the Trade class..", TradeTest.class);

			System.out.print("Summary: ");
			{
				float gained = 0.0f;
				for ( int i = 0 ; i < Marks.getInstance().passed.size(); ++i ) {
					if ( Marks.getInstance().passed.get(i).contains("Trade:") )
						gained += Marks.getInstance().marks.get(Marks.getInstance().passed.get(i));
				}

				float lost = 0.0f;
				for ( int i = 0 ; i < Marks.getInstance().failed.size(); ++i ) {
					if ( Marks.getInstance().failed.get(i).contains("Trade:") )
						lost += Marks.getInstance().marks.get(Marks.getInstance().failed.get(i));
				}
				tradePass = gained;
				tradeLost = lost;
				System.out.println(gained + " marks gained, " + lost + " marks lost.");
			}

		}

		
		if ( runBroker ) {
			runATest("Testing the StockBroker class..", StockBrokerTest.class);

			System.out.print("Summary: ");
			{
				float gained = 0.0f;
				for ( int i = 0 ; i < Marks.getInstance().passed.size(); ++i ) {
					if ( Marks.getInstance().passed.get(i).contains("StockBroker:") )
						gained += Marks.getInstance().marks.get(Marks.getInstance().passed.get(i));
				}

				float lost = 0.0f;
				for ( int i = 0 ; i < Marks.getInstance().failed.size(); ++i ) {
					if ( Marks.getInstance().failed.get(i).contains("StockBroker:") )
						lost += Marks.getInstance().marks.get(Marks.getInstance().failed.get(i));
				}
				brokerPass = gained;
				brokerLost = lost;
				System.out.println(gained + " marks gained, " + lost + " marks lost.");
			}

		}


		
		if ( runExchange ) {
			runATest("Testing the SecuritiesExchange class..", SecuritiesExchangeTest.class);

			System.out.print("Summary: ");
			{
				float gained = 0.0f;
				for ( int i = 0 ; i < Marks.getInstance().passed.size(); ++i ) {
					if ( Marks.getInstance().passed.get(i).contains("SecuritiesExchange:") )
						gained += Marks.getInstance().marks.get(Marks.getInstance().passed.get(i));
				}

				float lost = 0.0f;
				for ( int i = 0 ; i < Marks.getInstance().failed.size(); ++i ) {
					if ( Marks.getInstance().failed.get(i).contains("SecuritiesExchange:") )
						lost += Marks.getInstance().marks.get(Marks.getInstance().failed.get(i));
				}
				exchangePass = gained;
				exchangeLost = lost;
				System.out.println(gained + " marks gained, " + lost + " marks lost.");
			}

		}
		



		System.out.println("-----------------------------");
		System.out.println("\nFailed test details");
		System.out.println("( Test class: test name -> Error details)\n");
		for (Failure failure : failures) {
			String name = failure.getDescription().getClassName().replaceAll("Test",  "") 
					+ ": " +  failure.getDescription().getMethodName();
			System.out.print(name + " -> ");
			if ( failure.getMessage() != null )
				System.out.print(failure.getMessage());
			else
				System.out.print("No failure message present " +
						"(indicates systemic issues somewhere in the codebase)." +
						"\nTrace: " + failure.getTrace());
			System.out.print("\n");
		}
		
		
		System.out.println("-----------------------------");
		System.out.println("Mark summary:");
		System.out.println("\tList:  [gained " + listPass + ", lost " + listLost + "]");
		System.out.println("\tListGeneric:  [gained " + listGenericPass + ", lost " + listGenericLost + "]");
		System.out.println("\tListedCompany:  [gained " + lcPass + ", lost " + lcLost + "]");
		System.out.println("\tTrade:  [gained " + tradePass + ", lost " + tradeLost + "]");
		System.out.println("\tStockBroker:  [gained " + brokerPass + ", lost " + brokerLost + "]");
		System.out.println("\tStockExchange:  [gained " + exchangePass + ", lost " + exchangeLost + "]");

		float pass = listPass + listGenericPass + lcPass + tradePass + brokerPass + exchangePass;
		float lost = listLost + listGenericLost + lcLost + tradeLost + brokerLost + exchangeLost;
		float total = pass + lost;
		
		System.out.println("\nOverall pass percent: " + (pass / total * 100) + "%");

		//		System.out.println("Total: [" + (hP + aP + gP + mP) + ", lost " + (mL + hL + aL + gL) +"] (out of: " + (mP + hP + aP + gP + mL + hL + aL + gL) + ")");
	}

}  
