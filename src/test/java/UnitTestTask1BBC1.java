import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;
import static org.testng.Assert.*;


public class UnitTestTask1BBC1 {
    private WebDriver driver;

    @BeforeTest
    public void profileSetUp(){
        chromedriver().setup();
    }

    @BeforeMethod
    public void setUpTest() {
        driver = new ChromeDriver();  // створюється новий об'єкт драйвера
        driver.manage().window().maximize();   // відкривається нове вікно
        driver.get("https://bbc.com");        // у вікні запустаємо сторінку HOME_URL
    }
    @Test
    public void checkHeadlineTitle(){
        driver.findElement(By.xpath("//nav[@class='orbit-header-links international']//a[@href='https://www.bbc.com/news']")).click();
        WebElement drv = driver.findElement(By.xpath("//div[@data-entityid='container-top-stories#1']//h3"));
        assertEquals(drv.getAttribute("innerText"), "Uncertainty surrounds fate of Mariupol fighters");
    }
    @Test
    public void checkSecondTitle(){
        driver.findElement(By.xpath("//nav[@class='orbit-header-links international']//a[@href='https://www.bbc.com/news']")).click();
        WebElement drv = driver.findElement(By.xpath("//div[@data-entityid='container-top-stories#2']//h3"));
        assertEquals(drv.getAttribute("innerText"), "Mariupol: 80 days that left a flourishing city in ruins");
    }
    @Test
    public void checkThirdFourthFifthTitles(){
        driver.findElement(By.xpath("//nav[@class='orbit-header-links international']//a[@href='https://www.bbc.com/news']")).click();
        WebElement drv3 = driver.findElement(By.xpath("//div[@data-entityid='container-top-stories#3']//h3"));
        assertEquals(drv3.getAttribute("innerText"), "Trump-backed TV doctor in cliffhanger vote count");
        WebElement drv4 = driver.findElement(By.xpath("//div[@data-entityid='container-top-stories#4']//h3"));
        assertEquals(drv4.getAttribute("innerText"), "China plane crash likely intentional - US reports");
        WebElement drv5 = driver.findElement(By.xpath("//div[@data-entityid='container-top-stories#5']//h3"));
        assertEquals(drv5.getAttribute("innerText"), "Climate change swells odds of record India heatwave");
        WebElement drv6 = driver.findElement(By.xpath("//div[@data-entityid='container-top-stories#6']//h3"));
        assertEquals(drv6.getAttribute("innerText"), "Inside a secret school for Afghan girls");
    }
    @Test
    public void storeHeadlineTextCategory(){
        driver.findElement(By.xpath("//nav[@class='orbit-header-links international']//a[@href='https://www.bbc.com/news']")).click();
        driver.findElement(By.xpath("//div[@data-entityid='container-top-stories#1']")).click();
        WebElement categoryDrv = driver.findElement(By.xpath("//ul[@class='gs-o-list-ui--top-no-border nw-c-nav__wide-sections']//a[@class='nw-o-link']/span[@aria-hidden='true']"));
        String category =  categoryDrv.getAttribute("innerText");
        assertEquals(category, "World");
        WebElement subcategoryDrv = driver.findElement(By.xpath("//ul[@class='gs-o-list-ui--top-no-border nw-c-nav__secondary-sections']//a[@class='nw-o-link']//span[@aria-hidden='true']"));
        String subcategory = subcategoryDrv.getAttribute("innerText");
        assertEquals(subcategory, "Europe");

        driver.findElement(By.xpath("//a[@id='orbit-search-button']")).click();
        driver.findElement(By.xpath("//input[@id='search-input']")).sendKeys(category + " " + subcategory);
        driver.findElement(By.xpath("//button[@data-testid='test-search-submit']")).click();
        WebElement drv = driver.findElement(By.xpath("//ul/li[1]//p/span"));
        assertEquals(drv.getAttribute("innerText"), "World Cup 2022: European Qualifying Draw");
    }


    @AfterMethod
        public void tearDown() {
        driver.quit();    // закривається вікно
    }
}
