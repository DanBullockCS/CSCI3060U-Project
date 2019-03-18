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
  /*public FileHandler filehandler = new FileHandler(); // FileHandler class
  public String eventName; //Title of the event on the ticket
  public double price; // Price of the ticket
  public String seller; // Name of the user selling the ticket
  public int numTickets; // Number of tickets remaining*/

	/**
		This function is used to check the constraint of having negative tickets, and updates numTickets remaining for the specific event.
		@param int ticketsPurchased - The number of tickets purchased
		@return: True if: Tickets are greater or equal to zero
					   False if: Tickets are less than zero
	*/
  public boolean checkNegativeTickets(int ticketsPurchased, int totalTickets) {
		int remainingTickets = totalTickets - ticketsPurchased;
		if (remainingTickets >= 0) {
			totalTickets -= ticketsPurchased;
			return true;
		}
		else {
			System.out.println("ERROR:  Insufficient tickets available for purchase!");
			return false;
		}
	}
	
	 /**
  * Checks that the user that was being created has a unique username
  * @param String username - the user name of the current user
  * @return: True if: User to be created does exist in database
  *          False if: User to be created does NOT exists in database or if User is not unique
  */
  public boolean checkUserIntegrity(String username, List<String> userList) {
		String currentUser;
    // check if username is already in users.ua
		int count = 0;
    for (String s: userList) {
			if (s != null){
				currentUser = s.substring(0,15);
		    if (currentUser.equals(username)) {
					count += 1;
		    }
			}
    }
		if (count == 0){
			// User does not exist in users.ua
   		System.out.println("User is not in database. Ending transaction.");
   		return false;
		} else if (count == 1){
			// User does exit in users.ua
			System.out.println("Unique user found in database.");	
			return true;
		} else {
			// Users with the same name found
			System.out.println("Multiple users of same name found. Ending transaction.");
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
				if (line.substring(0, 25).equals(eventName)){

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
					if (line.substring(0,2).equals("00")){
						name = line.substring(3, 18);
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
  public List<String> buy(List<String> ticketList, String trans_line, List<String> transList, List<String> userList) {

		//Current ticketList line information
		String eventName = "";
		String sellersUsername = "";
		int amountOfTickets = 0;
		double ticketPrice = 0;

		//Current transaction line information
		String trans_line_eventName = trans_line.substring(3, 28);
		String trans_line_sellerUsername = trans_line.substring(29, 44);
		int trans_line_amountOfTickets = Integer.parseInt(trans_line.substring(45, 48));
		double trans_line_ticketPrice = Double.parseDouble(trans_line.substring(49, 55));

		//Seller information
		//trans_line_sellerUsername = sellers username
		String sellerPrivelege = "";
		double sellerCredits = 0;

		//Buyer information
		String buyerUsername = findBuyer(transList, trans_line);
		String buyerPrivelege = "";
		double buyerCredits = 0;	
		
		//index variables
		int index = 0;
		int trans_line_index = 0;
		boolean eventFound = false;
		for (String line : ticketList) {
			if (line != null)
			{
				if (eventFound == false){
					eventName = line.substring(0, 25);
					sellersUsername = line.substring(26, 41);
					amountOfTickets = Integer.parseInt(line.substring(42, 45));
					ticketPrice = Double.parseDouble(line.substring(46, 52));
				
					//If a duplicate eventname is found, reading the transaction is cancelled.
					if (checkDuplicateEvent(ticketList, eventName) == true){
						System.out.println("ERROR: Duplicate Event found!\nEnding buy transaction.");
						return ticketList;
					}
					//Find the index of the event referenced in the transaction.s
					if (eventName.equals(trans_line_eventName)){
						trans_line_index = index;
						System.out.println("Event found...");
					}
				}
			}
			index += 1;
		}

		
		if (checkNegativeTickets(trans_line_amountOfTickets ,amountOfTickets) == true){
			System.out.println("Sufficient tickets available for purchase...");
		} else {
			return ticketList;
		}
		if (!checkUserIntegrity(buyerUsername, userList) && !checkUserIntegrity(sellersUsername, userList)){
			return ticketList;
		}
		//Find the buyer and seller indexes
		index = 0;
		int buyer_index = 0, seller_index = 0;
		boolean buyerFound = false;
		boolean sellerFound = false;
    for (String line : userList) {
			if (line != null) {
				if (buyerFound == false){
		      if (line.substring(0,15).equals(buyerUsername)){ 
						buyer_index = index; 
						buyerPrivelege = userList.get(buyer_index).substring(16,18);
					  buyerCredits = Double.parseDouble(userList.get(buyer_index).substring(19, 28));
						
					}
				}
				if (sellerFound == false){
		      if (line.substring(0,15).equals(sellersUsername)){ 
						seller_index = index; 
						sellerPrivelege = userList.get(seller_index).substring(16,18);
					  sellerCredits = Double.parseDouble(userList.get(seller_index).substring(19,28));
					}
				}
			}
      index++;
    }
   
		//Now that constraints have been checked and index found, can begin updating the values

		//New values to replace old ones.
		double new_sellers_credit = sellerCredits + ticketPrice;
		double new_buyers_credit = buyerCredits - ticketPrice;
		String string_new_sellers_credit = String.valueOf(new_sellers_credit);
		String string_new_buyers_credit = String.valueOf(new_buyers_credit);
		int remainingTickets = amountOfTickets - trans_line_amountOfTickets;
		String new_amountOfTickets = String.valueOf(remainingTickets);
		String string_ticketPrice = String.valueOf(ticketPrice);

		//Add padding to amountOfTickets
		while(new_amountOfTickets.length() <= 3){
			new_amountOfTickets = "0" + new_amountOfTickets;
		}
		if (new_sellers_credit > 999999.99){
			System.out.println("Seller max credit reached cannot proceed, cancelling transaction.");
			return ticketList;
		} else if (new_buyers_credit < 0){
			System.out.println("Buyer has insufficient credits, cancelling transaction.");
			return ticketList;
		} else {
			//Padding for seller
			if(!string_new_sellers_credit.contains(".")){
				string_new_sellers_credit += ".00";
			}
			while(string_new_sellers_credit.length() <= 9){
				string_new_sellers_credit = "0" + string_new_sellers_credit;
			}
			//Padding for buyer
			if(!string_new_buyers_credit.contains(".")){
				string_new_buyers_credit += ".00";
			}
			while(string_new_sellers_credit.length() <= 9){
				string_new_buyers_credit = "0" + string_new_buyers_credit;
			}
			//Padding for ticket price
			if(!string_ticketPrice.contains(".")){
				string_ticketPrice += ".00";
			}
			while(string_ticketPrice.length() <= 6){
				string_ticketPrice = "0" + string_ticketPrice;
			}
		}
		String updatedBuyer = buyerUsername + " " + buyerPrivelege + " " + string_new_buyers_credit;
		String updatedSeller = sellersUsername + " " + sellerPrivelege + " " + string_new_sellers_credit;
		String updatedTicket = trans_line_eventName + " " + sellersUsername + " " + new_amountOfTickets + " " + string_ticketPrice;
		
		
		//One last check to make sure buyer and seller are not the same
		if (buyerUsername.equals(sellersUsername)){
			System.out.println("Cannot buy from self, ending transaction.");
			return ticketList;	
		} else {
			ticketList.remove(trans_line_index);
			ticketList.add(updatedTicket);
			userList.remove(buyer_index);
			userList.add(updatedBuyer);
			userList.remove(seller_index);
			userList.add(updatedSeller);
			System.out.println("Transaction completed successfully!");
		}
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
