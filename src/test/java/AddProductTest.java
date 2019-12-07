import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.io.File;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class AddProductTest extends BaseTest {

    @Test
    public void testInstance() {
        login("admin", "admin");
        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog");

        // строк в каталоге
        int count = count(By.cssSelector(".dataTable tr.row"));
        driver.findElement(By.cssSelector("#content a.button:last-child")).click();
        wait(10);

        // general
        driver.findElement(By.cssSelector("[name=status][value='1']")).click();
        driver.findElement(By.cssSelector("[name='name[en]']")).sendKeys("New product");
        driver.findElement(By.cssSelector("[name=code]")).sendKeys("4541");
        driver.findElement(By.cssSelector("[data-name='Rubber Ducks']")).click();
        driver.findElement(By.cssSelector("[value='1-2']")).click();
        clearAndFillTheField(By.cssSelector("[name=quantity]"), "7.50");
        selectByValue(By.cssSelector("[name=sold_out_status_id]"), "2");
        driver.findElement(By.cssSelector("[type=file]")).sendKeys((new File("1.png").getAbsolutePath()));
        driver.findElement(By.cssSelector("[name=date_valid_from]")).sendKeys("01.12.2019");
        driver.findElement(By.cssSelector("[name=date_valid_to]")).sendKeys("31.12.2019");

        // info
        driver.findElement(By.cssSelector("[href='#tab-information']")).click();
        selectByValue(By.cssSelector("[name=manufacturer_id]"), "1");
        driver.findElement(By.cssSelector("[name=keywords]")).sendKeys("duck");
        driver.findElement(By.cssSelector("[name='short_description[en]']")).sendKeys("duck");
        driver.findElement(By.cssSelector("[name='description[en]']")).sendKeys("green duck");
        driver.findElement(By.cssSelector("[name='head_title[en]']")).sendKeys("green duck");
        driver.findElement(By.cssSelector("[name='meta_description[en]']")).sendKeys("green duck");

        // prices
        driver.findElement(By.cssSelector("[href='#tab-prices']")).click();
        clearAndFillTheField(By.cssSelector("[name='purchase_price']"), "1000");
        selectByValue(By.cssSelector("[name=purchase_price_currency_code]"), "EUR");
        driver.findElement(By.cssSelector("[name='prices[USD]']")).sendKeys("1200");
        driver.findElement(By.cssSelector("[name='prices[EUR]']")).sendKeys("1000");
        driver.findElement(By.cssSelector("#add-campaign")).click();
        setDatepicker(By.cssSelector("[name*=start_date]"), "201-12-01T00:00");
        setDatepicker(By.cssSelector("[name*=end_date]"), "2019-12-31T23:59");
        clearAndFillTheField(By.cssSelector("[name*=percentage]"), "10");
        driver.findElement(By.cssSelector("[name=save]")).click();
        wait.until(presenceOfElementLocated(By.cssSelector(".dataTable")));

        // строки после добавление товара
        int countNew = count(By.cssSelector(".dataTable tr.row"));
        Assert.assertEquals(count + 1, countNew);
    }

    public void setDatepicker(By locator, String date) {
        WebElement datepiсker = driver.findElement(locator);
        JavascriptExecutor.class.cast(driver).executeScript("arguments[0].value=arguments[1]", datepiсker, date);
    }

    public void selectByValue(By locator, String value) {
        Select field = new Select(driver.findElement(locator));
        field.selectByValue(value);
    }

    public void clearAndFillTheField(By locator, String value) {
        WebElement field = driver.findElement(locator);
        field.clear();
        field.sendKeys(value);
    }

    public int count(By locator) {
        return driver.findElements(locator).size();
    }
}
