import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class GeoZonesTest extends BaseTest {

    @Test
    public void testInstance() {
        login("admin", "admin");
        driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");

        List<WebElement> geoZonesList = driver.findElements(By.cssSelector("table.dataTable tr.row"));
        for (int i = 0; i < geoZonesList.size(); i++) {
            WebElement geoZone = geoZonesList.get(i).findElements(By.cssSelector("td")).get(2).findElement((By.cssSelector("a")));
            geoZone.click();

            String geoZonesPrevious = null;
            List<WebElement> geoZonesCountryList = driver.findElements(By.cssSelector("table#table-zones tr"));
            for (int y = 1; y < geoZonesCountryList.size() - 1; y++) {
                String geoZones = geoZonesCountryList.get(y).findElements(By.cssSelector("td")).get(2).findElement((By.cssSelector("select option[selected='selected']"))).getText();

                if (geoZonesPrevious != null && !(geoZones.compareToIgnoreCase(geoZonesPrevious) > 0)) {
                    Assert.fail();
                }
                geoZonesPrevious = geoZones;
            }
            driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
            geoZonesList = driver.findElements(By.cssSelector("table.dataTable tr.row"));
        }
    }
}
