import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class testAutomation {

    public WebDriver driver;

    @Before
    public void browserSetUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void assertSubmission() throws InterruptedException {

        driver.get("https://www.digitalunite.com/practice-webform-learners");

        Thread.sleep(3000);
        driver.findElement(By.id("onetrust-reject-all-handler")).click();

        Thread.sleep(3000);
        //Name
        driver.findElement(By.id("edit-name")).sendKeys("Toufa Saha");

        //Phone number
        driver.findElement(By.id("edit-number")).sendKeys("+8801772379040");

        //Select Age
        driver.findElement(By.xpath("//*[@id='edit-agnew']/label[1]")).click();

        //Select current date
        DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        Date date = new Date();
        String currentDate = dateFormat.format(date);
        driver.findElement(By.id("edit-date")).sendKeys(currentDate, Keys.ENTER);

        //Input gmail
        driver.findElement(By.name("email")).sendKeys("toufa@gmail.com");

        //Input text
        driver.findElement(By.cssSelector("textarea[name = 'tell_us_a_bit_about_yourself_']")).sendKeys("I am a SQA Engineer");

        //Scroll down
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,300)", "");
        Thread.sleep(3000);

        //Upload File
        WebElement uploadElement = driver.findElement(By.id("edit-uploadocument-upload"));
        uploadElement.sendKeys("C:\\Users\\Tofa\\Pictures\\Interview\\Capture.PNG");

        js.executeScript("window.scrollBy(300,600)", "");
        Thread.sleep(3000);

        //Check box
        WebElement webElement = driver.findElement(By.id("edit-age"));
        webElement.click();

        //Submit
        driver.findElement(By.id("edit-submit")).click();

        //Assert text
        String textActual = driver.findElement(By.cssSelector("#block-pagetitle-2 > h1")).getText();
        String textExpected = "Thank you for your submission!";
        Assert.assertEquals(textExpected, textActual);
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }
}
