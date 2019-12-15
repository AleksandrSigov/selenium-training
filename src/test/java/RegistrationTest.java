import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.Random;

public class RegistrationTest extends BaseTest {

    Random random = new Random();
    int rNumber = random.nextInt(100);
    String name = "Kek";
    String lastName = "Kekov";
    String address = "Kek avenue";
    String postCode = "05588";
    String city = "Alabama";
    String email = "kek" + rNumber + "@kekmail.com";
    String phone = "+79998887744";
    String password = "12345678";

    @Test
    public void testInstance() {
        driver.get("http://localhost/litecart/en/");

        WebElement link = driver.findElement(By.cssSelector("div#box-account-login a[href*=create_account]"));
        link.click();
        wait(10);

        WebElement firstName = driver.findElement(By.name("firstname"));
        firstName.click();
        firstName.clear();
        firstName.sendKeys(name);

        WebElement lastNameE = driver.findElement(By.name("lastname"));
        lastNameE.click();
        lastNameE.clear();
        lastNameE.sendKeys(lastName);

        WebElement address1 = driver.findElement(By.name("address1"));
        address1.click();
        address1.clear();
        address1.sendKeys(address);

        WebElement postcode = driver.findElement(By.name("postcode"));
        postcode.click();
        postcode.clear();
        postcode.sendKeys(postCode);

        WebElement cityE = driver.findElement(By.name("city"));
        cityE.click();
        cityE.clear();
        cityE.sendKeys(city);

        Select countryE = new Select(driver.findElement(By.cssSelector("select[name=country_code]")));
        countryE.selectByValue("US");

        Select stateE = new Select(driver.findElement(By.cssSelector("select[name=zone_code]")));
        stateE.selectByValue("AK");

        WebElement emailE = driver.findElement(By.name("email"));
        emailE.click();
        emailE.clear();
        emailE.sendKeys(email);

        WebElement phoneE = driver.findElement(By.name("phone"));
        phoneE.click();
        phoneE.clear();
        phoneE.sendKeys(phone);

        WebElement passwordE = driver.findElement(By.name("password"));
        passwordE.click();
        passwordE.clear();
        passwordE.sendKeys(password);

        WebElement passwordConfirm = driver.findElement(By.name("confirmed_password"));
        passwordConfirm.click();
        passwordConfirm.clear();
        passwordConfirm.sendKeys(password);

        WebElement submit = driver.findElement(By.cssSelector("button[type=submit]"));
        submit.click();

        wait(10);
        String accountCreatedText = driver.findElement(By.cssSelector("div#main div#notices-wrapper")).getText();


        //Log out
        logout();

        //Log in
        login(email, password);
    }

    public void logout() {
        WebElement logout = driver.findElement(By.cssSelector("div#box-account a[href*=logout]"));
        logout.click();
        wait(10);
    }

    public void login(String login, String password) {
        WebElement loginE = driver.findElement(By.cssSelector("div#box-account-login input[name=email]"));
        loginE.click();
        loginE.clear();
        loginE.sendKeys(login);

        WebElement passwordE = driver.findElement(By.cssSelector("div#box-account-login input[name=password]"));
        passwordE.click();
        passwordE.clear();
        passwordE.sendKeys(password);

        WebElement loginBtn = driver.findElement(By.cssSelector("div#box-account-login button[name=login]"));
        loginBtn.click();
        wait(10);

        String text = driver.findElement(By.cssSelector("div#main div#notices-wrapper")).getText();
        String expectedMessage = "You are now logged in as " + name + " " + lastName + ".";
        if (!text.equals(expectedMessage)) {
            Assert.fail();
        }
    }
}
