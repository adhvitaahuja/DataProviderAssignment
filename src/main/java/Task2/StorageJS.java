package Task2;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class StorageJS {
    public static JavascriptExecutor js;

    public static String getItemFromLocalStorage(String key) {
        return (String) js.executeScript(String.format(
                "return window.localStorage.getItem('%s');", key));
    }
    public static String getItemFromSessionStorage(String key) {
        return (String) js.executeScript(String.format(
                "return window.sessionStorage.getItem('%s');", key));
    }

    public static void main(String[] args){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        js = ((JavascriptExecutor)driver);
        driver.manage().window().maximize();
        driver.navigate().to("https://www.maxlifeinsurance.com/term-insurance-plans/premium-calculator");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


        String localItem = getItemFromLocalStorage("eligibilityData");
        System.out.println(localItem);
        System.out.println(getItemFromLocalStorage("leadData"));
        System.out.println(getItemFromLocalStorage("__tz"));
        String sessionItem = getItemFromSessionStorage("utmCode");
        System.out.println(sessionItem);
        System.out.println(getItemFromSessionStorage("lastRoute"));
    }
}
