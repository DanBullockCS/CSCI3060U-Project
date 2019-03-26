package noLabToday;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import noLabToday.FileHandler;
import java.io.*;

/**
* Class contains all Junit Test methods to be run by TestRunner
*
* @author Danooshan Sureshkumar, Matthew Wierzbicki, and Daniel Bullock
* (Team No Lab Today)
* @version 0.2
* @since 2019-03-10
*/
public class TestJunit {
   // Main method test
   @Test
   public void MainTest() {
     System.out.println("\nMainTest:\n-----------------------------------");
     Main main = new Main();
   }

   // FileHandler Tests:
   @Test
   // Testing the WriteTicketsFile()
   public void FileTest1() {
     System.out.println("\nFileTest1:\n-----------------------------------");

     try
     {
    	 FileHandler handlerObj = new FileHandler();
         assertEquals(true,handlerObj.WriteTicketsFile());
         System.out.println("Tickets File Written To.");

     }
	catch(Exception e)
	{
		System.out.println(e.getMessage());
	}
   }

   @Test
   // Testing the WriteUsersFile() method
   public void FileTest2() {
     System.out.println("\nFileTest2:\n-----------------------------------");
     try
     {
    	 FileHandler handlerObj = new FileHandler();
         assertEquals(true,handlerObj.WriteUsersFile());
         System.out.println("Users File Written To.");

     }
	catch(Exception e)
	{
		System.out.println(e.getMessage());
	}


   }

   @Test
   // Testing the readFile() method
   public void FileTest3() {
     System.out.println("\nFileTest4:\n-----------------------------------");
     try
     {
    	 FileHandler handlerObj = new FileHandler();

         assertNotNull(handlerObj.readFile("./files/stock.at"));
         assertNotNull(handlerObj.readFile("./files/trans.out"));
         assertNotNull(handlerObj.readFile("./files/users.ua"));
         System.out.println("Files not null Verified.");

     }
	catch(Exception e)
	{
		System.out.println(e.getMessage());
	}

   }

   @Test
   // Testing the verifyUserFileFormat() method
   public void FileTest4() {
     System.out.println("\nFileTest5:\n-----------------------------------");

     try
     {
    	 FileHandler handlerObj = new FileHandler();
         assertEquals(true, handlerObj.verifyUserFileFormat());
         System.out.println("Users Format Verified.");

     }
	catch(Exception e)
	{
		System.out.println(e.getMessage());
	}
   }

   @Test
   // Testing the verifyTicketsFileFormat() method
   public void FileTest5() {
     System.out.println("\nFileTest6:\n-----------------------------------");

     try
     {
    	 FileHandler handlerObj = new FileHandler();
         assertEquals(true, handlerObj.verifyTicketsFileFormat());
         System.out.println("Tickets Format Verified.");

     }
	catch(Exception e)
	{
		System.out.println(e.getMessage());
	}
   }

   @Test
   // Testing the verifyTransFileFormat() method
   public void FileTest6() {
     System.out.println("\nFileTest7:\n-----------------------------------");

     try
     {
    	 FileHandler handlerObj = new FileHandler();
         assertEquals(true, handlerObj.verifyTransFileFormat());
         System.out.println("Trans Format Verified.");
     }
	catch(Exception e)
	{
		System.out.println(e.getMessage());
	}
   }


   // AccountManager Tests:
   @Test
   // Testing the checkUserIntegrity() method for its false and true outputs
   public void AccountTest1() {
     System.out.println("\nAccountTest1 (checkUserIntegrity):\n-----------------------------------");
     AccountManager acctmanager = new AccountManager();
     // a sample userList
     ArrayList<String> userList = new ArrayList<>(Arrays.asList(
                                                  "user01          AA 000230.00"));
     assertEquals(true, acctmanager.checkUserIntegrity(userList, "user01"));
     // Passing a user that doesn't exist in the system ("BoyDontExist")
     assertEquals(false, acctmanager.checkUserIntegrity(userList, "BoyDontExist"));
   }

   @Test
   // Testing the create() method
   public void AccountTest2() {
     System.out.println("\nAccountTest2 (create):\n-----------------------------------");
     AccountManager acctmanager = new AccountManager();
     String trans_line = "01 newTestUser     BS 123456.78";
     String trans_line_already_exists = "01 user01          AA 123456.78";
     // a sample userList
     ArrayList<String> userList = new ArrayList<>(Arrays.asList(
                                                     "user01          AA 000230.00",
                                                     "user06          AA 002323.00"));
     // the expected list to be returned from the create() method
     ArrayList<String> expectedList = new ArrayList<>(Arrays.asList(
                                                     "user01          AA 000230.00",
                                                     "user06          AA 002323.00",
                                                     "newTestUser     BS 123456.78"));
     assertEquals(expectedList, acctmanager.create(userList, trans_line));
     // Passing in a user that already exists,
     // therefore the original userList should be returned
     assertEquals(userList, acctmanager.create(userList, trans_line_already_exists));
   }

   @Test
   // Testing the delete() method
   public void AccountTest3() {
     System.out.println("\nAccountTest3 (delete):\n-----------------------------------");
     AccountManager acctmanager = new AccountManager();
     String trans_line = "02 user06          AA 002323.00";
     // sending a trans line of a user that doesn't exist
     String trans_line_doesnt_exists = "02 userWho         AA 123456.78";
     // a sample userList
     ArrayList<String> userList = new ArrayList<>(Arrays.asList(
                                                     "user01          AA 000230.00",
                                                     "user06          AA 002323.00"));
     // the expected list to be returned from the delete() method
     ArrayList<String> expectedList = new ArrayList<>(Arrays.asList(
                                                     "user01          AA 000230.00"));
     assertEquals(expectedList, acctmanager.delete(userList, trans_line));
     // Passing in a user that does not exist,
     // therefore the original userList should be returned
     assertEquals(userList, acctmanager.delete(userList, trans_line_doesnt_exists));
   }

   @Test
   // Testing the refund() method
   public void AccountTest4() {
     System.out.println("\nAccountTest4 (refund):\n-----------------------------------");
     AccountManager acctmanager = new AccountManager();
     String trans_line = "05 user06          user01          000050.00";
     String trans_line_fake_user = "05 fake00          user01          000050.00";
     String trans_line_refund_yourself = "05 user01          user01          000050.00";
     ArrayList<String> userList = new ArrayList<>(Arrays.asList(
                                                  "user01          AA 000230.00",
                                                  "user06          AA 002323.00"));
     ArrayList<String> expectedList = new ArrayList<>(Arrays.asList(
                                                  "user01          AA 000180.00",
                                                  "user06          AA 002373.00"));
     assertEquals(expectedList, acctmanager.refund(userList, trans_line));
     // Users does not exist in the system therefore no refund occurs, same list returned
     assertEquals(userList, acctmanager.refund(userList, trans_line_fake_user));
     // user refunding to themselves
     assertEquals(userList, acctmanager.refund(userList, trans_line_refund_yourself));
   }

   @Test
   // Testing the addCredit() method
   public void AccountTest5() {
     System.out.println("\nAccountTest5 (addCredit):\n-----------------------------------");
     AccountManager acctmanager = new AccountManager();
     String trans_line = "06 user01          AA 000080.00";
     String trans_line_fake_user = "06 fake00          AA 000080.00";
     ArrayList<String> userList = new ArrayList<>(Arrays.asList(
                                                  "user01          AA 000230.00",
                                                  "user06          AA 002323.00"));
     ArrayList<String> expectedList = new ArrayList<>(Arrays.asList(
                                                  "user01          AA 000310.00",
                                                  "user06          AA 002323.00"));
     assertEquals(expectedList, acctmanager.addCredit(userList, trans_line));
     // Users does not exist in the system therefore no addcredit occurs, same list returned
     assertEquals(userList, acctmanager.addCredit(userList, trans_line_fake_user));
   }



   // TicketManager Tests:
   @Test
   // Testing the checkNegativeTickets() method
   public void TicketTest1() {
		 System.out.println("\nTicketTest1:\n-----------------------------------");
		 TicketManager tickmanager = new TicketManager();
		 int ticketsPurchased = 10; //First assertion value
		 int ticketsPurchased2 = 1000; //Second assertion value
	   int totalTickets = 100;

			//Case of sufficient tickets
		 assertEquals(true, tickmanager.checkNegativeTickets(ticketsPurchased, totalTickets));
			//Case of insufficient tickets
		 assertEquals(false, tickmanager.checkNegativeTickets(ticketsPurchased2, totalTickets));
   }

   @Test
   // Testing the checkUserIntegrity(username, userList) method
   public void TicketTest2() {
     System.out.println("\nTicketTest2:\n-----------------------------------");
		 TicketManager tickmanager = new TicketManager();
		 String username = "user01         ";
			//First assertion list
		 ArrayList<String> userList = new ArrayList<>(Arrays.asList(
                                                  "user06          AA 002323.00"));
			//Second assertion list
		 ArrayList<String> userList2 = new ArrayList<>(Arrays.asList(
                                                  "user01          AA 000230.00",
                                                  "user06          AA 002323.00"));
			//Third assertion list
		 ArrayList<String> userList3 = new ArrayList<>(Arrays.asList(
                                                  "user01          AA 000230.00",
																									"user01          AA 000235.00",
                                                  "user06          AA 002323.00"));
			//Case of no user found
		 assertEquals(false, tickmanager.checkUserIntegrity(username, userList));
			//Case of user found
		 assertEquals(true, tickmanager.checkUserIntegrity(username, userList2));
			//Case of duplicate user found
		 assertEquals(false, tickmanager.checkUserIntegrity(username, userList3));

   }

   @Test
   // Testing the checkDuplicateEvent() method
   public void TicketTest3() {
     System.out.println("\nTicketTest3:\n-----------------------------------");
		 TicketManager tickmanager = new TicketManager();
		 String eventName = "airpods meetup           ";
			//First assertion list
		 ArrayList<String> ticketList1 = new ArrayList<>(Arrays.asList(
																										"run real fast event       user01          100 015.00",
																										"airpods meetup            user01          003 100.00"));
			//Second assertion list
		 ArrayList<String> ticketList2 = new ArrayList<>(Arrays.asList(
																										"airpods meetup            user01          100 015.00",
																										"airpods meetup            user01          003 100.00"));



			//Case of duplicate event
		 assertEquals(true, tickmanager.checkDuplicateEvent(ticketList2, eventName));
			//Case of unique event
		 assertEquals(false, tickmanager.checkDuplicateEvent(ticketList1, eventName));
   }

   @Test
   // Testing the findBuyer() method
   public void TicketTest4() {
     System.out.println("\nTicketTest4:\n-----------------------------------");
		 TicketManager tickmanager = new TicketManager();
		 String trans_line = "04 airpods meetup            user01          023 100.00";
		 ArrayList<String> transactionList = new ArrayList<>(Arrays.asList(
																												"01 user06          AA 002323.00",
																												"02 UserCreditMax   FS 000000.00",
																												"05 user02          user01          000050.00",
																												"06 user01          AA 000080.00",
																												"00 user01          AA 000100.00",
																												"03 run real fast event       user01          100 015.00",
																												"00 user01          AA 000100.00",
																												"04 airpods meetup            user01          023 100.00",
																												"00 user02          FS 000075.00"));
		 ArrayList<String> transactionList2 = new ArrayList<>(Arrays.asList(
																												"01 user06          AA 002323.00",
																												"02 UserCreditMax   FS 000000.00",
																												"05 user02          user01          000050.00",
																												"06 user01          AA 000080.00",
																												"00 user01          AA 000100.00",
																												"03 run real fast event       user01          100 015.00",
																												"00 user01          AA 000100.00",
																												"04 airpods meetup            user01          023 100.00"));
			//Case of buyer existing
		 assertEquals("user02         ", tickmanager.findBuyer(transactionList, trans_line));
			//Case of buyer not found NOTE: this test can only occur if by some chance the logout transaction is not printed at
			//end of the session in which the buy is transacted
		 assertEquals("", tickmanager.findBuyer(transactionList2, trans_line));
   }

   @Test
   // Testing the buy() method
   public void TicketTest5() {
     System.out.println("\nTicketTest5:\n-----------------------------------");
		 TicketManager tickmanager = new TicketManager();
				//Transaction cases
		 String trans_line = "04 airpods meetup            user01          023 100.00";
		 String trans_line2 = "04 airpods meetup            user02          023 100.00";
				//Transaction list cases
		 ArrayList<String> transactionList = new ArrayList<>(Arrays.asList(
																												"04 airpods meetup            user01          023 100.00",
																												"00 user02          FS 000075.00"));
		 ArrayList<String> transactionList2 = new ArrayList<>(Arrays.asList(
																												"04 airpods meetup            user01          500 100.00",
																												"00 user02          FS 000075.00"));
		 ArrayList<String> transactionList3 = new ArrayList<>(Arrays.asList(
																												"04 airpods meetup            user02          500 100.00",
																												"00 user02          FS 000075.00"));
		 ArrayList<String> ticketList = new ArrayList<>(Arrays.asList(
																										"run real fast event       user01          100 015.00",
																										"airpods meetup            user01          300 100.00"));
		 ArrayList<String> ticketList2 = new ArrayList<>(Arrays.asList(
																										"airpods meetup            user01          100 015.00",
																										"airpods meetup            user01          300 100.00"));
		 ArrayList<String> ticketList3 = new ArrayList<>(Arrays.asList(
																										"run real fast event       user01          100 015.00",
																										"airpods meetup            user02          300 100.00"));
			//User list cases
		 ArrayList<String> userList = new ArrayList<>(Arrays.asList(
                                                  "user01          AA 000230.00",
                                                  "user02          AA 002323.00"));
		 ArrayList<String> userList2 = new ArrayList<>(Arrays.asList(
                                                  "user02          AA 000230.00",
                                                  "user02          AA 002323.00"));
		 ArrayList<String> userList3 = new ArrayList<>(Arrays.asList(
                                                  "user01          AA 999999.00",
                                                  "user02          AA 002323.00"));
		 ArrayList<String> userList4 = new ArrayList<>(Arrays.asList(
                                                  "user02          AA 000230.00",
                                                  "user02          AA 000001.00"));
		 ArrayList<String> expectedTicketList = new ArrayList<>(Arrays.asList(
																										"run real fast event       user01          100 015.00",
																										"airpods meetup            user01          277 100.00"));
		//Case of successfull transaction
		assertEquals(expectedTicketList, tickmanager.buy(ticketList, trans_line, transactionList, userList));
		//Unsuccesfful cases:
		//	1)	Case of duplicate event
		assertEquals(ticketList2, tickmanager.buy(ticketList2, trans_line, transactionList, userList));

		//	3)	Case of invalid user, or duplicate user
		assertEquals(ticketList, tickmanager.buy(ticketList, trans_line, transactionList, userList2));
		//	4)	Case of seller reaching maximum credit
		assertEquals(ticketList, tickmanager.buy(ticketList, trans_line, transactionList, userList3));
		//	5)	Case of buyer having insufficient credits
		assertEquals(ticketList, tickmanager.buy(ticketList, trans_line, transactionList, userList4));
		//	6)	Case of buyer is seller
		assertEquals(ticketList3, tickmanager.buy(ticketList3, trans_line2, transactionList3, userList));

   }

   @Test
   // Testing the checkCorrectDuplicate() method
   public void TicketTest6() {
     System.out.println("\nTicketTest6:\n-----------------------------------");
		 TicketManager tickmanager = new TicketManager();
		 String eventName = "airpods meetup           ";
		 String userName = "user01         ";
			//Ticket List cases
		 ArrayList<String> ticketList = new ArrayList<>(Arrays.asList(
																										"cooleventforme            user01          100 015.00",
																										"airpods meetup            user02          003 100.00"));
		 ArrayList<String> ticketList2 = new ArrayList<>(Arrays.asList(
																										"airpods meetup            user01          100 015.00",
																										"airpods meetup            user01          003 100.00"));
			//Case of duplicate events with the same seller
		 assertEquals(true, tickmanager.checkCorrectDuplicate(ticketList2, userName, eventName));
			//Successful case
		 assertEquals(false, tickmanager.checkCorrectDuplicate(ticketList, userName, eventName));
   }

   @Test
   // Testing the sell() method
   public void TicketTest7() {
     System.out.println("\nTicketTest7:\n-----------------------------------");
		 TicketManager tickmanager = new TicketManager();
		 String trans_line = "03 run real fast event       user01          100 015.00";

		 ArrayList<String> userList1 = new ArrayList<>(Arrays.asList(
                                                  "user01          AA 000230.00",
                                                  "user02          AA 002323.00"));

		 ArrayList<String> userList2 = new ArrayList<>(Arrays.asList(
                                                  "user01          AA 000230.00",
                                                  "user01          AA 002323.00"));

		 //Ticket List cases
		 ArrayList<String> ticketList = new ArrayList<>(Arrays.asList(
																										"airpods meetup            user01          100 015.00",
																										"airpods meetup            user02          003 100.00"));
		 ArrayList<String> ticketList2 = new ArrayList<>(Arrays.asList(
																										"run real fast event       user01          100 015.00",
																										"airpods meetup            user01          003 100.00"));
		 ArrayList<String> expectedTicketList = new ArrayList<>(Arrays.asList(
																										"airpods meetup            user01          100 015.00",
																										"airpods meetup            user02          003 100.00",
																										"run real fast event       user01          100 015.00"));
			//Successful case
		 assertEquals(expectedTicketList, tickmanager.sell(ticketList, trans_line, userList1));
			//Unsuccessful cases:
			//	1)	Duplicate users
		 assertEquals(ticketList, tickmanager.sell(ticketList, trans_line, userList2));
			//	2)	Creating an event the seller has already created
		 assertEquals(ticketList2, tickmanager.sell(ticketList2, trans_line, userList1));
   }
}
