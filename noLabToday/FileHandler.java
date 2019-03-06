package noLabToday;
import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.nio.*;

/*
* Class handles reading and writing to the various ticket/user/daily trans files
*
* @author Danooshan Sureshkumar, Matthew Wierzbicki, and Daniel Bullock
* (Team No Lab Today)
* @version 0.1
* @since 2019-03-05
*/

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
  public static List<String> readFile(String fileName) throws IOException {
	  InputStream is = new FileInputStream(fileName);
	  BufferedReader buf = new BufferedReader(new InputStreamReader(is));

	  List<String> fileAsString = new ArrayList<String>();
	  String line = "notempty";
	  while(line != null)
	  {
		  line = buf.readLine();
		  if(line != null)
		  {
			  fileAsString.add(line);
		  }

	  }
	  System.out.println(fileAsString.toString());
	  return fileAsString;
  }
  public boolean verifyFileFormat(String fileName) throws java.io.IOException
  {

	  List<String> lineList = readFile(fileName);
	  String username = "";
	  String type = "";
	  String credit = "";
	  boolean check = true;
	  for (String line : lineList)
	  {
		  if(line != null)
		  {
			  username = line.substring(1,15);
			  type = line.substring(16,18);
			  credit = line.substring(19,28);

			  System.out.println(line.length());
			  System.out.println(type);

			  if(line.length() != 28)
			  {
				  check = false;
			  }
	    	  else if (type != "AA" && type != "FS" && type != "BS" && type != "SS")
			  {
		    	 check = false;
			  }
		  }

      }

	  return check;
  }

  public void readAllFiles(String fileNames[]) {


  }
}
