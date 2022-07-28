import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

/**
 * Класс, отражающий страницу выбранного продукта
 *
 * @author Бодякова Дарья
 */
public class ProductPage {

    /**
     * Экземпляр класса веб драйвер
     */
    public static WebDriver driver;

    /**
     * конструктор класса, занимающийся инициализацией полей класса
     * @param driver
     */
    public ProductPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    /**
     * определение локатора цены
     */
    @FindBy(xpath = "//div[contains(@class, '_3NaXx _3kWlK')]")
    private static WebElement price;

    /**
     * определение локатора продавца
     */
    @FindBy(xpath = "//a[contains(@class, '_1yBO3 BgRce _2IaHT cia-cs')]")
    private static WebElement customerSiteAndName;

    /**
     * Переключение веб драйвера на новую вкладку браузера
     */
    public static void switchToNewTab() {
        String oldTab = driver.getWindowHandle();
        ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
        newTab.remove(oldTab);
        driver.switchTo().window(newTab.get(0));
    }

    /**
     * Вывод названия магазина в консоль и в отчет
     */
    @Step("8. Вывод названия магазина в консоль")
    public static void customerName() {
        Allure.addAttachment("Карточка первого товара в списке", new ByteArrayInputStream(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES)));
        Allure.addAttachment("Магазин", "Магазин: "+customerSiteAndName.getText());
        System.out.println("Магазин: "+customerSiteAndName.getText());
    }

    /**
     * Вывод цены товара в консоль и в отчет
     */
    @Step("8. Вывод цены товара в консоль")
    public static void productPrice() {
        Allure.addAttachment("Цена", "Цена: "+price.getText());
        System.out.println("Цена: "+price.getText());
    }
}
