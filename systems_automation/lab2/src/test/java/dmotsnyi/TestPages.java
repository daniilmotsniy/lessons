package dmotsnyi;

import dmotsnyi.config.ConfigurationProperties;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class TestPages {
    private HomePage homePage;
    private SearchPage searchPage;
    private ProductPage productPage;
    private CartPage cartPage;

    @BeforeEach
    public void init() {
        System.setProperty("webdriver.chrome.driver", ConfigurationProperties.getProperty("chromedriver"));

        WebDriver driver = new ChromeDriver();
        homePage = new HomePage(driver);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(ConfigurationProperties.getProperty("homePage"));
    }

    @AfterEach
    public void tearDown() {
        homePage.getDriver().quit();
    }

    @Test
    public void searchProduct() {
        String text = ConfigurationProperties.getProperty("searchItem");
        homePage.enterTextIntoSearchBar(text);
        String actual = homePage.clickSearch().getTextOfResultSearch();
        assertEquals(text, actual);
    }

    @Test
    public void homePageContainsGamingCat() {
        String text = ConfigurationProperties.getProperty("searchItemGamingCategory");
        List<String> listSalesPost = new ArrayList<>();
        homePage.getNamesSalesPost().forEach(webElement -> listSalesPost.add(webElement.getText()));
        listSalesPost.forEach(System.out::println);
        assertTrue(listSalesPost.contains(text));
    }

    @Test
    void getStatus() {
        cartPage = homePage.getCartPage();
        assertEquals(ConfigurationProperties.getProperty("statusCart"), cartPage.getStatusCart());
    }

    @Test
    void getSigInBtn() {
        cartPage = homePage.getCartPage();
        assertEquals(ConfigurationProperties.getProperty("signIn"), cartPage.getLocatorSigIn());
    }

    @Test
    void getSignUpBtn() {
        cartPage = homePage.getCartPage();
        assertEquals(ConfigurationProperties.getProperty("signUp"), cartPage.getLocatorSignUp());
    }

    @Test
    public void homePageContainsHealthCat() {
        String text = ConfigurationProperties.getProperty("searchHealthCategory");
        List<String> listSalesPost = new ArrayList<>();
        homePage.getNamesSalesPost().forEach(webElement -> listSalesPost.add(webElement.getText()));
        listSalesPost.forEach(System.out::println);
        assertTrue(listSalesPost.contains(text));
    }

    @Test
    public void changeLanguage() {
        homePage.changeLanguage();
        assertEquals(ConfigurationProperties.getProperty("sell"), homePage.getStringSellOnMainPanel());
    }

    @Test
    public void isSortByPrice() {
        homePage.enterTextIntoSearchBar(ConfigurationProperties.getProperty("searchItem"));
        searchPage = homePage.clickSearch();
        assertEquals(ConfigurationProperties.getProperty("sort"), searchPage.isSortOnPrice());
    }

    @Test
    public void isItemsNotEmpty() {
        homePage.enterTextIntoSearchBar(ConfigurationProperties.getProperty("searchItem"));
        searchPage =  homePage.clickSearch();
        List<WebElement> products = searchPage.getListWithItemsCount();
        assertFalse(products.isEmpty());
    }

    @Test
    public void searchOneItem() {
        homePage.enterTextIntoSearchBar(ConfigurationProperties.getProperty("searchItem"));
        searchPage = homePage.clickSearch();
        List<WebElement> products = searchPage.getItem();
        assertEquals(1, products.size());
    }

    @Test
    public void isProductPresentInPage() {
        homePage.enterTextIntoSearchBar(ConfigurationProperties.getProperty("searchItem"));
        searchPage = homePage.clickSearch();
        assertTrue(searchPage.isProductByName(ConfigurationProperties.getProperty("productName")));
    }

    @Test
    public void isProductNotPresentInPage() {
        homePage.enterTextIntoSearchBar(ConfigurationProperties.getProperty("searchItem"));
        searchPage = homePage.clickSearch();
        assertFalse(searchPage.isProductByName("donut"));
    }

    @Test
    public void getNameOfProduct() {
        homePage.enterTextIntoSearchBar(ConfigurationProperties.getProperty("searchItem"));
        searchPage = homePage.clickSearch();
        productPage = searchPage.getProductByName(ConfigurationProperties.getProperty("productName"));
        assertEquals(ConfigurationProperties.getProperty("productName"), productPage.getNameProduct());
    }

    @Test
    public void getSecondNameOfProduct() {
        homePage.enterTextIntoSearchBar(ConfigurationProperties.getProperty("searchSecondItem"));
        searchPage = homePage.clickSearch();
        productPage = searchPage.getProductByName(ConfigurationProperties.getProperty("productSecondName"));
        assertEquals(ConfigurationProperties.getProperty("productSecondName"), productPage.getNameProduct());
    }
}
