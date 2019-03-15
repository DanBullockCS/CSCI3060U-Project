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
  public static final String USER_FILE = "./files/users.ua";
	public static final String STOCK_FILE = "./files/stock.at";
	public static final String TRANS_FILE = "./files/trans.out";

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
        System.out.println("User exists in database, ok to create.");
  			return true;
      }
    }
    // User does not exist in users.ua
    System.out.println("ERROR: User to be created already exists in the database!");
    return false;
  }

  public List<String> create(List<String> userList, String trans_line) {
    try {
			filehandler.initializeFiles(USER_FILE, STOCK_FILE, TRANS_FILE);
		} catch (IOException e) {
			System.err.println("An IOException was caught :" + e.getMessage());
		}

    // get userinfo in userList
    for (String line : userList) {
			if (line != null) {
				filehandler.usernamesTicketFileList.add(line.substring(0, 15).trim());
				filehandler.typeList.add(line.substring(16, 18).trim());
				filehandler.creditsList.add(Double.parseDouble(line.substring(19, 28).trim()));
			}
		}

    // get trans_line info in seperate variables
    username = trans_line.substring(3, 17);
    type = trans_line.substring(18, 21);
    credit = Double.parseDouble(trans_line.substring(22, 30));

    // combine the user information
    String combined_user = username + type + String.valueOf(credit);

    // add the user to the userList
    filehandler.userList.add(combined_user);
    System.out.println("Da list is: " + filehandler.userList);
    return filehandler.userList;
  }

  public List<String> delete(List<String> userList, String trans_line) {
    return filehandler.userList;
  }

  public List<String> refund(List<String> userList, String trans_line) {
    return filehandler.userList;
  }

  public List<String> addCredit(List<String> userList, String trans_line) {
    return filehandler.userList;
  }

}
