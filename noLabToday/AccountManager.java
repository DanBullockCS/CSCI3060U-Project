package noLabToday;
import java.io.*;
import java.util.List;
import java.util.ArrayList;

/**
* Class handles the users accounts and verifying their information
*
* @author Danooshan Sureshkumar, Matthew Wierzbicki, and Daniel Bullock
* (Team No Lab Today)
* @version 0.1
* @since 2019-03-05
*/

public class AccountManager {

  public FileHandler filehandler = new FileHandler();
  public String username; // user name of current user
  public String type;     // account type of that user (AA, FS, BS, SS)
  public double credit;   // the amount of credit in that users account

  /**
  * Checks that the user that was being created has a unique username
  * @param String username - the user name of the current user
  * @return: True if: Tickets are greater or equal to zero
  *          False if: Tickets are less than zero
  */
  public boolean checkUserIntegrity(String username) {
    List<String> account_file = new ArrayList<String>();
    try {
      account_file = filehandler.readFile("./files/users.ua");
    }
    catch (IOException e)
    {
      System.err.println("ERROR: An IOException was caught :"+ e.getMessage());
    }

    // check if username is already in users.ua
    if (account_file.contains(username)) {
			return true;
		} else {
			System.out.println("ERROR: User to be created already exists in the database!");
			return false;
		}
  }

  /**
  * Get all the account information from the ticket file into mutable data types
  * @param String username - the user name of the current user
  * @return: nothing
  */
  public void getUserInformation(String username) {

  }

}
