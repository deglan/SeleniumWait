import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class ImplicitWaitTest extends DriverSetUp {

    @Test
    public void impliciteWaitTest() {
        PasswordHelper passwordHelper = new PasswordHelper();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        // Add product to cart and proceed to checkour
        driver.findElement(By.cssSelector("#add_to_cart > button")).click();
        driver.findElement(By.cssSelector("#layer_cart > div.clearfix > div.layer_cart_cart.col-xs-12.col-md-6 > div.button-container > a")).click();
        driver.findElement(By.cssSelector("#center_column > p.cart_navigation.clearfix > a.button.btn.btn-default.standard-checkout.button-medium")).click();
        // Create account - email
        WebElement emailInput = driver.findElement(By.id("email_create"));
        emailInput.sendKeys(passwordHelper.generateRandomEmail());
        // Create account - personal information
        driver.findElement(By.id("SubmitCreate")).click();
        driver.findElement(By.id("uniform-id_gender1")).click();
        driver.findElement(By.id("customer_firstname")).sendKeys("John");
        driver.findElement(By.id("customer_lastname")).sendKeys("Doe");
        WebElement emailField = driver.findElement(By.id("email"));
        emailField.clear();
        emailField.sendKeys(passwordHelper.generateRandomEmail());
        driver.findElement(By.id("passwd")).sendKeys("your_password");
        driver.findElement(By.id("days")).sendKeys("1");
        driver.findElement(By.id("months")).sendKeys("January");
        driver.findElement(By.id("years")).sendKeys("1990");
        WebElement newsletterCheckbox = driver.findElement(By.id("newsletter"));
        if (!newsletterCheckbox.isSelected()) {
            newsletterCheckbox.click();
        }
        driver.findElement(By.id("submitAccount")).click();
        // Create account - address
        driver.findElement(By.id("firstname")).sendKeys("John");
        driver.findElement(By.id("lastname")).sendKeys("Doe");
        driver.findElement(By.id("company")).sendKeys("MyCompany");
        driver.findElement(By.id("address1")).sendKeys("123 Main St");
        driver.findElement(By.id("address2")).sendKeys("Apt 4");
        driver.findElement(By.id("city")).sendKeys("New York");
        driver.findElement(By.id("id_state")).sendKeys("New York");
        driver.findElement(By.id("postcode")).sendKeys("10001");
        driver.findElement(By.id("id_country")).sendKeys("United States");
        driver.findElement(By.id("phone")).sendKeys("1234567890");
        driver.findElement(By.id("phone_mobile")).sendKeys("0987654321");
        driver.findElement(By.id("alias")).clear();
        driver.findElement(By.id("alias")).sendKeys("My new address");
        // Confirm address
        driver.findElement(By.id("submitAddress")).click();
        driver.findElement(By.cssSelector("button[name='processAddress']")).click();
        // Choose shipping method
        driver.findElement(By.cssSelector("#uniform-cgv")).click();
        driver.findElement(By.cssSelector("button[name='processCarrier']")).click();
        // Choose payment method
        driver.findElement(By.cssSelector("a.cheque")).click();
        driver.findElement(By.cssSelector("button[type='submit'].button-medium")).click();

        String actualMessage = driver.findElement(By.cssSelector("p.alert.alert-success")).getText();
        String expectedMessage = "Your order on My Shop is complete.";
        assertThat(actualMessage).isEqualTo(expectedMessage);

        log.info("Expected message: '" + expectedMessage + "'. Actual message: '" + actualMessage + "'");
    }


}
