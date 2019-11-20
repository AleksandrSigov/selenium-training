import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    public WebDriver driver;
    public WebDriverWait wait;

    @Before
    public void startUp() {
        System.setProperty("webdriver.chrome.driver", "C:/tools/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
    }

    @After
    public void tearDown() {
        driver.quit();
        driver = null;
    }
}
