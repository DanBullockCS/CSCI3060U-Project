package noLabToday;

import noLabToday.FileHandler;
import java.io.*;

/**
* Class contains the main method that will utilize other classes to run the program
*
* @author Danooshan Sureshkumar, Matthew Wierzbicki, and Daniel Bullock
* (Team No Lab Today)
* @version 0.2
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
			// verifying the format of each file
			if (handlerObj.verifyTransFileFormat() == true &&
			 		handlerObj.verifyUserFileFormat() == true &&
			  	handlerObj.verifyTicketsFileFormat() == true) {
				// Reading the daily trans to do the transactions
				handlerObj.transList = handlerObj.readFile(TRANS_FILE);

				AccountManager acctManager = new AccountManager();
				TicketManager tickManager = new TicketManager();
				String transCode = "";

				for (String line : handlerObj.transList) {
					if (line != null) {
						transCode = line.substring(0, 2);
						if (transCode.trim().equals("00")) {
							System.out.println("Logout occurred on this line");
						} else if (transCode.trim().equals("01")) {
							// Create
							System.out.println("\nStarting Create:");
							handlerObj.userList = acctManager.create(handlerObj.userList, line);
							System.out.println(handlerObj.userList);
						} else if (transCode.trim().equals("02")) {
							// Delete
							System.out.println("\nStarting Delete:");
							handlerObj.userList = acctManager.delete(handlerObj.userList, line);
							System.out.println(handlerObj.userList);
						} else if (transCode.trim().equals("03")) {
							// Sell
							System.out.println("\nStarting Sell:");
							handlerObj.ticketList = tickManager.sell(handlerObj.ticketList, line, handlerObj.userList);
							System.out.println(handlerObj.ticketList);
						} else if (transCode.trim().equals("04")) {
							// Buy
							System.out.println("\nStarting Buy:");
							handlerObj.ticketList = tickManager.buy(handlerObj.ticketList, line, handlerObj.transList, handlerObj.userList);
							System.out.println(handlerObj.ticketList);
							System.out.println(handlerObj.userList);
						} else if (transCode.trim().equals("05")) {
							// Refund
							System.out.println("\nStarting Refund:");
							handlerObj.userList = acctManager.refund(handlerObj.userList, line);
							System.out.println(handlerObj.userList);
						} else if (transCode.trim().equals("06")) {
							// addCredit
							System.out.println("\nStarting addCredit:");
							handlerObj.userList = acctManager.addCredit(handlerObj.userList, line);
							System.out.println(handlerObj.userList);
						}
					}
				}
				// Write the changed lists to the file
				handlerObj.WriteTicketsFile();
				handlerObj.WriteUsersFile();
		} else {
			System.out.println("ERROR: Daily Trans File format is wrong");
		}

		} catch (IOException e) {
			System.err.println("An IOException was caught :" + e.getMessage());
		}
	}
}
