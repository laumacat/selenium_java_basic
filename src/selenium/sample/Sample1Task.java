package selenium.sample;


import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

public class Sample1Task {
    static String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;

    @Test
    public void goToHomepage() throws Exception {
//        TODO:
//         define driver
        System.setProperty("webdriver.chrome.driver","C:\\Lauma\\Test Automation Accenture\\My Java Bootcamp\\selenium_java_basic\\lib\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
//         go to https://kristinek.github.io/site/index2.html
        driver.get("https://kristinek.github.io/site/index2.html");
//         get title of page
        System.out.println("Title: " + driver.getTitle());
//         get URL of current page
        System.out.println("URL: " + driver.getCurrentUrl());
//         To observe the result, because executes too fast to see
        Thread.sleep(5000);
//         close browser
        driver.quit();
    }
}
