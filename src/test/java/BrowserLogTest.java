import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;


public class BrowserLogTest extends BaseTest {

    @Test
    public void testInstance() {
        login("admin", "admin");
        wait(3);
        driver.findElement(By.cssSelector("a[href*='?app=catalog&doc=catalog']")).click();
        wait(3);
        driver.findElement(By.cssSelector("a[href*='?app=catalog&doc=catalog&category_id=1']")).click();
        List<WebElement> products = driver.findElements(By.cssSelector("a[href*='product_id']"));
        List<String> productNames = new ArrayList<String>();
        for(int i = 0; i < products.size(); i++) {
            productNames.add(products.get(i).getText());
        }

        for(String productName : productNames) {
            driver.findElement(By.linkText(productName)).click();
            driver.navigate().back();
        }

        driver.manage().logs().get("browser").forEach(log -> {
            System.out.println(log);
            Assert.assertTrue(log.equals(""));
        });
    }
}
