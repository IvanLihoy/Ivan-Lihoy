import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

/**
 * Created by Администратор on 12.07.2016.
 */
public class DropBox implements Variables {
    private static WebDriver driver;
    private static boolean checkElementPresence(String selectorType, String selector) {
        try {
            if(selectorType == "css"){
                driver.findElement(By.cssSelector(selector));
                return true;
            }
            else {
                driver.findElement(By.xpath(selector));
                return true;
            }
        }
        catch (NoSuchElementException e) {
            return false;
        }
    }
    public static void sleep(int time) {
        try {
            Thread.sleep(time * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @BeforeMethod
    public static void setup() {
        String String_key = "Webdriver.chrome.driver";
        String String_path = ("bin/chromedriver.exe");
        System.setProperty(String_key, String_path);
        driver = new ChromeDriver();
    }

    @AfterMethod
    public static void testdown() {
        driver.quit();
    }

    @Test
    public static void Dropboxtestlogin() {
        driver.get(Login_page);
        sleep(3);
        WebElement Login_fild = driver.findElement(By.cssSelector(Dropbox_Login_fild));
        WebElement Login_password = driver.findElement(By.cssSelector(Dropbox_Login_password));
        Login_fild.sendKeys(Login);
        Login_password.sendKeys(Password);
        WebElement Login_button = driver.findElement(By.cssSelector(Dropbox_Login_button));
        Login_button.click();
        sleep(3);
        boolean Is_logged_in = checkElementPresence("css", Boolean_Is_logged);
        Assert.assertTrue(Is_logged_in);
        sleep(6);
    }

    @Test
    public static void viewfile() {
        Dropboxtestlogin();
        driver.findElement(By.cssSelector(File)).click();
        sleep(4);
        Assert.assertTrue(checkElementPresence("css", ".share-button"));
    }
}




