/*
* Class handles reading and writing to the various ticket/user/daily trans files
*
* @author Danooshan Sureshkumar, Matthew Wierzbicki, and Daniel Bullock
* (Team No Lab Today)
* @version 0.1
* @since 2019-03-05
*/

import java.util.List;
import java.util.ArrayList;

public class FileHandler {
  public String fileName;

  public List<String> updateTickets() {
    List<String> ticketList = new ArrayList<String>();
    return ticketList;
  }

  public List<String> updateUsers() {
    List<String> userList = new ArrayList<String>();
    return userList;
  }

  public void verifyFileFormat(String fileName) {}

  public void readFiles(String fileName) {}
}
