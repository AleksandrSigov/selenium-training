import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CountryOrderTest extends BaseTest {

    @Test
    public void testAlphabeticalOrderCountries() {

        login("admin", "admin");
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");

        String previousCountryName = null;

        List<WebElement> rows = driver.findElements(By.cssSelector("table.dataTable tr.row"));
        for (int j = 0; j < rows.size(); j++) {
            String countryName = rows.get(j).findElements(By.cssSelector("td")).get(4).findElement((By.cssSelector("a"))).getText();
            if (previousCountryName != null && !(countryName.compareToIgnoreCase(previousCountryName) > 0)) {
                Assert.fail();
            }
            previousCountryName = countryName;

            String countryZone = rows.get(j).findElements(By.cssSelector("td")).get(5).getText();
            if (!countryZone.equals("0")) {
                WebElement countryLink = rows.get(j).findElements(By.cssSelector("td")).get(4).findElement((By.cssSelector("a")));
                countryLink.click();

                String previousZone = null;
                List<WebElement> rowsZones = driver.findElements(By.cssSelector("table#table-zones tr"));
                for (int k = 1; k < rowsZones.size() - 1; k++) {
                    String zone = rowsZones.get(k).findElements(By.cssSelector("td")).get(2).getText();

                    if (previousZone != null && !(zone.compareToIgnoreCase(previousZone) > 0)) {
                        Assert.fail();
                    }
                    previousZone = zone;
                }

                driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
                rows = driver.findElements(By.cssSelector("table.dataTable tr.row"));
            }
        }
    }
}

