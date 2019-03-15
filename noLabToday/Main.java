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
			// System.out.println(handlerObj.verifyUserFileFormat());
			// System.out.println(handlerObj.verifyTicketsFileFormat());
			// System.out.println(handlerObj.verifyTransFileFormat());
			// handlerObj.storeTicketLineInformation();
			// handlerObj.storeUserLineInformation();
			// Reading the daily trans to do the transactions
			handlerObj.transList = handlerObj.readFile(TRANS_FILE);
			AccountManager acctManager = new AccountManager();
			TicketManager tickManager = new TicketManager();
			String transCode = "";

			try {
				handlerObj.initializeFiles(USER_FILE, STOCK_FILE, TRANS_FILE);
			} catch (IOException e) {
				System.err.println("An IOException was caught :" + e.getMessage());
			}

			for (String line : handlerObj.transList) {
				if (line != null) {
					transCode = line.substring(0, 2);
					if (transCode.trim().equals("00")) {
						// Save buyer for TicketManager class
					} else if (transCode.trim().equals("01")) {
						// Create
						handlerObj.userList = acctManager.create(handlerObj.userList, line);
						System.out.println(handlerObj.userList); // showing that create works
						continue;
					} else if (transCode.trim().equals("02")) {
						// Delete
						handlerObj.userList = acctManager.delete(handlerObj.userList, line);
						System.out.println(handlerObj.userList);
					} else if (transCode.trim().equals("03")) {
						// Sell
						// TODO: Danooshan
						//handlerObj.ticketList = tickManager.sell(handlerObj.ticketList, line);
						//System.out.println(handlerObj.ticketList);
					} else if (transCode.trim().equals("04")) {
						// Buy
						// TODO: Danooshan
						//handlerObj.ticketList = tickManager.buy(handlerObj.ticketList, line);
						//System.out.println(handlerObj.ticketList);
					} else if (transCode.trim().equals("05")) {
						// Refund
						handlerObj.userList = acctManager.refund(handlerObj.userList, line);
						System.out.println(handlerObj.userList);
					} else if (transCode.trim().equals("06")) {
						// addCredit
						handlerObj.userList = acctManager.addCredit(handlerObj.userList, line);
						System.out.println(handlerObj.userList);
					} else {

					}
				}
			}

		} catch (IOException e) {
			System.err.println("An IOException was caught :" + e.getMessage());
		}
	}
}
