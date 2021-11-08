import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.openqa.selenium.By.cssSelector;

public class CardRequestTest {
    public WebDriver driver;

    @BeforeAll
    static void setupAll() {
        WebDriverManager.chromedriver().setup();

    }

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }

    @Test
    void sholdTestSuccess() {
        driver.get("http://localhost:9999/");
        driver.findElement(cssSelector("[type='text']")).sendKeys("Артем Чернышов");
        driver.findElement(cssSelector("[type='tel']")).sendKeys("+75555555555");
        driver.findElement(cssSelector("[class='checkbox__box']")).click();
        driver.findElement(cssSelector("button")).click();
        String actualMessage = driver.findElement(cssSelector("[data-test-id]")).getText().strip();
        String expectedMessage = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        Assertions.assertEquals(expectedMessage, actualMessage, "Фактическое сообщение не соответствет ожмдаемому");

    }

    @Test
    void shouldTestNameFieldEmpty() {
        driver.get("http://localhost:9999/");
        driver.findElement(cssSelector("[type='text']")).sendKeys("");
        driver.findElement(cssSelector("[type='tel']")).sendKeys("+75555555555");
        driver.findElement(cssSelector("[class='checkbox__box']")).click();
        driver.findElement(cssSelector("button")).click();
        String actualMessage = driver.findElement(cssSelector("[class='input__sub']")).getText().strip();
        String expectedMessage = "Поле обязательно для заполнения";
        Assertions.assertEquals(expectedMessage, actualMessage, "Фактическое сообщение не соответствет ожмдаемому");
    }

    @Test
    void shouldTestPhoneFieldEmpty() {
        driver.get("http://localhost:9999/");
        driver.findElement(cssSelector("[type='text']")).sendKeys("Артем Чернышов");
        driver.findElement(cssSelector("[type='tel']")).sendKeys("");
        driver.findElement(cssSelector("[class='checkbox__box']")).click();
        driver.findElement(cssSelector("button")).click();
        List<WebElement> elements = driver.findElements(cssSelector("[class='input__sub']"));
        String actualMessage = elements.get(1).getText().strip();
        String expectedMessage = "Поле обязательно для заполнения";
        Assertions.assertEquals(expectedMessage, actualMessage, "Фактическое сообщение не соответствет ожмдаемому");
    }

    @Test
    void shouldTestNameNotCorrect() {
        driver.get("http://localhost:9999/");
        driver.findElement(cssSelector("[type='text']")).sendKeys("Artem Chernyshov");
        driver.findElement(cssSelector("[type='tel']")).sendKeys("+75555555555");
        driver.findElement(cssSelector("[class='checkbox__box']")).click();
        driver.findElement(cssSelector("button")).click();
        String actualMessage = driver.findElement(cssSelector("[class='input__sub']")).getText().strip();
        String expectedMessage = "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";
        Assertions.assertEquals(expectedMessage, actualMessage, "Фактическое сообщение не соответствет ожмдаемому");
    }

    @Test
    void shouldTestPhoneNotCorrect() {
        driver.get("http://localhost:9999/");
        driver.findElement(cssSelector("[type='text']")).sendKeys("Артем Чернышов");
        driver.findElement(cssSelector("[type='tel']")).sendKeys("+7555555555");
        driver.findElement(cssSelector("[class='checkbox__box']")).click();
        driver.findElement(cssSelector("button")).click();
        List<WebElement> elements = driver.findElements(cssSelector("[class='input__sub']"));
        String actualMessage = elements.get(1).getText().strip();
        String expectedMessage = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        Assertions.assertEquals(expectedMessage, actualMessage, "Фактическое сообщение не соответствет ожмдаемому");
    }

    @Test
    void shouldTestCheckboxEmpty() {
        driver.get("http://localhost:9999/");
        driver.findElement(cssSelector("[type='text']")).sendKeys("Артем Чернышов");
        driver.findElement(cssSelector("[type='tel']")).sendKeys("+75555555555");
        //driver.findElement(cssSelector("[class='checkbox__box']")).click();
        driver.findElement(cssSelector("button")).click();
        assertTrue(driver.findElement(By.cssSelector(".input_invalid>.checkbox__box")).isDisplayed());

    }

}