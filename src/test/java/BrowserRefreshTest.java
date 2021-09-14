import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chromium.ChromiumDevToolsLocator;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.testng.annotations.Test;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BrowserRefreshTest {

    @Test
    public void browserRefreshTest() throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--auto-open-devtools-for-tabs");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();


        driver.get("https://fruitshoppe.firebaseapp.com/");

        //Navigate to 'mangocados' and add 'bananas' to cart. The navigate to cart.
        driver.findElement(By.xpath("//a[contains(.,'Mangocados')]")).click();
        driver.findElement(By.xpath("//div[@class='fruit-box fruit-banans']//a[.='Add to cart']")).click();

        driver.navigate().refresh();

        driver.findElement(By.xpath("//span[.='My Cart']")).click();

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

