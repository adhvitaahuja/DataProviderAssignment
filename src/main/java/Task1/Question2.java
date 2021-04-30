package Task1;
/*
Read console data using selenium
 */

import java.util.List;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

public class Question2{
    public static void main(String args[]){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.tothenew.com/");
        driver.manage().window().maximize();
        LogEntries entry = driver.manage().logs().get(LogType.BROWSER);
        List<LogEntry> logs= entry.getAll();

        System.out.println("******************* CONSOLE LOGS *******************");

        for(LogEntry e: logs)
        {
            System.out.println("Message is: " +e.getMessage());
            System.out.println("Level is: " +e.getLevel());
        }
        driver.close();
    }
}
