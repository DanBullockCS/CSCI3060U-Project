package noLabToday;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestJunit {
   // FileHandler Tests:
   @Test
   public void FileTest1() {

   }

   // AccountManager Tests:
   @Test
   // Testing the checkUserIntegrity() method for its false and true outputs
   public void AccountTest1() {
     AccountManager acctmanager = new AccountManager();
     assertEquals(true, acctmanager.checkUserIntegrity("user01"));
     assertEquals(false, acctmanager.checkUserIntegrity("BoyDontExist"));
   }

   @Test
   // Testing the create() method
   public void AccountTest2() {
   }

   @Test
   // Testing the delete() method
   public void AccountTest3() {
   }

   @Test
   // Testing the refund() method
   public void AccountTest4() {
   }

   @Test
   // Testing the addCredit() method
   public void AccountTest5() {
   }

   // TicketManager Tests:
   @Test
   public void TicketTest1() {

   }
}
