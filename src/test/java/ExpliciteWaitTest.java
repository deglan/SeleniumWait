import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Random;

public class ExpliciteWaitTest extends DriverSetUp {

    @Test
    public void expliciteWaitTest() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("http://www.automationpractice.pl/index.php?id_product=1&controller=product");
        WebElement addToCart = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#add_to_cart > button")));
        addToCart.click();
        WebElement proceedToCheckout = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#layer_cart > div.clearfix > div.layer_cart_cart.col-xs-12.col-md-6 > div.button-container > a")));
        proceedToCheckout.click();
        WebElement checkout = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#center_column > p.cart_navigation.clearfix > a.button.btn.btn-default.standard-checkout.button-medium")));
        checkout.click();
        WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email_create")));
        String randomEmail = generateRandomEmail();
        emailInput.sendKeys(randomEmail);
        WebElement createAccountButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("SubmitCreate")));
        createAccountButton.click();
        WebElement genderMale = wait.until(ExpectedConditions.elementToBeClickable(By.id("uniform-id_gender1")));
        genderMale.click();
        WebElement firstNameInput = driver.findElement(By.id("customer_firstname"));
        firstNameInput.sendKeys("John");
        WebElement lastNameInput = driver.findElement(By.id("customer_lastname"));
        lastNameInput.sendKeys("Doe");
        WebElement emailField = driver.findElement(By.id("email"));
        emailField.clear();
        emailField.sendKeys(randomEmail);
        WebElement passwordInput = driver.findElement(By.id("passwd"));
        passwordInput.sendKeys("your_password");
        WebElement daySelect = driver.findElement(By.id("days"));
        daySelect.sendKeys("1");
        WebElement monthSelect = driver.findElement(By.id("months"));
        monthSelect.sendKeys("January");
        WebElement yearSelect = driver.findElement(By.id("years"));
        yearSelect.sendKeys("1990");
        WebElement newsletterCheckbox = driver.findElement(By.id("newsletter"));
        if (!newsletterCheckbox.isSelected()) {
            newsletterCheckbox.click();
        }
        wait.until(ExpectedConditions.elementToBeClickable(By.id("submitAccount"))).click();
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

        wait.until(ExpectedConditions.elementToBeClickable(By.id("submitAddress"))).click();
        WebElement proceedToCheckoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[name='processAddress']")));
        proceedToCheckoutButton.click();
        WebElement agree = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#uniform-cgv")));
        agree.click();
        WebElement proceedToPayment = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[name='processCarrier']")));
        proceedToPayment.click();
        WebElement payByCheckLink = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.cheque")));
        payByCheckLink.click();
        WebElement confirmOrderButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit'].button-medium")));
        confirmOrderButton.click();
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p.alert.alert-success")));

        Assertions.assertThat(successMessage.getText()).isEqualTo("Your order on My Shop is complete.");
    }


    public static String generateRandomEmail() {
        String allowedChars = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder email = new StringBuilder();
        Random random = new Random();
        int length = 7;

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(allowedChars.length());
            email.append(allowedChars.charAt(index));
        }

        email.append(random.nextInt(100));
        email.append("@example.com");
        return email.toString();
    }
}
