import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class StickerTest extends BaseTest {

    @Test
    public void testInstance() {
        driver.get("http://localhost/litecart/en/");
        List<WebElement> elements = driver.findElements(By.className("product"));
        for (WebElement e : elements) {
            Assert.assertTrue(e.findElements(By.className("sticker")).size() == 1);
        }
    }
}
