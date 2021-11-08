import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.WebDriverException;


import java.util.List;

public class CardRequestTest {
    public WebDriver driver = new ChromeDriver();

    @BeforeAll
    static void setupAll() {
        //WebDriverManager.chromedriver().setup();
        System.setProperty("webdriver.chrome.driver", "D:\\blackart\\Documents\\GoogleDriveQA\\Netology\\HW\\04.Auto\\2.1\\driver\\chromedriver.exe");
    }

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
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
//        List<WebElement> textFields = driver.findElements(By.className("input__control"));
//        textFields.get(0).sendKeys("Артем Чернышов");
//        textFields.get(1).sendKeys("+75555555555");
        driver.findElement(By.cssSelector("[type='text']")).sendKeys("Артем Чернышов");
        driver.findElement(By.cssSelector("[type='tel']")).sendKeys("+75555555555");
        driver.findElement(By.cssSelector("[class='checkbox__box']")).click();
        driver.findElement(By.cssSelector("button")).click();
        String actualMessage = driver.findElement(By.cssSelector("[data-test-id]")).getText().strip();
        String expectedMessage = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        Assertions.assertEquals(expectedMessage, actualMessage, "Фактическое сообщение не соответствет ожмдаемому");

    }

}