package selenium.sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//imports to use LoadingColorPage (Lauma)
import org.openqa.selenium.support.PageFactory;
import selenium.pages.LoadingColorPage;


import java.io.File;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class Sample9Task {
    WebDriver driver;
    static LoadingColorPage loadingColor;


    @Before
    public void openPage() {
        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        driver = new ChromeDriver();
        driver.get("https://kristinek.github.io/site/examples/loading_color");



    }

    @After
    public void closeBrowser() {
        driver.close();
    }

    @Test
    public void loadGreenSleep() throws Exception {

        // to use LoadingColorPage in this test (Lauma)
        loadingColor = PageFactory.initElements(driver, LoadingColorPage.class);
//
//
//         * 1) click on start loading green button
//        driver.findElement(By.id("start_green")).click();
        loadingColor.clickStartGreen();

//         * 2) check that button does not appear,
//         * but loading text is seen instead   "Loading green..."
//        assertFalse(driver.findElement(By.id("start_green")).isDisplayed());
        assertFalse(loadingColor.displayStartGreen());
        assertEquals("Loading green...", loadingColor.textLoadingGreen());

        Thread.sleep(6000);

//         * 3) check that both, button
//         * and loading text, is not seen,
//         * success is seen instead "Green Loaded"
        assertFalse(loadingColor.displayStartGreen());
        assertFalse(loadingColor.displayLoadingGreen());
        assertEquals("", loadingColor.textLoadingGreen());
        assertEquals("Green Loaded", loadingColor.textFinishGreen());

    }

    @Test
    public void loadGreenImplicit() throws Exception {
//         TODO:
//         * 1) click on start loading green button

        driver.findElement(By.id("start_green")).click();

//         * 2) check that button does not appear,
//         * but loading text is seen instead   "Loading green..."
        assertFalse(driver.findElement(By.id("start_green")).isDisplayed());
        assertEquals("Loading green...", driver.findElement(By.id("loading_green")).getText());


//         * 3) check that both button
//         * and loading text is not seen,
//         * success is seen instead "Green Loaded"
        driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
        //next line needed to find that expected element has loaded (Lauma)
        driver.findElement(By.id("finish_green"));

        assertFalse(driver.findElement(By.id("start_green")).isDisplayed());
        assertFalse(driver.findElement(By.id("loading_green")).isDisplayed());
        assertEquals("", driver.findElement(By.id("loading_green")).getText());
        assertEquals("Green Loaded", driver.findElement(By.id("finish_green")).getText());

    }

    @Test
    public void loadGreenExplicitWait() throws Exception {
//         TODO:

        WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver, 10).ignoring(StaleElementReferenceException.class);

//         * 1) click on start loading green button
        driver.findElement(By.id("start_green")).click();
//         * 2) check that button does not appear,
//         * but loading text is seen instead   "Loading green..."

        assertFalse(driver.findElement(By.id("start_green")).isDisplayed());
        assertEquals("Loading green...", driver.findElement(By.id("loading_green")).getText());

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("finish_green")));

//         * 3) check that both button
//         * and loading text is not seen,
//         * success is seen instead "Green Loaded"
        assertFalse(driver.findElement(By.id("start_green")).isDisplayed());
        assertFalse(driver.findElement(By.id("loading_green")).isDisplayed());
        assertEquals("", driver.findElement(By.id("loading_green")).getText());
        assertEquals("Green Loaded", driver.findElement(By.id("finish_green")).getText());

    }

    @Test
    public void loadGreenAndBlueBonus() throws Exception {

//       TODO:
        /* 0) wait until button to load green and blue appears
         * 1) click on start loading green and blue button
         * 2) check that button does not appear, but loading text is seen instead for green
         * 3) check that button does not appear, but loading text is seen instead for green and blue
         * 4) check that button and loading green does not appear,
         * 		but loading text is seen instead for blue and success for green is seen
         * 5) check that both button and loading text is not seen, success is seen instead
         */


//        0) wait until button to load green and blue appears
        driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
        //next line needed to find that expected element has loaded
        driver.findElement(By.id("start_green_and_blue"));

//        1) click on start loading green and blue button
        driver.findElement(By.id("start_green_and_blue")).click();

//        2) check that button does not appear, but loading text is seen instead for green
        assertFalse(driver.findElement(By.id("start_green_and_blue")).isDisplayed());
        assertTrue(driver.findElement(By.id("loading_green_without_blue")).isDisplayed());
        assertEquals("Loading green...",driver.findElement(By.id("loading_green_without_blue")).getText());

//        3) check that button does not appear, but loading text is seen instead for green and blue
        assertFalse(driver.findElement(By.id("start_green_and_blue")).isDisplayed());

        assertTrue(driver.findElement(By.id("loading_green_without_blue")).isDisplayed());

        WebDriverWait wait1 = (WebDriverWait) new WebDriverWait(driver, 5).ignoring(StaleElementReferenceException.class);
        wait1.until(ExpectedConditions.presenceOfElementLocated(By.id("loading_green_with_blue")));

        assertTrue(driver.findElement(By.id("loading_green_with_blue")).isDisplayed());
        assertEquals("Loading blue...",driver.findElement(By.id("loading_green_with_blue")).getText());


//        4) check that button and loading green does not appear,
//
        WebDriverWait wait2 = (WebDriverWait) new WebDriverWait(driver, 5).ignoring(StaleElementReferenceException.class);
        wait2.until(ExpectedConditions.presenceOfElementLocated(By.id("loading_blue_without_green")));

        assertFalse(driver.findElement(By.id("start_green_and_blue")).isDisplayed());
        assertFalse(driver.findElement(By.id("loading_green_without_blue")).isDisplayed());

//        * but loading text is seen instead for blue and success for green is seen
        assertTrue(driver.findElement(By.id("loading_blue_without_green")).isDisplayed());
        assertEquals("Loading blue...",driver.findElement(By.id("loading_green_with_blue")).getText());
        assertEquals("Green finished waiting for blue",driver.findElement(By.id("loading_blue_without_green")).getText());


//        5) check that both button and loading text is not seen, success is seen instead
        WebDriverWait wait3 = (WebDriverWait) new WebDriverWait(driver, 5).ignoring(StaleElementReferenceException.class);
        wait3.until(ExpectedConditions.presenceOfElementLocated(By.id("finish_green_and_blue")));

        assertFalse(driver.findElement(By.id("start_green_and_blue")).isDisplayed());
        assertFalse(driver.findElement(By.id("loading_green_without_blue")).isDisplayed());
        assertFalse(driver.findElement(By.id("loading_blue_without_green")).isDisplayed());

        assertTrue(driver.findElement(By.id("finish_green_and_blue")).isDisplayed());
        assertEquals("Green and Blue Loaded",driver.findElement(By.id("finish_green_and_blue")).getText());



    }

}