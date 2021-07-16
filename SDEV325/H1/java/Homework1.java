import java.text.DateFormat;  
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    


class Homework1 {
    Homework1() {
        LocalDateTime now = LocalDateTime.now();  
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");  

        System.out.println("Hello World! It is currently " + formatter.format(now));
        System.out.println("I hope you enjoy the rest of your day!");
    }
}