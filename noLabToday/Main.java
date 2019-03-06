package noLabToday;
import noLabToday.FileHandler;
import java.io.*;

/*
* Class contains the main method that will utilize other classes to run the program
*
* @author Danooshan Sureshkumar, Matthew Wierzbicki, and Daniel Bullock
* (Team No Lab Today)
* @version 0.1
* @since 2019-03-05
*/

public class Main {
  public static final String USER_FILE = "./files/users.ua";
  public static void main(String[] args)
  {
    FileHandler handlerObj = new FileHandler();

    try
    {
    	handlerObj.readFile(USER_FILE);
      System.out.println(handlerObj.verifyFileFormat(USER_FILE));
    }
    catch (IOException e)
    {
      System.err.println("An IOException was caught :"+ e.getMessage());
    }
  }
}
