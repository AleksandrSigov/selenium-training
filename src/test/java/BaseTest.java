import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    public WebDriver driver;
    public WebDriverWait wait;

    @Before
    public void startUp() {
        System.setProperty("webdriver.chrome.driver", "C:/tools/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 20);
    }

    @After
    public void tearDown() {
        driver.quit();
        driver = null;
    }

    public void login(String username, String password) {
        driver.get("http://localhost/litecart/admin");
        driver.findElement(By.name("username")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("login")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("name")));
    }

    public List<String> getElementNames(List<WebElement> elements){
        List<String> names = new ArrayList<String>();
        for(WebElement e : elements){
            names.add(e.getText());
        }
        return names;
    }
}
