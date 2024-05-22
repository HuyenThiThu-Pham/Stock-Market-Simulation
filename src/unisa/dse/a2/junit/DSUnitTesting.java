package unisa.dse.a2.junit;


import java.util.HashMap;

import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

/**
 * @author simont
 * @author james.walsh@unisa.edu.au
 *
 */
public class DSUnitTesting {

	/* 
	 * Display the methods that fail. 
	 */
	@Rule
	public TestRule classWatchman = new TestWatcher() {
		
		@Override
		protected void failed(Throwable e, Description desc) {
			
			String className = desc.getClassName().replaceAll("Test", "").replaceAll("testing.", "");
			String methodName = desc.getMethodName().replaceAll("test","").replaceAll("Test","");
			
			String testID = className + ":" + methodName;
			Marks.getInstance().failed.add(testID);
			System.err.println("FAIL: \t" + testID + ". Mark lost: " + Marks.getInstance().marks.get(testID));
			
		}
		
		@Override
		protected void succeeded(Description desc) {
			
			String className = desc.getClassName().replaceAll("Test", "").replaceAll("testing.", "");
			String methodName = desc.getMethodName().replaceAll("test","").replaceAll("Test","");
			methodName = methodName.replaceAll("\\]","").replaceAll("\\[", "").replaceAll("\\d+","");
			
			String testID = className + ":" + methodName;
		//	System.out.println("Inserting [" + testID + "] into Marks");
			
			Marks.getInstance().passed.add(testID);
		
			HashMap<String, Float> m = Marks.getInstance().marks;
			
//			System.out.print("Searching for: " + testID);
			float mark = m.get(testID);
//			System.out.println("... found");
			System.out.print(String.format("   %-4s %-8s %-33s %-4s", "PASS", 
					className, methodName, mark));
			System.out.print("\n");
		}
	};
	
	
}
