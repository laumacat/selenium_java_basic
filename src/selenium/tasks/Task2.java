package selenium.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.util.List;

import static org.junit.Assert.*;

public class Task2 {
    WebDriver driver;

    @Before
    public void openPage() {
        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        driver = new ChromeDriver();
        driver.get("https://kristinek.github.io/site/tasks/provide_feedback");
    }

    @After
    public void closeBrowser() {
        driver.close();
    }

    @Test
    public void initialFeedbackPage() throws Exception {
//         TODO:
//         check that all field are empty and no tick are clicked
        assertEquals("", driver.findElement(By.id("fb_name")).getAttribute("value"));
        assertEquals("", driver.findElement(By.id("fb_age")).getAttribute("value"));
        assertEquals("", driver.findElement(By.tagName("textarea")).getAttribute("value"));

        assertFalse(driver.findElements(By.cssSelector("#lang_check > .w3-check")).get(0).isSelected());
        assertFalse(driver.findElements(By.cssSelector("#lang_check > .w3-check")).get(1).isSelected());
        assertFalse(driver.findElements(By.cssSelector("#lang_check > .w3-check")).get(2).isSelected());
        assertFalse(driver.findElements(By.cssSelector("#lang_check > .w3-check")).get(3).isSelected());

//         "Don't know" is selected in "Genre"
        assertFalse(driver.findElements(By.cssSelector(".w3-radio")).get(0).isSelected());
        assertFalse(driver.findElements(By.cssSelector(".w3-radio")).get(1).isSelected());
        assertTrue(driver.findElements(By.cssSelector(".w3-radio")).get(2).isSelected());

//         "Choose your option" in "How do you like us?"
        WebElement dropdown = driver.findElement(By.id("like_us"));
        Select dropSelect = new Select(dropdown);

        List<WebElement> allSelect = dropSelect.getAllSelectedOptions();
        assertEquals(1, allSelect.size());
        assertEquals("Choose your option", allSelect.get(0).getText());

//         check that the button send is blue with white letters
        WebElement sendButton = driver.findElement(By.tagName("button"));
        assertEquals("rgba(33, 150, 243, 1)", sendButton.getCssValue("background-color"));
        assertEquals("rgb(255, 255, 255)", sendButton.getCssValue("text-decoration-color"));
    }

    @Test
    public void emptyFeedbackPage() throws Exception {
//         TODO:
//         click "Send" without entering any data
        driver.findElement(By.tagName("button")).click();

//         check fields are empty or null
        assertEquals("", driver.findElement(By.id("name")).getText());
        assertEquals("", driver.findElement(By.id("age")).getText());
        assertEquals("", driver.findElement(By.id("language")).getText());
        assertEquals("", driver.findElement(By.id("comment")).getText());

        assertEquals("null", driver.findElement(By.id("gender")).getText());
        assertEquals("null", driver.findElement(By.id("option")).getText());

//         check button colors
//         (green with white letter and red with white letters)
        WebElement yesButton = driver.findElement(By.className("w3-green"));
        WebElement noButton = driver.findElement(By.className("w3-red"));

        assertEquals("rgba(76, 175, 80, 1)", yesButton.getCssValue("background-color"));
        assertEquals("rgba(244, 67, 54, 1)", noButton.getCssValue("background-color"));

        assertEquals("rgb(255, 255, 255)", yesButton.getCssValue("text-decoration-color"));
        assertEquals("rgb(255, 255, 255)", yesButton.getCssValue("text-decoration-color"));

    }

    @Test
    public void notEmptyFeedbackPage() throws Exception {
//         TODO:

        String name = "Liene";
        String age = "42";
        String option = "Good";
        String comment = "My comment";
        String language = "English";
        String gender = "female";

//         fill the whole form,
        driver.findElement(By.id("fb_name")).sendKeys(name);
        driver.findElement(By.id("fb_age")).sendKeys(age);
        driver.findElement(By.tagName("textarea")).sendKeys(comment);

        driver.findElement(By.cssSelector("input[value='" + language + "']")).click();

        driver.findElement(By.cssSelector("input[value='" + gender + "']")).click();

        WebElement dropdown = driver.findElement(By.id("like_us"));
        Select dropSelect = new Select(dropdown);
        dropSelect.selectByVisibleText(option);

//        click "Send"
        driver.findElement(By.tagName("button")).click();

//         check fields are filled correctly


        assertEquals(name, driver.findElement(By.id("name")).getText());
        assertEquals(age, driver.findElement(By.id("age")).getText());
        assertEquals(language, driver.findElement(By.id("language")).getText());
        assertEquals(comment, driver.findElement(By.id("comment")).getText());

        assertEquals(gender, driver.findElement(By.id("gender")).getText());
        assertEquals(option, driver.findElement(By.id("option")).getText());


//         check button colors
//         (green with white letter and red with white letters)

        WebElement yesButton = driver.findElement(By.className("w3-green"));
        WebElement noButton = driver.findElement(By.className("w3-red"));

        assertEquals("rgba(76, 175, 80, 1)", yesButton.getCssValue("background-color"));
        assertEquals("rgba(244, 67, 54, 1)", noButton.getCssValue("background-color"));

        assertEquals("rgb(255, 255, 255)", yesButton.getCssValue("text-decoration-color"));
        assertEquals("rgb(255, 255, 255)", yesButton.getCssValue("text-decoration-color"));


    }

    @Test
    public void yesOnWithNameFeedbackPage() throws Exception {
//         TODO:
//         enter only name
        String name = "Liene";
        driver.findElement(By.id("fb_name")).sendKeys(name);

//         click "Send"
        driver.findElement(By.tagName("button")).click();

//         click "Yes"
        driver.findElement(By.className("w3-green")).click();

//         check message text: "Thank you, NAME, for your feedback!"

        assertEquals("Thank you, " + name + ", for your feedback!", driver.findElement(By.id("message")).getText());


//         color of text is white with green on the background
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.id("message")).getCssValue("color"));
        assertEquals("rgba(76, 175, 80, 1)", driver.findElement(By.className("w3-green")).getCssValue("background-color"));
    }

    @Test
    public void yesOnWithoutNameFeedbackPage() throws Exception {
//         TODO:
//         click "Send" (without entering anything
        driver.findElement(By.tagName("button")).click();
//         click "Yes"
        driver.findElement(By.className("w3-green")).click();

//         check message text: "Thank you for your feedback!"
        String expectedMessage = "Thank you for your feedback!";
        assertEquals(expectedMessage, driver.findElement(By.id("message")).getText());

//         color of text is white with green on the background
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.id("message")).getCssValue("color"));
        assertEquals("rgba(76, 175, 80, 1)", driver.findElement(By.className("w3-green")).getCssValue("background-color"));

    }

    @Test
    public void noOnFeedbackPage() throws Exception {

        String name = "Liene";
        String age = "42";
        String option = "Good";
        String comment = "My comment";
        String language = "English";
        String gender = "female";

//         fill the whole form
        driver.findElement(By.id("fb_name")).sendKeys(name);
        driver.findElement(By.id("fb_age")).sendKeys(age);
        driver.findElement(By.tagName("textarea")).sendKeys(comment);

        driver.findElement(By.cssSelector("input[value='" + language + "']")).click();

        driver.findElement(By.cssSelector("input[value='" + gender + "']")).click();

        WebElement dropdown = driver.findElement(By.id("like_us"));
        Select dropSelect = new Select(dropdown);
        dropSelect.selectByVisibleText(option);

//         click "Send"
        driver.findElement(By.tagName("button")).click();

//         click "No"
        driver.findElement(By.className("w3-red")).click();

//         check fields are filled correctly (values are not clean)

        assertEquals(name, driver.findElement(By.id("fb_name")).getAttribute("value"));
        assertEquals(age, driver.findElement(By.id("fb_age")).getAttribute("value"));
        assertEquals(comment, driver.findElement(By.tagName("textarea")).getAttribute("value"));

        assertTrue(driver.findElement(By.cssSelector("input[value='" + language + "']")).isSelected());
        assertTrue(driver.findElement(By.cssSelector("input[value='" + gender + "']")).isSelected());

//
        WebElement dropdownTwo = driver.findElement(By.id("like_us"));
        Select backDrop = new Select(dropdownTwo);

        List<WebElement> allSelect = backDrop.getAllSelectedOptions();
        assertEquals(1, allSelect.size());
        assertEquals(option, allSelect.get(0).getText());

    }
}
