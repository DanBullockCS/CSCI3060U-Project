package noLabToday;

/*
* Class handles the tickets in the system, verifying their validity
*
* @author Danooshan Sureshkumar, Matthew Wierzbicki, and Daniel Bullock
* (Team No Lab Today)
* @version 0.1
* @since 2019-03-05
*/

public class TicketManager {
  public String eventName;
  public double price; 
  public String String;
  public int numTickets;

	/*
		This function is used to check the constraint of having negative tickets, and updates numTickets remaining for the specific event.
		@params: int ticketsPurchased - The number of tickets purchased
		@returns: True if: Tickets are greater or equal to zero
							False if: Tickets are less than zero
	*/
  public boolean checkNegativeTickets(int ticketsPurchased) {
		int remainingTickets = numTickets - ticketsPurchased;
		if (remainingTickets >= 0){
			numTickets -= ticketsPurchased;
			return true;
		}
		else {
			System.out.println("ERROR:  Insufficient tickets available for purchase!");
			return false;
		}
	}

	/*
		TODO: Still working on dis
	*/
  public void getTicketInformation() {
		String message = "Eventname = " + eventName + ",\nCost = " + String.valueOf(price) + ",\nTickets remaining = " + String.valueOf(numTickets);
		System.out.println(message);
	}


	/*
		TODO: Still working on dis
	*/
  public void checkDuplicateEvent() {}
}
