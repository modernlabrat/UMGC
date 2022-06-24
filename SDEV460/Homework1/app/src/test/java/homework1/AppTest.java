package homework1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import javax.swing.JPanel;

class AppTest {
  @Test void createdLoginPanel() {
    App appTest = new App();
    assertEquals(new JPanel().getClass(), appTest.getLoginPanel().getClass(), "Login JPanel could not be created");
  }

  @Test void validLogin() {
    App appTest = new App();
    assertTrue(appTest.verifyPassword("sdevAdmin", "S3CR3Tp4ssw0rd"), "Invalid Credentials");
  }

  @Test void reauthenticated() {
    App appTest = new App();
    String code = appTest.generateCode();
    String code2 = appTest.generateCode();

    assertEquals(code.length(), 4, "The code should be 4 digits long.");
    assertNotEquals(code, code2, "These codes should not be the same.");

    assertTrue(appTest.validateCode(code, code), "Invalid Code");
  }



  @Test void addLog() {
    new App();
    App.setUpFrame();

    assertNotNull(App.createNewLog("Test log message", "C:/Users/kyras/OneDrive/Desktop/Code/UMGC/SDEV460/Homework1/app/src/main/logs/Log.txt"), "Unable to write log message. Verify the path.");
  }
}
