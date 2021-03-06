import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

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
    void sholdTestV1() {
        driver.get("http://localhost:9999/");
        driver.findElement(cssSelector("[type='text']")).sendKeys("Артем Чернышов");
        driver.findElement(cssSelector("[type='tel']")).sendKeys("+75555555555");
        driver.findElement(cssSelector("[class='checkbox__box']")).click();
        driver.findElement(cssSelector("button")).click();
        String actualMessage = driver.findElement(cssSelector("[data-test-id]")).getText().strip();
        String expectedMessage = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        Assertions.assertEquals(expectedMessage, actualMessage, "Фактическое сообщение не соответствет ожмдаемому");

    }

}