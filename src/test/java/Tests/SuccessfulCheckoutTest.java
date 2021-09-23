package Tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;
public class SuccessfulCheckoutTest {

        @Test
        public void successfulCheckout() {

                WebDriverManager.chromedriver().setup();
                WebDriver driver = new ChromeDriver();
                driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                driver.manage().window().maximize();
                driver.get("https://fruitshoppe.firebaseapp.com/");

                //Navigate to 'mangocados' and add 'bananas' to cart. The navigate to cart.
                driver.findElement(By.xpath("//a[contains(.,'Mangocados')]")).click();
                driver.findElement(By.xpath("//div[@class='fruit-box fruit-banans']//a[.='Add to cart']")).click();
                driver.findElement(By.xpath("//span[.='My Cart']")).click();

                //click on checkout button and fill up all required information
                driver.findElement(By.cssSelector("[ng-click='checkout()']")).click();

                //Enter billing/shipping info and place order
                driver.findElement(By.id("billing-firstname")).sendKeys("John");
                driver.findElement(By.id("billing-lastname")).sendKeys("Doe");

                driver.findElement(By.
                        xpath("//div[@class='market-place ng-scope']//form[1]/div[2]/input[@class='form-control input-sm']"))
                        .sendKeys("333 Johnson Drive");
                driver.findElement(By.id("billing-city")).sendKeys("Huntsville");
                driver.findElement(By.id("billing-zip")).sendKeys("09022");
                driver.findElement(By.id("shipping-same-billing")).click();
                driver.findElement(By.id("credit_card_number")).sendKeys("12345678910");
                driver.findElement(By.id("credit_card_cvv")).sendKeys("199");
                driver.findElement(By.id("im-sure-check")).click();

                driver.findElement(By.xpath("//a[.='Purchase']")).click();


                LogEntries entry = driver.manage().logs().get(LogType.BROWSER);
                // Retrieving all log
                List<LogEntry> logs = entry.getAll();
                // Print one by one
                for (LogEntry e : logs) {
                        System.out.println(e);
                }

                // Printing details separately
                for (LogEntry e : logs) {
                        System.out.println("Message is: " + e.getMessage());
                        System.out.println("Level is: " + e.getLevel());

                }
                driver.quit();
                
        }
}