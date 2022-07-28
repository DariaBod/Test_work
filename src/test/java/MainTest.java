import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Класс, запускающий тестирование
 *
 * @author Бодякова Дарья
 */
public class MainTest {

    /**
     * Экземпляр класса поисковой страницы
     */
    public static SearchPage searchPage;

    /**
     * Экземпляр класса страницы с результатами поиска
     */
    public static ResultsPage resultsPage;

    /**
     * Экземпляр класса страницы продукта
     */
    public static ProductPage productPage;

    /**
     * Экземпляр класса веб драйвер
     */
    public static WebDriver driver;

    /**
     * Осуществление первоначальной настройки
     */
    @BeforeClass
    @Step("1. Открытие сайта Яндекс Маркет в окне браузера Google Chrome")
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
        //создание экземпляра драйвера
        driver = new ChromeDriver();
        System.out.println(driver);
        searchPage = new SearchPage(driver);
        resultsPage=new ResultsPage(driver);
        productPage=new ProductPage(driver);
        //окно разворачивается на полный экран
        driver.manage().window().maximize();
        //задержка на выполнение теста = 20 сек.
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        //получение ссылки на страницу входа из файла настроек
        driver.get(ConfProperties.getProperty("mainpage"));
    }

    /**
     * Тестирование
     */
    @Test
    public static void test() {
        SearchPage.inputNameOfProduct(ConfProperties.getProperty("productname"));
        SearchPage.clickFindBtn();
        ResultsPage.printResults();
        ResultsPage.verificationOfResults();
        ResultsPage.sortByPrice();
        ResultsPage.clickOnFirstResult();
        ProductPage.switchToNewTab();
        ProductPage.customerName();
        ProductPage.productPrice();
    }

    /**
     * Закрытие браузера после выполнения теста
     */
    @AfterClass
    @Step("9. Закрытие браузера")
    public static void tearDown() {
        driver.quit();
    }

}
