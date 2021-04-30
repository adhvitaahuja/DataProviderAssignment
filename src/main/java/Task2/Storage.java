package Task2;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.SessionStorage;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.remote.Augmenter;

import java.util.concurrent.TimeUnit;

public class Storage {
    public static JavascriptExecutor js;

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        js = ((JavascriptExecutor) driver);
        driver.manage().window().maximize();
        driver.navigate().to("https://www.maxlifeinsurance.com/term-insurance-plans/premium-calculator");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebStorage webStorage = (WebStorage) new Augmenter().augment(driver);
        LocalStorage localStorage = webStorage.getLocalStorage();
        SessionStorage sessionStorage = webStorage.getSessionStorage();

        System.out.println(localStorage.getItem("eligibilityData"));
        System.out.println(localStorage.getItem("__tz"));
        System.out.println(sessionStorage.getItem("utmCode"));
        System.out.println(sessionStorage.getItem("lastRoute"));
        System.out.println(sessionStorage.getItem("lead"));
    }
}
