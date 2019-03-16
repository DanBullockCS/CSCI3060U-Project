package noLabToday;
import java.io.*;
import java.util.List;
import java.util.ArrayList;

/**
* Class handles the tickets in the system, verifying their validity
*
* @author Danooshan Sureshkumar, Matthew Wierzbicki, and Daniel Bullock
* (Team No Lab Today)
* @version 0.1
* @since 2019-03-05
*/

public class TicketManager {
  public FileHandler filehandler = new FileHandler(); // FileHandler class
  public String eventName; //Title of the event on the ticket
  public double price; // Price of the ticket
  public String seller; // Name of the user selling the ticket
  public int numTickets; // Number of tickets remaining

	/**
		This function is used to check the constraint of having negative tickets, and updates numTickets remaining for the specific event.
		@param int ticketsPurchased - The number of tickets purchased
		@return: True if: Tickets are greater or equal to zero
					   False if: Tickets are less than zero
	*/
  public boolean checkNegativeTickets(int ticketsPurchased) {
		int remainingTickets = numTickets - ticketsPurchased;
		if (remainingTickets >= 0) {
			numTickets -= ticketsPurchased;
			return true;
		}
		else {
			System.out.println("ERROR:  Insufficient tickets available for purchase!");
			return false;
		}
	}

	/**
	* This function is used to check the constraint of having duplicate events.
	* @param: nothing
	* @return: true: if a duplicate event is found.
						 false: if no duplicate event is found.
	*/
  public boolean checkDuplicateEvent(List<String> ticketList, String eventName) {
		int count = 0;
		for (String line : ticketList) {
			if (line != null){
				if (line.substring(0, 26).trim() == eventName){
					count += 1; 
				}
				if (count > 1){
					return true;
				}			
			}
		}
		return false;
	}
	/**
	* This function finds the username of the buyer for the buy transaction specifically for the buy transaction
	* @param List<String> transList - a list of all the transactions in the system
	* @param String trans_line - containing the current line from the daily trans file.
	* @return: username of the buyer
	*/
	public String findBuyer(List<String> transList, String trans_line){
		String name = "";
		boolean found = false;
		for (String line : transList){
			if (line != null){
				if (found == false){
					if (line.equals(trans_line)){
						found = true;
					}
				} else {
					if (line.substring(0,2).trim().equals("00")){
						name = line.substring(3, 19);
						return name;
					}
				}
			}
		}
		return name;
	}

  /** TODO Danooshan
  * do buy transaction in ticketList
  * @param List<String> ticketList - a list of all the tickets in the system
  * @param String trans_line - containing the current line from the daily trans file
	* @param List<String> transList - a list of all the transactions in the system
  * @return: The List<String> ...
  */
  public List<String> buy(List<String> ticketList, String trans_line, List<String> transList) {
		String buyerUsername = findBuyer(transList, trans_line);

		//Current ticketList line information
		String eventName;
		String sellersUsername;
		int amountOfTickets;
		double ticketPrice;

		//Current transaction line information
		String trans_line_eventName = trans_line.substring(3, 29).trim();
		String trans_line_sellerUsername = trans_line.substring(29, 45).trim();
		int trans_line_amountOfTickets = Integer.parseInt(trans_line.substring(45, 49).trim());
		double trans_line_ticketPrice = Double.parseDouble(trans_line.substring(49, 56).trim());
		
		//index variables
		int index = 0;
		int trans_line_index;
		for (String line : ticketList) {
			if (line != null)
			{
				eventName = line.substring(0, 26).trim();
				/*
				sellersUsername = line.substring(26, 42).trim();
				amountOfTickets = Integer.parseInt(line.substring(42, 46).trim());
				ticketPrice = Double.parseDouble(line.substring(46, 52).trim());
				*/
				//If a duplicate eventname is found, reading the transaction is cancelled.
				if (checkDuplicateEvent(ticketList, eventName) == true){
					System.out.println("ERROR: Duplicate Event found!\nEnding buy transaction.");
					return ticketList;
				}
				//Find the index of the event referenced in the transaction.s
				if (eventName.equals(trans_line_eventName)){
					trans_line_index = index;
				} else {
					index += 1;
				}
			}
		}
		//Now that index has been found, can begin updating the values

    return ticketList;
  }

  /** TODO Danooshan
  * do sell transaction in ticketList
  * @param List<String> ticketList - a list of all the tickets in the system
  * @param String trans_line - containing the current line from the daily trans file
  * @return: The List<String> ...
  */
  public List<String> sell(List<String> ticketList, String trans_line) {
    return ticketList;
  }

}
