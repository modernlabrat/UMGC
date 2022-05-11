package App;

/*
 * Kyra Samuel
 * Homework2
 * SDEV425
 * 04/29/2022
 */


import javafx.beans.property.SimpleStringProperty;

public class Audit {
  // a simple audit, stores username, details, date, and id
  private final SimpleStringProperty user;
  private final SimpleStringProperty details;
  private final SimpleStringProperty on;
  private final SimpleStringProperty id;

  /*
   * default constructor that creates a new audit
   * @param id = id of audit
   * @param user = current user's username 
   * @param details = audit details
   * @param on = date of audit
   */
  
  public Audit(String id, String user, String details, String on) {
    this.user = new SimpleStringProperty(user);
    this.details = new SimpleStringProperty(details);
    this.on = new SimpleStringProperty(on);
    this.id = new SimpleStringProperty(id);
  }

  // getter and setter methods

  public String getUser() {
    return user.get();
  }

  public String getDetails() {
    return details.get();
  }

  public String getOn() {
    return on.get();
  }

  public String getId() {
    return id.get();
  }

  public void setUser(String sUser) {
    user.set(sUser);
  }

  public void setDetails(String sDetails) {
    user.set(sDetails);
  }

  public void setOn(String sOn) {
    user.set(sOn);
  }

  public void setId(String sId) {
    user.set(sId);
  }
}
