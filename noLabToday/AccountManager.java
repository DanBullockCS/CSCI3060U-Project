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

// get filenames from main
public class AccountManager extends Main {

  public FileHandler filehandler = new FileHandler(); // FileHandler class
  public String username; // user name of current user
  public String type;     // account type of that user (AA, FS, BS, SS)
  public double credit;   // the amount of credit in that users account

  /**
  * Checks that the user that was being created has a unique username
  * @param String username - the user name of the current user
  * @return: True if: User to be created does NOT exist in database
  *          False if: User to be created does exists in database
  */
  public boolean checkUserIntegrity(String username) {
    List<String> account_file = new ArrayList<String>();
    try {
      account_file = filehandler.readFile(USER_FILE);
    }
    catch (IOException e)
    {
      System.err.println("ERROR: An IOException was caught :"+ e.getMessage());
    }

    // check if username is already in users.ua
    for (String s: account_file) {
      if (s.contains(username)) {
        System.out.println("User exists in the database.");
  			return false;
      }
    }
    // User does not exist in users.ua
    System.out.println("User is not in database.");
    return true;
  }

  /**
  * Create the new user passed in from the daily trans file
  * @param List<String> userList - a list of all the users in the system
  * @param String trans_line - containing the current line from the daily trans file
  * @return: The List<String> userList with the newly created user
  */
  public List<String> create(List<String> userList, String trans_line) {
    // get trans_line user info in seperate variables
    username = trans_line.substring(3, 19);
    type = trans_line.substring(19, 22);
    String credit_string = trans_line.substring(22, 31);

    // combine the user info
    String combined_user = username + type + credit_string;

    // check if user is in users.ua, if not, add (create)
    if (checkUserIntegrity(username) == true) {
      System.out.println("Creating user: " + username + "...");
      userList.add(combined_user);
    }

    return userList;
  }

  /**
  * Delete a user passed in from the daily trans file
  * @param List<String> userList - a list of all the users in the system
  * @param String trans_line - containing the current line from the daily trans file
  * @return: The List<String> userList without the deleted user
  */
  public List<String> delete(List<String> userList, String trans_line) {
    // finds the username and delete that index
    int i = 0, deletion_index = 0;
    username = trans_line.substring(3, 19);

    // get userinfo in userList
    for (String line : userList) {
			if (line != null) {
        if (line.contains(username)) {
          deletion_index = i;
        }
        i++;
      }
		}

    // check if user is in users.ua, if it is, remove (delete)
    if (checkUserIntegrity(username) == false) {
      System.out.println("Deleting user: " + username + "...");
      userList.remove(userList.get(deletion_index));
    }

    return userList;
  }

  /**TODO
  * refund the users passed in from the daily trans file
  * @param List<String> userList - a list of all the users in the system
  * @param String trans_line - containing the current line from the daily trans file
  * @return: The List<String> userList with the user refunded their credits
  */
  public List<String> refund(List<String> userList, String trans_line) {
    return userList;
  }

  /**
  * Addcredit to a user passed in the daily trans file
  * @param List<String> userList - a list of all the users in the system
  * @param String trans_line - containing the current line from the daily trans file
  * @return: The List<String> userList with the user added more credit
  */
  public List<String> addCredit(List<String> userList, String trans_line) {
    // get trans_line user info in seperate variables
    username = trans_line.substring(3, 19);
    type = trans_line.substring(19, 22);
    credit = Double.parseDouble(trans_line.substring(22, 31));
    String credit_string = trans_line.substring(22, 31);

    // find the user who is getting the addCredit
    int i = 0, index = 0;
    for (String line : userList) {
			if (line != null) {
        if (line.contains(username)) {
          index = i;
        }
        i++;
      }
    }

    // the credit string of the user getting credit added to them
    String old_credit_string = userList.get(index).substring(19, 28).trim();
    Double old_credit = Double.parseDouble(userList.get(index).substring(19, 28).trim());
    Double new_credit = old_credit + credit;
    // add the leading zeros to the string
    String new_credit_string = ("00000000" + String.valueOf(new_credit)).substring(String.valueOf(new_credit).length());
    // if only one decimal place in string add another "0"
    if (new_credit_string.length() == 8) {
      new_credit_string += "0";
    }

    // combine the user info
    String combined_user = username + type + credit_string;
    // check if user is in users.ua, if not, add (create)
    if (checkUserIntegrity(username) == false) {
      System.out.println("Adding " + credit_string + " to user: " + username + "...");
      // remove from the list
      userList.remove(combined_user);

      // add back the user with credit added
      combined_user = username + type + new_credit_string;
      userList.add(combined_user);
    }
    return userList;
  }

}
