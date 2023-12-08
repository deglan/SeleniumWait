import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class ExplicitWaitTest extends DriverSetUp {

    @Test
    public void expliciteWaitTest() {
        PasswordHelper passwordHelper = new PasswordHelper();
        // Add product to cart and proceed to checkour
        waitToBeClickableAndClick(By.cssSelector("#add_to_cart > button"));
        waitToBeClickableAndClick(By.cssSelector("#layer_cart > div.clearfix > div.layer_cart_cart.col-xs-12.col-md-6 > div.button-container > a"));
        waitToBeClickableAndClick(By.cssSelector("#center_column > p.cart_navigation.clearfix > a.button.btn.btn-default.standard-checkout.button-medium"));
        // Create account - email
        waitToBeClickableAndClick(By.id("email_create"));
        String randomEmail = passwordHelper.generateRandomEmail();
        waitAndSendKeys(By.id("email_create"), randomEmail);
        // Create account - personal information
        waitToBeClickableAndClick(By.id("SubmitCreate"));
        waitToBeClickableAndClick(By.id("uniform-id_gender1"));
        findAndSendKeys(By.id("customer_firstname"), "John");
        findAndSendKeys(By.id("customer_lastname"), "Doe");
        findClearAndSendKeys(By.id("email"), randomEmail);
        findAndSendKeys(By.id("passwd"), "your_password");
        chooseDateOfBirth();
        subscribeToNewsletter();
        waitToBeClickableAndClick(By.id("submitAccount"));
        // Create account - address
        findAndSendKeys(By.id("firstname"), "John");
        findAndSendKeys(By.id("lastname"), "Doe");
        findAndSendKeys(By.id("company"), "MyCompany");
        findAndSendKeys(By.id("address1"), "123 Main St");
        findAndSendKeys(By.id("address2"), "Apt 4");
        findAndSendKeys(By.id("city"), "New York");
        chooseFromDropdown(By.id("id_state"), "New York");
        findAndSendKeys(By.id("postcode"), "10001");
        chooseFromDropdown(By.id("id_country"), "United States");
        findAndSendKeys(By.id("phone"), "1234567890");
        findAndSendKeys(By.id("phone_mobile"), "0987654321");
        findAndSendKeys(By.id("alias"), "My new address");
        // Confirm address
        waitToBeClickableAndClick(By.id("submitAddress"));
        waitToBeClickableAndClick(By.cssSelector("button[name='processAddress']"));
        // Choose shipping method
        waitToBeClickableAndClick(By.cssSelector("#uniform-cgv"));
        waitToBeClickableAndClick(By.cssSelector("button[name='processCarrier']"));
        // Choose payment method
        waitToBeClickableAndClick(By.cssSelector("a.cheque"));
        // Confirm order
        waitToBeClickableAndClick(By.cssSelector("button[type='submit'].button-medium"));

        String actualMessage = waitToBeVisible(By.cssSelector("p.alert.alert-success")).getText();
        String expectedMessage = "Your order on My Shop is complete.";
        assertThat(actualMessage).isEqualTo(expectedMessage);

        log.info("Expected message: '" + expectedMessage + "'. Actual message: '" + actualMessage + "'");
    }

    private void waitToBeClickableAndClick(By locator) {
        WebElement element = defaultWait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
    }

    private WebElement waitToBeVisible(By locator) {
        return defaultWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    private void waitAndSendKeys(By locator, String keys) {
        WebElement element = defaultWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.sendKeys(keys);
    }

    private void findAndSendKeys(By locator, String keys) {
        WebElement element = defaultWait.until(ExpectedConditions.elementToBeClickable(locator));
        element.sendKeys(keys);
    }

    private void findClearAndSendKeys(By locator, String keys) {
        WebElement element = defaultWait.until(ExpectedConditions.elementToBeClickable(locator));
        element.clear();
        element.sendKeys(keys);
    }

    private void chooseDateOfBirth() {
        WebElement daySelect = driver.findElement(By.id("days"));
        daySelect.sendKeys("1");
        WebElement monthSelect = driver.findElement(By.id("months"));
        monthSelect.sendKeys("January");
        WebElement yearSelect = driver.findElement(By.id("years"));
        yearSelect.sendKeys("1990");
    }

    private void chooseFromDropdown(By locator, String keys) {
        WebElement element = driver.findElement(locator);
        element.sendKeys("United States");
    }

    private void subscribeToNewsletter() {
        WebElement newsletterCheckbox = driver.findElement(By.id("newsletter"));
        if (!newsletterCheckbox.isSelected()) {
            newsletterCheckbox.click();
        }
    }
}
