import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@AllArgsConstructor
@NoArgsConstructor
public class DriverSetUp {

    protected WebDriver driver;
    protected Actions actions;

    @BeforeAll
    public static void setUp() {
        WebDriverManager.firefoxdriver().setup();
    }

    @BeforeEach
    public void driverSetUp() {
        driver = new FirefoxDriver();
        driver.get("http://www.automationpractice.pl/index.php");
        actions = new Actions(driver);
    }

    @AfterEach
    public void closeDriver() {
        driver.quit();
    }
}
