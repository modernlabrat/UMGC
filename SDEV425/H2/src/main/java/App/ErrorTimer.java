package App;

/*
 * Kyra Samuel
 * Homework2
 * SDEV425
 * 04/29/2022
 */


import java.util.TimerTask;
import javafx.scene.text.Text;

class ErrorTimer extends TimerTask {
  // a timer task that clears the error text 
  private Text errorText;

  ErrorTimer(Text errorText) {
    this.errorText = errorText;
  }

  public void run() {
    this.errorText.setText(" ");
  }
}
