import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ProductPageTest extends BaseTest {

    @Test
    public void testInstance() {
        driver.get("http://localhost/litecart/en/");

        // иконка товара на главной
        WebElement product = driver.findElement(By.cssSelector("#box-campaigns .product"));
        String productLink = product.findElement(By.cssSelector(".link")).getAttribute("href");
        String productName = product.findElement(By.cssSelector(".name")).getAttribute("textContent");

        WebElement regularPrice = product.findElement(By.cssSelector(".regular-price"));
        WebElement campaignPrice = product.findElement(By.cssSelector(".campaign-price"));

        String regularPriceValue = regularPrice.getAttribute("textContent");
        String campaignPriceValue = campaignPrice.getAttribute("textContent");
        String regularPriceClass = regularPrice.getAttribute("class");
        String campaignPriceClass = campaignPrice.getAttribute("class");
        String regularPriceTag = regularPrice.getAttribute("tagName");
        String campaignPriceTag = campaignPrice.getAttribute("tagName");

        product.click();

        // внутри страницы товара
        String productLinkPage = driver.findElement(By.cssSelector("link[rel=canonical")).getAttribute("href");
        String productNamePage = driver.findElement(By.cssSelector("#box-product .title")).getAttribute("textContent");

        WebElement regularPricePage = driver.findElement(By.cssSelector("#box-product .regular-price"));
        WebElement campaignPricePage = driver.findElement(By.cssSelector("#box-product .campaign-price"));

        String regularPriceValuePage = regularPricePage.getAttribute("textContent");
        String campaignPriceValuePage = campaignPricePage.getAttribute("textContent");
        String regularPriceClassPage = regularPricePage.getAttribute("class");
        String campaignPriceClassPage = campaignPricePage.getAttribute("class");
        String regularPriceTagPage = regularPricePage.getAttribute("tagName");
        String campaignPriceTagPage = campaignPricePage.getAttribute("tagName");

        Assert.assertEquals(productLink, productLinkPage);
        Assert.assertEquals(productName, productNamePage);
        Assert.assertEquals(regularPriceValue, regularPriceValuePage);
        Assert.assertEquals(campaignPriceValue, campaignPriceValuePage);
        Assert.assertEquals(regularPriceClass, regularPriceClassPage);
        Assert.assertEquals(campaignPriceClass, campaignPriceClassPage);
        Assert.assertEquals(regularPriceTag, regularPriceTagPage);
        Assert.assertEquals(campaignPriceTag, campaignPriceTagPage);
    }
}
