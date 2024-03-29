import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static org.openqa.selenium.support.ui.ExpectedConditions.stalenessOf;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class ShoppingCartTest extends BaseTest {

    @Test
    public void shoppingCart() {

        for (int i = 1; i < 4 ; i++) {
            driver.get("http://localhost/litecart");
            wait(3);
            driver.findElements(By.cssSelector("div#box-campaigns li")).get(0).click();
            Select selectProduct = new Select(driver.findElement(By.name("options[Size]")));
            selectProduct.selectByValue("Small");
            wait(1);
            driver.findElement(By.cssSelector("button[name='add_cart_product']")).click();
            wait(3);
            Assert.assertEquals(driver.findElement(By.cssSelector("div#cart span.quantity")).getText(), String.valueOf(i));
        }

        // корзина
        driver.findElement(By.cssSelector("div#cart a.link")).click();
        wait(3);
        WebElement tableElement = driver.findElement(By.cssSelector("div#order_confirmation-wrapper"));
        wait.until(visibilityOf(tableElement));
        while (driver.findElements(By.cssSelector("[name=remove_cart_item]")).size() != 0) {
            driver.findElement(By.cssSelector("[name=remove_cart_item]")).click();
            wait.until(stalenessOf(tableElement));
        }
        Assert.assertTrue(driver.findElement(By.tagName("p")).getText().contains("There are no items in your cart."));
    }
}
