package App;
/*
 * Kyra Samuel
 * Homework2
 * SDEV425
 * 04/29/2022
 */

import javafx.application.Application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.geometry.Pos;
import javafx.geometry.Insets;

import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import javafx.stage.Stage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.*;


import java.text.DateFormat;  
import java.text.SimpleDateFormat;  
import java.util.Date; 

import java.util.Timer;
public class App extends Application {
  HashMap<String, Integer> loginAttempts = new HashMap<>();
  ArrayList<String> locked = new ArrayList<>();
  boolean unauthorized = false;
  DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  

  int auditCount = 0;
  private final ObservableList<Audit> audits = FXCollections.observableArrayList();
  @Override
  public void start(Stage primaryStage) {
    loginAttempts.put("sdevadmin", 0);
    loginAttempts.put("ksamuel", 0);
    loginAttempts.put("jdoe", 0);

    displayStage(primaryStage);
  }

  public void displayStage(Stage primaryStage) {
    primaryStage.setTitle("SDEV425 Login");

    GridPane grid = new GridPane();

    grid.setAlignment(Pos.CENTER);
    grid.setHgap(10);
    grid.setVgap(10);

    Text scenetitle = new Text("Welcome. Login to continue.");
    grid.add(scenetitle, 0, 0, 2, 1);

    Label userName = new Label("User Name:");
    grid.add(userName, 0, 1);

    TextField userTextField = new TextField();
    grid.add(userTextField, 1, 1);

    Label pw = new Label("Password:");
    grid.add(pw, 0, 2);

    PasswordField pwBox = new PasswordField();    
    grid.add(pwBox, 1, 2);

    Button btn = new Button("Login");
    grid.add(btn, 1, 4);

    final Text actiontarget = new Text();
    grid.add(actiontarget, 1, 6);

    btn.setOnAction(new EventHandler<ActionEvent>() {
    @Override
    public void handle(ActionEvent e) {
      Timer timer = new Timer();
      Text errorText = new Text();

      errorText.setFill(Color.FIREBRICK);

      unauthorized = false;

      if (authenticate(userTextField.getText(), pwBox.getText(), grid) && !locked.contains(userTextField.getText())) {
        grid.setVisible(false);

        String code = String.valueOf(genCode()); // get random code
        System.out.print("Your access code is: " + code + ".");

        GridPane multiAuthGrid = new GridPane(); // multi-auth grid
        multiAuthGrid.setAlignment(Pos.CENTER);
        multiAuthGrid.setHgap(10);
        multiAuthGrid.setVgap(10);

        // add components to multiAuth grid

        Label codeLbl = new Label("Enter Code:");
        multiAuthGrid.add(codeLbl, 0, 1);

        TextField codeTextField = new TextField();
        multiAuthGrid.add(codeTextField, 1, 1);

        Button enterBtn = new Button("Access");
        multiAuthGrid.add(enterBtn, 1, 4);

        enterBtn.setOnAction(new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent e) {
            if (codeTextField.getText().equals(code)) {
              Noti systemUseNoti = new Noti();

              while(!systemUseNoti.isShowing()) {
                if (systemUseNoti.getAudited()) {
                  auditCount = auditCount +1;
                  String audString = "system-use notification accepted: " + String.valueOf(systemUseNoti.getAccepted());
    
                  audits.add(new Audit(String.valueOf(auditCount), userTextField.getText(), audString, new Date().toString()));
                  systemUseNoti.setAudited(true);
                }

                multiAuthGrid.setVisible(false);

                if (systemUseNoti.getAccepted()) {
                  GridPane adminPane = displayAdminView(userTextField.getText(), primaryStage); // get AdminGrid
  
                  Scene scene = new Scene(adminPane, 500, 400);
  
                  primaryStage.setScene(scene);
                  primaryStage.show();
                }

                primaryStage.hide();
              }

            } else {
              multiAuthGrid.add(errorText, 1, 6);
              errorText.setText("Please try again");

              auditCount = auditCount + 1;

              Date date = Calendar.getInstance().getTime();          
              audits.add(new Audit(String.valueOf(auditCount), userTextField.getText(), "failed multi-auth attempt", dateFormat.format(date)));

              ErrorTimer rmError = new ErrorTimer(errorText);            
              timer.schedule(rmError, 1000, 5000);
            }
          }
        });

        Scene scene = new Scene(multiAuthGrid, 500, 400);

        primaryStage.setScene(scene);
        primaryStage.show();
      } else if (locked.contains(userTextField.getText())) { // accounts are locked
        grid.add(errorText, 1, 6);
        errorText.setText("Account Locked.");

        // update audit count and add audit
        // this will be similar for the following else if/else statements
        auditCount = auditCount + 1;

        Date date = Calendar.getInstance().getTime();  
        audits.add(new Audit(String.valueOf(auditCount), userTextField.getText(), "account locked", dateFormat.format(date)));
        
        ErrorTimer rmError = new ErrorTimer(errorText);            
        timer.schedule(rmError, 1000, 5000);
      } else if (unauthorized) { // unauthorized access users
        grid.add(errorText, 1, 6);
        errorText.setText("Unauthorized Access.");

        auditCount = auditCount + 1;

        Date date = Calendar.getInstance().getTime();
        audits.add(new Audit(String.valueOf(auditCount), userTextField.getText(), "unauthorized access", dateFormat.format(date)));

        ErrorTimer rmError = new ErrorTimer(errorText);            
        timer.schedule(rmError, 1000, 5000);
      } else { // invalid password or username
        grid.add(errorText, 1, 6);
        errorText.setText("Please try again");

        auditCount = auditCount + 1;

        Date date = Calendar.getInstance().getTime();
        String loginAString = "failed login attempt: " + String.valueOf(loginAttempts.get(userTextField.getText()));
    
        audits.add(new Audit(String.valueOf(auditCount), userTextField.getText(), loginAString, dateFormat.format(date)));

        ErrorTimer rmError = new ErrorTimer(errorText);            
        timer.schedule(rmError, 1000, 5000);
      }
    }});

    Scene scene = new Scene(grid, 500, 400);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }

  public boolean authenticate(String user, String pword, GridPane grid) {
    // authenticate users
    switch(user) {
      case "sdevadmin":
      case "ksamuel": // these people are authorized
        if (pword.equals("425!pass")) {
          Date date = Calendar.getInstance().getTime();  
    
          loginAttempts.put(user, 0); 
          auditCount = auditCount + 1;

          audits.add(new Audit(String.valueOf(auditCount), user, "logged in", dateFormat.format(date)));

          return true;
        } else {
          loginAttempts.put(user, loginAttempts.get(user) +1); 
    
          if (loginAttempts.get(user) >= 5)
            locked.add(user);
        }
        return false;
      case "jdoe": // jdoe is unauthorized
        if (pword.equals("425!pass"))
          unauthorized = true;
        else {
          loginAttempts.put(user, loginAttempts.get(user) +1); 
    
          if (loginAttempts.get(user) >= 5)
            locked.add(user);
        }
        return false;
      default:
        return false;
    }
  }

  public GridPane displayAdminView(String user, Stage primaryStage) {
    // displays the welcome text and/or audits button
    GridPane grid2 = new GridPane();
    grid2.setAlignment(Pos.CENTER);
    grid2.setHgap(10);
    grid2.setVgap(10);
    
    Text scenetitle = new Text("Welcome " + user + "!");
    grid2.add(scenetitle, 0, 0, 2, 1);

    if (user.equals("sdevadmin")) { // sdevadmin has audit permissions
      Button auditsBtn = new Button("Audits");
      grid2.add(auditsBtn, 5, 5);

      auditsBtn.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent e) {
          grid2.setVisible(false);

          GridPane grid3 = new GridPane(); // display AuditsGrid
          grid3.setAlignment(Pos.CENTER);
          grid3.setHgap(10);
          grid3.setVgap(10);

          TableView<Audit> table = new TableView<Audit>(); // create table to display audits 
          
          // create table columns
          TableColumn idCol = new TableColumn("ID");
          idCol.setCellValueFactory(
            new PropertyValueFactory<>("id"));
          
          idCol.setMinWidth(75);

          TableColumn userCol = new TableColumn("User");
          userCol.setMinWidth(75);
          userCol.setCellValueFactory(
            new PropertyValueFactory<>("user"));

          TableColumn detailsCol = new TableColumn("Details");
          detailsCol.setMinWidth(125);
          detailsCol.setCellValueFactory(
            new PropertyValueFactory<>("details"));

          TableColumn onCol = new TableColumn("On");
          onCol.setMinWidth(75);
          onCol.setCellValueFactory(
            new PropertyValueFactory<>("on"));

          table.setItems(audits);
          table.getColumns().addAll(idCol, userCol, detailsCol, onCol); // set columns

          final VBox vbox = new VBox(); // add table to VBox
          vbox.setSpacing(5);
          vbox.setPadding(new Insets(10, 0, 0, 10));
          vbox.getChildren().addAll(table);
          vbox.setMaxHeight(250);
          vbox.setMinWidth(400);

          grid3.add(vbox, 1, 2);

          Scene scene = new Scene(grid3, 500, 400);
          primaryStage.setScene(scene);
          primaryStage.show();
        }
      });
    }

    return grid2;
  }

  public int genCode() {
    // generates a random five digit code
    Random code = new Random( System.currentTimeMillis() );
    return 10000 + code.nextInt(20000);
}
}