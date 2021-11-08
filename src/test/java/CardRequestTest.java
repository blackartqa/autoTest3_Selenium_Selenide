import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CardRequestTest {
    private WebDriver driver;

    @BeforeAll
    static void setupAll(){
        System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
    }

    @BeforeEach
    void setUp(){
        driver = new ChromeDriver();
    }

    @AfterEach
    void tearDown(){
        driver.quit();
        driver = null;
    }

    @Test
    void sholdTestV1(){
        driver.get("http://localhost:9999/");
        driver.findElement().sendKeys("Артем");
        driver.findElement().sendKeys("+75555555555");
        driver.findElement().click();
        driver.findElement().click();
        String actualMessage = driver.findElement().getText();
        String expectedMessage = "Ваша заявка успешно отправлена";
        Assertions.assertEquals(expectedMessage, actualMessage, "Фактическое сообщение не соответствет ожмдаемому");

    }

}
