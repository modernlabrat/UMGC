import javax.swing.*;


public class OutsideBounds extends Exception {
    public OutsideBounds(String errorMessage) 
    { 
      super(errorMessage);
    } 
}