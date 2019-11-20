import org.junit.Test;
import org.openqa.selenium.By;

public class FirstClass extends BaseTest {

    @Test
    public void testInstance() {
        driver.get("https://www.google.com/");
        driver.findElement(By.name("q")).sendKeys("Selenium WebDriver");
        driver.findElement(By.name("btnK")).click();
    }
}
