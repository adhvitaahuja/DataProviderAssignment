package Task1;
/*
Dataprovider to read data from excel using hashmap with Optimized code
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Question1 {
    @Test(dataProvider = "getdata")
    public void login(Map<Object, Object> mapdata) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys(mapdata.get("username").toString());
        driver.findElement(By.xpath("//input[@id='passwd']")).sendKeys(mapdata.get("password").toString());
        driver.findElement(By.xpath("//button[@id='SubmitLogin']")).click();
        Assert.assertTrue(driver.getPageSource().contains("Welcome to your account"),"Account Not Created");
        System.out.println("Account created successfully!!");
        driver.close();
    }
    @DataProvider(name ="getdata")
    public Object[][] dataproviderMethod() {
        FileInputStream fis = null;
        XSSFSheet ws = null;
        XSSFWorkbook wb = null;
        String filePath = "./datafile.xlsx";
        File xls = new File(filePath);
        try {
            fis = new FileInputStream(xls);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            wb = new XSSFWorkbook(fis);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        ws = wb.getSheet("Sheet1");
        int rowcount = ws.getLastRowNum();
        int colcount = ws.getRow(0).getLastCellNum();
        Object[][] data = new Object[rowcount][1];
        for (int r = 0; r < rowcount; r++) {
            HashMap<Object, Object> hm = new HashMap<Object, Object>();
            for (int c = 0; c < colcount; c++) {
                hm.put(ws.getRow(0).getCell(c).toString(), ws.getRow(r + 1).getCell(c).toString());
            }
            data[r][0] = hm;
        }
        try {
            wb.close();
        } catch (IOException e) {

            e.printStackTrace();
        }
        return data;
    }
}