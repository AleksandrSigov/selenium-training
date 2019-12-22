import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Set;

public class NewWindowTest extends BaseTest {

    @Test
    public void testInstance() {
        login("admin", "admin");
        wait(3);
        driver.findElement(By.cssSelector("a[href*='?app=countries&doc=countries'] span.name")).click();
        wait(1);
        driver.findElement(By.cssSelector("a.button")).click();
        wait(1);
        List<WebElement> windowElements = driver.findElements(By.cssSelector("i.fa.fa-external-link"));

        for (WebElement windowElement : windowElements) {
            String originalWindow = driver.getWindowHandle();
            Set<String> oldWindows = driver.getWindowHandles();
            windowElement.click();
            Set<String> newWindows = driver.getWindowHandles();
            newWindows.removeAll(oldWindows);
            String newWindow = newWindows.iterator().next();
            driver.switchTo().window(newWindow);
            driver.close();
            driver.switchTo().window(originalWindow);
        }
    }
}
