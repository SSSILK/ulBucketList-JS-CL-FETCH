import static org.junit.Assert.assertEquals;

import java.io.File;
import java.time.Duration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumTest {

    private WebDriver webDriver;

    @Before
    public void setUp() {
        
        System.setProperty("webdriver.edge.driver", "driver/msedgedriver");//linux_64

        // Get file
        File file = new File("src/main/cat-facts.html");
        String path = "file://" + file.getAbsolutePath();

        
        EdgeOptions options = new EdgeOptions();
        options.addArguments("headless");
        webDriver = new EdgeDriver(options);

        // Open the HTML file
        webDriver.get(path);

    }
    @Test
    public void testCatFact() {
        WebElement content = webDriver.findElement(By.id("content"));

        // to start, assert that the content is "placeholder"
        assertEquals("placeholder", content.getText());

        WebElement buttonElement = webDriver.findElement(By.id("button"));
        // click the button:
        buttonElement.click();        

        // Wait for the text content to change
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(content, "placeholder")));



        

    }

    @Test
    public void initialState() {
        WebElement content = webDriver.findElement(By.id("content"));

        // to start, assert that the content is "placeholder"
        assertEquals("placeholder", content.getText());
    }

    @After
    public void tearDown() {
        // Close the browser
        webDriver.quit();
    }
}
