import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DriverSetUp {

    protected static WebDriver driver;
    protected static Actions actions;
    protected static WebDriverWait defaultWait;

    @BeforeAll
    public static void setUp() {
        driver = new ChromeDriver();
        actions = new Actions(driver);
        defaultWait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @BeforeEach
    public void driverSetUp() {
        driver.get("http://www.automationpractice.pl/index.php?id_product=1&controller=product");
    }

    @AfterEach
    public void closeDriver() {
        driver.quit();
    }
}
