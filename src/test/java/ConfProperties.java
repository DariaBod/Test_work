import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Класс для получения данных из файла
 *
 * @author Бодякова Дарья
 */
public class ConfProperties {

    /**
     * Входящий поток данных
     */
    protected static FileInputStream fileInputStream;

    /**
     * Настройки
     */
    protected static Properties PROPERTIES;

    static {
        try {
            fileInputStream = new FileInputStream("src/test/resources/conf.properties");
            PROPERTIES = new Properties();
            PROPERTIES.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null)
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    /**
     * метод для возврата строки со значением из файла с настройками
     */
    public static String getProperty(String key) {
        return PROPERTIES.getProperty(key);
    }
}