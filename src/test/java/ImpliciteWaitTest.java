import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.Random;

public class ImpliciteWaitTest extends DriverSetUp {

    @Test
    public void impliciteWaitTest() {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        driver.get("http://www.automationpractice.pl/index.php?id_product=1&controller=product");
        driver.findElement(By.cssSelector("#add_to_cart > button")).click();
        driver.findElement(By.cssSelector("#layer_cart > div.clearfix > div.layer_cart_cart.col-xs-12.col-md-6 > div.button-container > a")).click();
        driver.findElement(By.cssSelector("#center_column > p.cart_navigation.clearfix > a.button.btn.btn-default.standard-checkout.button-medium")).click();

        WebElement emailInput = driver.findElement(By.id("email_create"));
        emailInput.sendKeys(generateRandomEmail());

        driver.findElement(By.id("SubmitCreate")).click();
        driver.findElement(By.id("uniform-id_gender1")).click();
        driver.findElement(By.id("customer_firstname")).sendKeys("John");
        driver.findElement(By.id("customer_lastname")).sendKeys("Doe");

        WebElement emailField = driver.findElement(By.id("email"));
        emailField.clear();
        emailField.sendKeys(generateRandomEmail());

        driver.findElement(By.id("passwd")).sendKeys("your_password");
        driver.findElement(By.id("days")).sendKeys("1");
        driver.findElement(By.id("months")).sendKeys("January");
        driver.findElement(By.id("years")).sendKeys("1990");
        WebElement newsletterCheckbox = driver.findElement(By.id("newsletter"));
        if (!newsletterCheckbox.isSelected()) {
            newsletterCheckbox.click();
        }

        driver.findElement(By.id("submitAccount")).click();
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

        driver.findElement(By.id("submitAddress")).click();
        driver.findElement(By.cssSelector("button[name='processAddress']")).click();
        driver.findElement(By.cssSelector("#uniform-cgv")).click();
        driver.findElement(By.cssSelector("button[name='processCarrier']")).click();
        driver.findElement(By.cssSelector("a.cheque")).click();
        driver.findElement(By.cssSelector("button[type='submit'].button-medium")).click();

        WebElement successMessage = driver.findElement(By.cssSelector("p.alert.alert-success"));
        Assertions.assertThat(successMessage.getText()).isEqualTo("Your order on My Shop is complete.");
    }


    private String generateRandomEmail() {
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
