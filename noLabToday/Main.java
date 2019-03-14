package noLabToday;
import noLabToday.FileHandler;
import java.io.*;

/**
* Class contains the main method that will utilize other classes to run the program
*
* @author Danooshan Sureshkumar, Matthew Wierzbicki, and Daniel Bullock
* (Team No Lab Today)
* @version 0.1
* @since 2019-03-05
*/

public class Main {
  // Testing the verify and readFile functions for the various files
  public static final String USER_FILE = "./files/users.ua";
  public static final String TICKETS_FILE = "./files/stock.at";
  public static final String DAILY_TRANS_FILE = "./files/trans.out";
  public static void main(String[] args)
  {
    FileHandler handlerObj = new FileHandler();

    try
    {
      System.out.println(handlerObj.verifyUserFileFormat(USER_FILE));
      System.out.println(handlerObj.verifyTicketsFileFormat(TICKETS_FILE));
      System.out.println(handlerObj.verifyTransFileFormat(DAILY_TRANS_FILE));
    }
    catch (IOException e)
    {
      System.err.println("ERROR: An IOException was caught :"+ e.getMessage());
    }
  }
}
