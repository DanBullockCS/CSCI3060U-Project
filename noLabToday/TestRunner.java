package noLabToday;
// Junit Class to run all our tests, might have to add package to these classes

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
  public static void main(String[] args) {
    Result result = JUnitCore.runClasses(noLabToday.TestJunit.class);

    for (Failure failure : result.getFailures()) {
    	 System.out.println(failure.toString());
    }

    System.out.println("Test run successful: " + result.wasSuccessful());
  }
}
