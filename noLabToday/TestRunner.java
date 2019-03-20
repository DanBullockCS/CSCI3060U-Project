package noLabToday;
/**
* Class contains a main to run all the tests in TestJunit.java
*
* @author Danooshan Sureshkumar, Matthew Wierzbicki, and Daniel Bullock
* (Team No Lab Today)
* @version 0.2
* @since 2019-03-10
*/
// Junit Class to run all our tests

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
  public static void main(String[] args) {
    Result result = JUnitCore.runClasses(noLabToday.TestJunit.class);

    for (Failure failure : result.getFailures()) {
    	 System.out.println(failure.toString());
    }

    System.out.println("\nTests Ran Successful = " + result.wasSuccessful());
  }
}
