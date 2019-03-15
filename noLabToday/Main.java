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
	public static final String STOCK_FILE = "./files/stock.at";
	public static final String TRANS_FILE = "./files/trans.out";
	public static final String ALL_FILES[] = { USER_FILE, STOCK_FILE, TRANS_FILE };

	public static void main(String[] args) {
		FileHandler handlerObj = new FileHandler();

		try {
			handlerObj.initializeFiles(USER_FILE, STOCK_FILE, TRANS_FILE);
			System.out.println(handlerObj.verifyUserFileFormat());
			System.out.println(handlerObj.verifyTicketsFileFormat());
			System.out.println(handlerObj.verifyTransFileFormat());
			handlerObj.storeTicketLineInformation();
			handlerObj.storeUserLineInformation();
		} catch (IOException e) {
			System.err.println("An IOException was caught :" + e.getMessage());
		}
	}
}
