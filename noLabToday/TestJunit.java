import org.junit.Test;
import static org.junit.Assert.*;

public class TestJunit {
   // FileHandler Tests:
   @Test
   public void FileTest1() {
     String str = "Junit is working fine";
     assertEquals("Junit is working fine",str);
   }

   // AccountManager Tests:
   @Test
   public void AccountTest1() {
   }

   // TicketManager Tests:
   @Test
   public void TicketTest1() {
   }
}
