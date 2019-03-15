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
  * @return: The List<String> filehandler.userList with the newly created user
  */
  public List<String> create(List<String> userList, String trans_line) {
    try {
			filehandler.initializeFiles(USER_FILE, STOCK_FILE, TRANS_FILE);
		} catch (IOException e) {
			System.err.println("An IOException was caught :" + e.getMessage());
		}

    // get trans_line user info in seperate variables
    username = trans_line.substring(3, 19);
    type = trans_line.substring(19, 22);
    String credit_string = trans_line.substring(22, 31);

    // combine the user info
    String combined_user = username + type + credit_string;

    // check if user is in users.ua, if not, add (create)
    if (checkUserIntegrity(username) == true) {
      System.out.println("Creating user: " + username);
      filehandler.userList.add(combined_user);
    }

    return filehandler.userList;
  }

  /**
  * Delete a user passed in from the daily trans file
  * @param List<String> userList - a list of all the users in the system
  * @param String trans_line - containing the current line from the daily trans file
  * @return: The List<String> filehandler.userList without the deleted user
  */
  public List<String> delete(List<String> userList, String trans_line) {
    try {
			filehandler.initializeFiles(USER_FILE, STOCK_FILE, TRANS_FILE);
		} catch (IOException e) {
			System.err.println("An IOException was caught :" + e.getMessage());
		}
    // finds the username and delete that index
    int i = 0, deletion_index = 0;
    username = trans_line.substring(3, 19);

    // get userinfo in userList
    for (String line : filehandler.userList) {
			if (line != null) {
        if (line.contains(username)) {
          deletion_index = i;
        }
        i++;
      }
		}

    // check if user is in users.ua, if it is, remove (delete)
    if (checkUserIntegrity(username) == false) {
      System.out.println("Deleting user: " + username);
     // full List
      filehandler.userList.remove(filehandler.userList.get(deletion_index));
      // sublists
      // filehandler.usernamesTicketFileList.remove(filehandler.userList.get(deletion_index));
      // filehandler.typeList.remove(filehandler.userList.get(deletion_index));
      // filehandler.creditsList.remove(filehandler.userList.get(deletion_index));
    }

    return filehandler.userList;
  }

  /**TODO
  * Delete a user passed in from the daily trans file
  * @param List<String> userList - a list of all the users in the system
  * @param String trans_line - containing the current line from the daily trans file
  * @return: The List<String> filehandler.userList with the user refunded their credits
  */
  public List<String> refund(List<String> userList, String trans_line) {
    return filehandler.userList;
  }

  /**TODO
  * Delete a user passed in from the daily trans file
  * @param List<String> userList - a list of all the users in the system
  * @param String trans_line - containing the current line from the daily trans file
  * @return: The List<String> filehandler.userList with the user added more credit
  */
  public List<String> addCredit(List<String> userList, String trans_line) {
    try {
			filehandler.initializeFiles(USER_FILE, STOCK_FILE, TRANS_FILE);
		} catch (IOException e) {
			System.err.println("An IOException was caught :" + e.getMessage());
		}

    // get trans_line user info in seperate variables
    username = trans_line.substring(3, 19);
    type = trans_line.substring(19, 22);
    credit = Double.parseDouble(trans_line.substring(22, 31));
    String credit_string = trans_line.substring(22, 31);

    // combine the user info
    String combined_user = username + type + credit_string;
    // check if user is in users.ua, if not, add (create)
    if (checkUserIntegrity(username) == true) {
      System.out.println("Adding " + credit_string + " to user: " + username);
      // remove from the list
      filehandler.userList.remove(combined_user);

      // add back the user with credit added
      combined_user = "";

      filehandler.userList.add(combined_user);
    }
    return filehandler.userList;
  }

}
