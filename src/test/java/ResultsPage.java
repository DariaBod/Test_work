import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.ByteArrayInputStream;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс, отражающий страницу с результатами поиска
 *
 * @author Бодякова Дарья
 */
public class ResultsPage {

    /**
     * Экземпляр класса веб драйвер
     */
    public static WebDriver driver;

    /**
     * конструктор класса, занимающийся инициализацией полей класса
     * @param driver
     */
    public ResultsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    /**
     * определение локаторов цен на товары
     */
    @FindBy(xpath = "//div[contains(@class, '_3NaXx _33ZFz _2m5MZ')]")
    private static List<WebElement> pricesOfProducts;

    /**
     * определение локаторов названий товаров
     */
    @FindBy(xpath = "//a[contains(@class, '_2f75n _24Q6d cia-cs')]")
    private static List<WebElement> namesOfProducts;

    /**
     * определение локатора кнопки для сортировки по цене
     */
    @FindBy(xpath = "//button[contains(@class, '_23p69 _3D8AA')]")
    private static WebElement sortByPriceBtn;

    /**
     * Создание списка найденных товаров с ценниками, вывод его
     * в консоль и в отчёт
     */
    @Step("4. Создание списка найденных товаров с ценниками")
    public static void printResults() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("(//a[contains(@class, '_2f75n _24Q6d cia-cs')])[1]")));
        Allure.addAttachment("Скриншот найденных товаров", new ByteArrayInputStream(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES)));
        ((JavascriptExecutor)driver).executeScript("scroll(0,800)");
        ArrayList<String> listOfProductsAndPrices = new ArrayList<>();
        for (int i = 0; i < namesOfProducts.size(); i++)
            listOfProductsAndPrices.add(namesOfProducts.get(i).getText()+" - "+pricesOfProducts.get(i).getText());
        System.out.println("Список найденных товаров с ценниками:");
        for (int i = 0; i < listOfProductsAndPrices.size(); i++)
            System.out.println(listOfProductsAndPrices.get(i));
        Allure.addAttachment("Список найденных товаров", String.valueOf(listOfProductsAndPrices));
    }

    /**
     * Проверка присутствия искомого продукта в полученном списке
     */
    @Step("5. Проверка присутствия искомого продукта в полученном списке")
    public static void verificationOfResults() {
        String productName=ConfProperties.getProperty("productname");
        Assert.assertTrue(driver.getPageSource().contains(productName),"В результатах поиска нет требуемого продукта");
        Allure.addAttachment("Результат проверки присутствия продукта в списке", "Искомый продукт присутствует в результатах поиска");
        System.out.println("Искомый продукт присутствует в результатах поиска");
    }

    /**
     * Сортировка списка товаров по цене
     */
    @Step("6. Применение сортировки по цене к списку товаров")
    public static void sortByPrice() {
        ((JavascriptExecutor)driver).executeScript("scroll(0,-300)");
        sortByPriceBtn.click();
        ((JavascriptExecutor)driver).executeScript("scroll(0,200)");
    }

    /**
     * Нажатие на первый результат в выдаче после сортировки по цене
     */
    @Step("7. Нажатие на первый результат в выдаче после сортировки по цене")
    public static void clickOnFirstResult() {
        try {
            Thread.sleep(3*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Allure.addAttachment("Скриншот товаров, отсортированных по цене", new ByteArrayInputStream(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES)));
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("(//a[contains(@class, '_2f75n _24Q6d cia-cs')])[1]")));
    }
}
