package dmotsnyi;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static java.lang.Thread.sleep;

public class SearchPage {
    private final WebDriver driver;

    By resultSearch = By.xpath("//span[@class='a-color-state a-text-bold']");
    By productListSearchResult = By.xpath("//div[@class=\"sg-col-20-of-24 s-result-item s-asin sg-col-0-of-12 sg-col-16-of-20 sg-col s-widget-spacing-small sg-col-12-of-16\"]");
    By productListSearchCount= By.xpath("//div[@class=\"s-matching-dir sg-col-16-of-20 sg-col sg-col-8-of-12 sg-col-12-of-16\"]");
    By sortSpan = By.id("p_36-title");

    public SearchPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getTextOfResultSearch() { return driver.findElement(resultSearch).getText().replace("\"", "");}

    public String isSortOnPrice() {return driver.findElement(sortSpan).getText();}

    public List<WebElement> getListWithItemsCount() {return driver.findElements(productListSearchResult);}
    public List<WebElement> getItem() {return driver.findElements(productListSearchCount);}

    public ProductPage getProductByName(String  name) {
        if(isProductByName(name)) {
            try {
                sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            getListWithItemsCount().stream().filter(webElement -> webElement.getText().contains(name)).findFirst().get().click();
            return new ProductPage(driver);
        }
        else return null;
    }

    public boolean isProductByName(String  name) {
        return getListWithItemsCount().stream().anyMatch(webElement -> webElement.getText().split("\n")[0].equals(name));
    }

}
