import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MenuHeadersTest extends BaseTest {

    @Test
    public void testInstance() {
        login("admin", "admin");

        // Находим и сохраняем все основные левые пункты меню в лист
        List<WebElement> leftMenuMainElements = driver.findElements(By.className("name"));
        List<String> leftMenuMain = getElementNames(leftMenuMainElements);

        // Кликаем и проверяем заголовок
        for (String s : leftMenuMain) {
            driver.findElement(By.xpath("//span[text()='" + s + "']")).click();
            Assert.assertTrue(driver.findElement(By.tagName("h1")).isDisplayed());

            // Находим и сохраняем все подпункты меню в лист
            List<WebElement> leftMenuSubElements = driver.findElements(By.cssSelector(("[id^=doc-]")));
            List<String> leftMenuSub = getElementNames(leftMenuSubElements);

            // // Кликаем и проверяем заголовок
            for (String s1 : leftMenuSub) {
                driver.findElement(By.xpath("//span[text()='" + s1 + "']")).click();
                Assert.assertTrue(driver.findElement(By.tagName("h1")).isDisplayed());
            }
        }
    }
}
