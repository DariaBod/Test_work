import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.ByteArrayInputStream;

/**
 * Класс, отражающий страницу поиска
 *
 * @author Бодякова Дарья
 */
public class SearchPage {

    /**
     * Экземпляр класса веб драйвер
     */
    public static WebDriver driver;

    /**
     * конструктор класса, занимающийся инициализацией полей класса
     * @param driver
     */
    public SearchPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    /**
     * определение локатора поля ввода товара
     */
    @FindBy(xpath = "//*[@id=\"header-search\"]")
    private static WebElement searchField;

    /**
     * определение локатора кнопки поиска
     */
    @FindBy(xpath = "/html/body/div[4]/header/noindex/div/div/div[2]/div[2]/div/div/form/div[1]/button/span")
    private static WebElement findButton;

    /**
     * метод для ввода названия товара
     * @param productName
     */
    @Step("2. Название товара(модель телефона) {0} вводится в поисковую строку")
    public static void inputNameOfProduct(String productName) {
        searchField.sendKeys(productName);
        Allure.addAttachment("Скриншот строки поиска с данными", new ByteArrayInputStream(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES)));
    }


    /**
     * метод для осуществления нажатия кнопки поиска
     */
    @Step("3. Нажатие кнопки найти")
    public static void clickFindBtn() {
        findButton.click();
    }
}
