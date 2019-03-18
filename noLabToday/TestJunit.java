package noLabToday;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class TestJunit {
   /*
   TODO -> Matt
   */
   // FileHandler Tests:
   @Test
   // Testing the WriteTicketsFile()
   public void FileTest1() {
     System.out.println("\nFileTest1:\n-----------------------------------");
   }

   @Test
   // Testing the WriteUsersFile() method
   public void FileTest2() {
     System.out.println("\nFileTest2:\n-----------------------------------");
   }

   @Test
   // Testing the initializeFiles() method
   public void FileTest3() {
     System.out.println("\nFileTest3:\n-----------------------------------");
   }

   @Test
   // Testing the readFile() method
   public void FileTest4() {
     System.out.println("\nFileTest4:\n-----------------------------------");
   }

   @Test
   // Testing the verifyUserFileFormat() method
   public void FileTest5() {
     System.out.println("\nFileTest5:\n-----------------------------------");
   }

   @Test
   // Testing the verifyTicketsFileFormat() method
   public void FileTest6() {
     System.out.println("\nFileTest6:\n-----------------------------------");
   }

   @Test
   // Testing the verifyTransFileFormat() method
   public void FileTest7() {
     System.out.println("\nFileTest7:\n-----------------------------------");
   }

   @Test
   // Testing the readAllFiles() method
   public void FileTest8() {
     System.out.println("\nFileTest8:\n-----------------------------------");
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


   /*
   TODO -> Danooshan
   */
   // TicketManager Tests:
   @Test
   // Testing the checkNegativeTickets() method
   public void TicketTest1() {
     System.out.println("\nTicketTest1:\n-----------------------------------");
   }

   @Test
   // Testing the checkUserIntegrity() method
   public void TicketTest2() {
     System.out.println("\nTicketTest2:\n-----------------------------------");
   }

   @Test
   // Testing the checkDuplicateEvent() method
   public void TicketTest3() {
     System.out.println("\nTicketTest3:\n-----------------------------------");
   }

   @Test
   // Testing the findBuyer() method
   public void TicketTest4() {
     System.out.println("\nTicketTest4:\n-----------------------------------");
   }

   @Test
   // Testing the buy() method
   public void TicketTest5() {
     System.out.println("\nTicketTest5:\n-----------------------------------");
   }

   @Test
   // Testing the checkCorrectDuplicate() method
   public void TicketTest6() {
     System.out.println("\nTicketTest6:\n-----------------------------------");
   }

   @Test
   // Testing the sell() method
   public void TicketTest7() {
     System.out.println("\nTicketTest7:\n-----------------------------------");
   }
}
