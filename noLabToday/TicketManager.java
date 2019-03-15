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
  public boolean checkDuplicateEvent() {
		return true;
	}

  // TODO Danooshan
  public List<String> buy(List<String> ticketList, String trans_line) {
    return filehandler.ticketList;
  }

  // TODO Danooshan
  public List<String> sell(List<String> ticketList, String trans_line) {
    return filehandler.ticketList;
  }

}
