
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

class Main {
  // components needed 
  JFrame frame;

  TreeMap<Integer, Property> tree_map  = new TreeMap<Integer, Property>(); 
   
  private JLabel transNumLbl, addressLbl, bedroomsLbl, sqftLbl, priceLbl; 

	private JTextField transNumTF, addressTF, bedroomsTF, sqftTF, priceTF; 
	
	private String addressInput, processOptionSelected;
	
  private int transNumInput, bedroomsInput, sqftInput, priceInput, score;
	
	private JButton processBtn, changeStatusBtn;
	
	private JComboBox processOptionsCB, statusCB;
	
	private String[] processArray = {"Insert", "Delete", "Find"};
	
	private String[] statusStrings = {"FOR_SALE", "UNDER_CONTRACT", "SOLD"};

	Status selectedStatus;
  
  private final int PASSING_SCORE = 4;


  public Main() {
    frame = new JFrame();
    // main panel
	  JPanel panelContent = new JPanel(new GridLayout(7,2));
		// components 
		
    // labels 
		transNumLbl = new JLabel("Transaction Number",(JLabel.LEFT));
		addressLbl = new JLabel("Address", (JLabel.LEFT));
		bedroomsLbl = new JLabel("Bedrooms",JLabel.LEFT);
		sqftLbl = new JLabel("Square Foot",JLabel.LEFT);
		priceLbl = new JLabel("Price",JLabel.LEFT);
		
    // textfields
	  transNumTF = new JTextField();
		addressTF = new JTextField();
		bedroomsTF = new JTextField();
		sqftTF = new JTextField();
		priceTF = new JTextField();
    //buttons
		processBtn = new JButton("Process");
		// action handler
		processBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent listener) {
        checkFields();
        // if user input is correct
        if (score == PASSING_SCORE) {
				  switch (processOptionSelected) {
				    case "Insert":
						  // check if treemap contains key 
              if(tree_map.containsKey(transNumInput)) {
                JOptionPane.showMessageDialog(frame, "Transation No." + transNumInput + " is already in the database. Enter an unused transaction number or new property.");  
              // if not, try to make a new property object
              } else {
                try {
			            Property newProperty = new Property(addressInput, bedroomsInput, sqftInput, priceInput);
			          
                  tree_map.put(transNumInput,newProperty);
		              score = 0;
                  JOptionPane.showMessageDialog(frame, "Transation No." + transNumInput + " inserted");
			          } catch (Exception e) {
		              JOptionPane.showMessageDialog(frame, "Transation No." + transNumInput + "\nError: Something went wrong. Check all input.");
		         	  }
              }
					  break;
					  case "Find":
			        if(tree_map.containsKey(transNumInput)) {
                JOptionPane.showMessageDialog(frame, tree_map.get(transNumInput).toString());
              } else {
                JOptionPane.showMessageDialog(frame, "Transation No." + transNumInput + " could not be found.");
              }
					  break;
					  case "Delete":
					    int testScore = 0;
           
              if(tree_map.containsKey(transNumInput)) {
						    try { 
		          	  tree_map.remove(transNumInput);
                  testScore = 4;
             	  } catch (Exception e) {
                  JOptionPane.showMessageDialog(frame, "Transaction No. " + transNumInput + " could not be deleted.");
	              }

                if (testScore == PASSING_SCORE) {
                  JOptionPane.showMessageDialog(frame,"Transaction No. " + transNumInput+ " deleted.");
                }
              } else {
                JOptionPane.showMessageDialog(frame, "Transation No." + transNumInput + " could not be found.");
              } 
					  break;
					  default:
            JOptionPane.showMessageDialog(frame, "Error: I cannot insert, find, or delete.");
					  break;
				  }
        }
			}
		});
		
		changeStatusBtn = new JButton("Change Status");
		changeStatusBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent listener) {
        checkFields();
        if(tree_map.containsKey(transNumInput)) {
          tree_map.get(transNumInput).changeState(selectedStatus);
          JOptionPane.showMessageDialog(frame, "Transaction No." + transNumInput + " status changed.");
        } else {
          JOptionPane.showMessageDialog(frame, "Transaction No." + transNumInput + " could not be found.");
        }
			}
		});
		
		processOptionsCB = new JComboBox<>(processArray);
		processOptionsCB.addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent listener) {
		  	String processOptions = (String)processOptionsCB.getSelectedItem();
			  
        if (processOptions == null) {
          JOptionPane.showMessageDialog(frame, "Please select a process option");
        }
        switch (processOptions) {
				  case "Insert":
				  	processOptionSelected = "Insert";
			  	break;
				  case "Delete":
			  		processOptionSelected = "Delete";
			  	break;
			  	case "Find":
		  			processOptionSelected = "Find";
			  	break;
		  		default:
		        JOptionPane.showMessageDialog(frame, "Error" + transNumInput + "Please select insert, delete, or find");
		  		break;
  			}
		  }
		});
		
		statusCB = new JComboBox<>(statusStrings);
		statusCB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent listener) {
				String newStatusOptions = (String)statusCB.getSelectedItem();
				
        switch (newStatusOptions) {
					case "FOR_SALE":
						selectedStatus = Status.FOR_SALE;
					break;
					case "UNDER_CONTRACT":
						selectedStatus = Status.UNDER_CONTRACT;
					break;
					case "SOLD":
						selectedStatus = Status.SOLD;
					break;
					default:
						selectedStatus = Status.FOR_SALE;
					break;
				}
			}
		});
		
		panelContent.add(transNumLbl);
		panelContent.add(transNumTF);
		panelContent.add(addressLbl);
		panelContent.add(addressTF);
		panelContent.add(bedroomsLbl);
		panelContent.add(bedroomsTF);
		panelContent.add(sqftLbl);
		panelContent.add(sqftTF);
		panelContent.add(priceLbl);
		panelContent.add(priceTF);
		panelContent.add(processBtn);
		panelContent.add(processOptionsCB);
		panelContent.add(changeStatusBtn);
    panelContent.add(statusCB);

    JScrollPane scrollPane = new JScrollPane(panelContent);  
    frame.setTitle("Real Estate Database");
    frame.add(scrollPane);
    frame.setSize(400,300);
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
	}
	
  public void checkFields() {
    int testScore = 0;
    if (addressTF.getText().isEmpty()) {
     JOptionPane.showMessageDialog(frame,"Please enter an address");
    } else {
     addressInput = (String) addressTF.getText();
    }

    if (transNumTF.getText().isEmpty()) {
     JOptionPane.showMessageDialog(frame,"Please enter a transaction number.");
    } else {
      transNumInput = convertTF(transNumTF);
      testScore++;
    }

    if (sqftTF.getText().isEmpty()) {
     JOptionPane.showMessageDialog(frame,"Please enter square footage.");
    } else {
     sqftInput = convertTF(sqftTF);
     testScore++;
    }

    if (priceTF.getText().isEmpty()) {
     JOptionPane.showMessageDialog(frame,"Please enter a price");
    } else {
     priceInput = convertTF(priceTF);
     testScore++;
    }

    if (bedroomsTF.getText().isEmpty()) {
     JOptionPane.showMessageDialog(frame,"Please enter number of bedrooms");
    } else {
     bedroomsInput = convertTF(bedroomsTF);
     testScore++;
    }
  }
  
  public int convertTF(JTextField textFieldToChange) {
	  String x = textFieldToChange.getText();
    int newInt = 0;
	  try {
	    newInt = Integer.valueOf(x);
      score = 4;
	  } catch (Exception e) {
	    JOptionPane.showMessageDialog(frame, "Please enter a whole number only.");
	  }
	  return newInt;
	}

  public static void main(String[] args) {
    new Main();
  }
}