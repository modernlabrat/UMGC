import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Audits {
  ArrayList<HashMap<Integer, String>> auditsList; // stores all audits 
  private int auditCount; // audit id

  public Audits() {
    auditsList = new ArrayList<>();
    auditCount = 0;
  }
  
  /** 
   * Adds audit string to the auditsList
   * @param audit - audit passed via SDEV425_1.java
   */
  public void addAudit(String audit) {
    HashMap<Integer, String> auditMap = new HashMap<>(); // audit id, audit string

    String stringDate = new Date().toString(); // date of audit
    String polAudit = stringDate + ":: " + audit; // polished audit
    auditCount++; // increase id number

    auditMap.put(auditCount, polAudit);

    auditsList.add(auditMap);
  }


  public void displayAudits() {
    // prints all audits in auditsList
    if (auditsList.isEmpty())
      System.out.print("No Audits.");
    else {
      for (int i = 0; i < auditsList.size(); i++) {
        auditsList.get(i).entrySet().forEach(entry -> {
          System.out.println(entry.getKey() + " " + entry.getValue());
      });
      }
    }
  }
}
