package initialAddition;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class SetUp {

    public static String OS = null;
    public static WebDriver driver = null;
    //geckodriver


    @BeforeTest
    public static void callSetUp(){
        setUpDriver("mac", "https://www.facebook.com/", "chrome");
    }
    public static WebDriver setUpDriver(String plateform, String url, String browser) {
        if(plateform.equalsIgnoreCase("mac")){
            if(browser.equalsIgnoreCase("chrome"))
            {  System.setProperty("webdriver.chrome.driver", "src/main/resources/Drivers/chromedriver 2");
            driver = new ChromeDriver();}
            else if(browser.equalsIgnoreCase("mozilla")) {
                System.setProperty("webdriver.gecko.driver", "src/main/resources/Drivers/geckodriver");
                driver = new FirefoxDriver();
            }
        }else if(plateform.equalsIgnoreCase("windows")){
            if(browser.equalsIgnoreCase("chrome")) {
                System.setProperty("webdriver.chrome.driver", "src/main/resources/Drivers/chromedriver.exe");
                driver = new ChromeDriver();
            }
            else if(browser.equalsIgnoreCase("mozilla")) {
                System.setProperty("webdriver.gecko.driver", "src/main/resources/Drivers/geckodriver.exe");
                driver = new FirefoxDriver();
            }
        }


        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);//waiting for the dom to be loaded
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);//wait if we have any internet connexion
        driver.manage().window().maximize();
        driver.get(url);
        return driver;
    }

    @Test
    public static void facebook_login() {
        driver.findElement(By.xpath("//*[@id='email']")).sendKeys("girlof16@live.fr");
        driver.findElement(By.xpath("//*[@id='pass']")).sendKeys("");
        driver.findElement(By.xpath("//*[@value='Log In']")).click();
        // driver.findElement(By.cssSelector())


    }
    @Test
    public static void messenger_button(){
        driver.findElement(By.linkText("Messenger")).click();
    }
    @Test
    public static void About_button(){
        driver.findElement(By.linkText("About")).click();
    }
    @Test
    public static void SignUp(){
        driver.findElement(By.xpath("//*[@id='u_0_c']")).sendKeys("Sosso");
        driver.findElement(By.xpath("//*[@id='u_0_e']")).sendKeys("Sosso");
        driver.findElement(By.xpath("//*[@id='u_0_h']")).sendKeys("347");
        driver.findElement(By.xpath("//*[@id='u_0_o']")).sendKeys("123");
        Select month = new Select(driver.findElement(By.xpath("//*[@id='month']")));
        month.selectByValue("7");
        Select day = new Select(driver.findElement(By.xpath("//*[@id='day']")));
        day.selectByValue("10");
        Select year = new Select(driver.findElement(By.xpath("//*[@id='year']")));
        year.selectByValue("1995");
        WebElement sex = driver.findElement(By.xpath("//*[@name='sex']"));
        sex.click();
        driver.findElement(By.xpath("//*[@id='u_0_u']")).click();

    }


    @AfterTest
    public void quit_Driver(){
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.quit();
    }

}
