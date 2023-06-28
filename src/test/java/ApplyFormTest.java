
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApplyFormTest {
    private static WebDriver driver;
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
    @AfterAll
    static void teardown(){
        driver.quit();
    }
    @Test
     void shouldTestApplyForm() {
        driver.get("http://localhost:9999");
        WebElement form = driver.findElement(By.cssSelector("form"));

        form.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Ольга Власова");
        form.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79276102655");
        form.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        form.findElement(By.cssSelector(".button")).submit();

         WebElement text = driver.findElement(By.className("Success_successBlock__2L3Cw"));
        assertEquals("  Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text.getText());
        }

}
