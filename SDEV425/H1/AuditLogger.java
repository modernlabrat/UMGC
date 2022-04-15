import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.io.IOException;

public class AuditLogger {
  public Logger startLogger() {
    // Create Logger
    boolean append = true;
    Logger logger = Logger.getLogger(SDEV425_1.class.getName());
    
    try {
      FileHandler handler = new FileHandler("audits.log", append);

      logger.addHandler(handler);
    } catch (IOException ioe) {
      System.out.println(ioe);
    }

    return logger;
  }
}
