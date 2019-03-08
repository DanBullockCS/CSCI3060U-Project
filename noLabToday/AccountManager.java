package noLabToday;

/*
* Class handles the users accounts and verifying their information
*
* @author Danooshan Sureshkumar, Matthew Wierzbicki, and Daniel Bullock
* (Team No Lab Today)
* @version 0.1
* @since 2019-03-05
*/

public class AccountManager {
  public String username; // user name of current user
  public String type;     // account type of that user (AA, FS, BS, SS)
  public double credit;   // the amount of credit in that users account

  /*
  * Checks that the user that was being created has a unique username
  * @params: String username - the user name of the current user
  * @returns: nothing
  */
  public void checkUserIntegrity(String username) {}

  /*
  * Get all the account information from the ticket file into mutable data types
  * @params: String username - the user name of the current user
  * @returns: nothing
  */
  public void getUserInformation(String username) {}

}
