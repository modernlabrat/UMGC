package App;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Noti extends Application {
  private boolean accepted;
  private boolean showing;
  private boolean audited;

  public Noti() {
    // default constructor
    // set accepted to false
    this.accepted = false;
    this.showing = true;
    this.audited = false;
  }

  public void displayStage(Stage primaryStage) {
    // set up stage
    primaryStage.setTitle("Welcome to SDEV425 Admin"); // title
    GridPane grid = new GridPane();

    grid.setAlignment(Pos.CENTER);
    grid.setHgap(10);
    grid.setVgap(10);

    Text sceneMSG = new Text("You are accessing the SDEV325 Admin Portal that is provided for SDEV425 authorize use only\n.By using this portal, you consent to the following conditions: monitoring communications, sessions - at any time. Data stored on this portal is not private to SDEV425 and can be accessed at any time. Thank you!"); // add system use message
    grid.add(sceneMSG, 0, 0, 2, 1);

    Button acceptBtn = new Button("Accept");
    grid.add(acceptBtn, 1, 3);

    Button denyBtn = new Button("Deny");
    grid.add(denyBtn, 1, 4);

    acceptBtn.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent e) {
        accepted = true;
        showing = false;
        primaryStage.hide();
      }
    });

    denyBtn.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent e) {
        accepted = false;
        showing = false;

        primaryStage.hide();
      }
    });

    Scene scene = new Scene(grid, 300, 300);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  // getter / setter methods
  public boolean getAccepted() {
    return this.accepted;
  }

  public boolean getAudited() {
    return this.audited;
  }

  public void setAudited(boolean audited) {
    this.audited = audited;
  }

  public boolean isShowing() {
    return this.showing;
  }

@Override
public void start(Stage arg0) throws Exception {
	// TODO Auto-generated method stub
	
}
}