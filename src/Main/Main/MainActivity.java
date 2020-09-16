package Main;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.File;
import java.util.ArrayList;


public class MainActivity {
    private static WebDriver driver;
    private static WebElement webElement;
    public static void main(String[] args) {
        setup();
        boolean v = mainlogo();
        if (v) checkicons();
        checkicons();
        checkpanels();
        hearAboutUs();
        jumpToTop();
        shareedIcons();
        openWhatapp();
    }

    private static void setup() {
        String path = new File("chromedriver.exe").getAbsolutePath();
        System.setProperty("webdriver.chrome.driver", path);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://automation.herolo.co.il/");
        threadSleep();
    }

    private static boolean mainlogo() {
        webElement = driver.findElement(By.id("logom"));
        markElements(webElement);
        try {
            if (webElement.isDisplayed()) {
                threadSleep();
                return true;
            }
        } catch (NotFoundException e) {
            e.printStackTrace();
        }

        return false;
    }

    private static void checkicons() {
        for (int i=1; i<=3; i++) {
            try {
                webElement = driver.findElement(By.xpath("//*[@id=\"gatsby-focus-wrapper\"]/div/header/div/img["+i+"]"));
                markElements(webElement);
                threadSleep();
            }
            catch (NotFoundException e)
            {
                e.printStackTrace();
            }
        }
    }
    private static void checkpanels() {
        try {
            JavascriptExecutor jse = (JavascriptExecutor)driver;
            jse.executeScript("window.scrollBy(0,1000)");
            threadSleep();
            webElement = driver.findElement(By.xpath("//*[text()[contains(.,'מיקור חוץ לפיתוח Frontend ובודקי איכות')]]"));
            markElements(webElement);
            threadSleep();
            webElement = driver.findElement(By.xpath("//*[text()[contains(.,'פרויקט Full Stack מקצה לקצה')]]"));
            markElements(webElement);
            threadSleep();
            webElement = driver.findElement(By.xpath("//*[text()[contains(.,'ייעוץ ושדרוג ל–JavaScript')]]"));
            markElements(webElement);
            threadSleep();
            if(webElement.isDisplayed())
            {
                driver.findElement(By.xpath("//*[text()[contains(.,'אנחנו יכולים לשדרג לכם את האתר ו/או האפליקציה שלכם, לדאוג שתהיו מעודכנים עם הטכנולוגיות החדשות ביותר ולבחון את בסיס הקוד יחד איתכם.')]]"));
                Thread.sleep(2000);
            }
        } catch (NotFoundException | InterruptedException e) {
            e.printStackTrace();
        }
        threadSleep();
    }
    private  static  void jumpToTop()
    {
        webElement = driver.findElement(By.xpath("//*[@id=\"gatsby-focus-wrapper\"]/div/a[1]"));
        markElements(webElement);
        webElement.click();
        threadSleep();
    }
    private static void hearAboutUs()
    {
        try {
            webElement = driver.findElement(By.xpath("//*[text()[contains(.,'רוצים לשמוע עוד?')]]"));
            driver.findElement(By.xpath("//*[text()[contains(.,'דברו איתנו')]]")).click();
            threadSleep();
            if( driver.findElement(By.xpath("//*[text()[contains(.,'שדה שם הוא שדה חובה')]]")).isDisplayed())
            {
                findElementAndInsert("name","Andrey");
                markElements(webElement);
                findElementAndInsert("company","HEROLO");
                markElements(webElement);
                findElementAndInsert("email","abcd@walla.com");
                markElements(webElement);
                findElementAndInsert("telephone","0508117980");
                markElements(webElement);

            }

        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        threadSleep();
    }
    private static void findElementAndInsert(String xpath, String val)
    {
        webElement = driver.findElement(By.id(xpath));
        webElement.click();
        webElement.sendKeys(val);
    }
    private static void shareedIcons()
    {
        ArrayList<String> tabs2 = new ArrayList<>(driver.getWindowHandles());
        for(int i=1; i<=4; i++) {
            findIconBySrc("//*[@id=\"section-contact\"]/section/div[2]/div/div[2]/div/a["+i+"]");
        }
        driver.switchTo().window(tabs2.get(0));
    }
    private static void findIconBySrc(String xpath)
    {
        webElement = driver.findElement(By.xpath(xpath));
        webElement.click();
    }
    private  static void threadSleep()
    {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private  static void openWhatapp() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        webElement = driver.findElement(By.xpath("//*[@id=\"modal-form\"]/div/div[1]/input"));
        webElement.sendKeys("aaa");
        threadSleep();
        driver.findElement(By.xpath("//*[@id=\"modal-form\"]/button")).click();
        markElements(webElement);
        threadSleep();
        for(int i=2; i<=3; i++)
        {
                webElement = driver.findElement(By.xpath("//*[@id=\"modal-form\"]/div/div[" + i + "]/input"));
                webElement.click();
                if(i!=3) {
                    webElement.sendKeys("aaa@walla.com");
                }
                else{
                    webElement.sendKeys("0508117980");
                }
            markElements(webElement);
            threadSleep();
        }
        driver.findElement(By.xpath("//*[@id=\"modal-form\"]/button")).click();
        }

    private  static void markElements(WebElement ele)
    {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", ele);
    }
}
