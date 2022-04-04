package selenium.sample;

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

public class Sample7Task {
    WebDriver driver;
    String base_url = "https://kristinek.github.io/site/examples/actions";

    // method which is being run before each test
    @Before
    public void startingTests() throws Exception {
        // from Sample 1:
        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        // declaration above:
        driver = new ChromeDriver();
        //open page:
        driver.get(base_url);
    }

    // method which is being run after each test
    @After
    public void endingTests() throws Exception {
        driver.close();
    }

    @Test
    public void selectCheckBox() throws Exception {
//         TODO:
//        check that none of the checkboxes are ticked
        WebElement option1 = driver.findElement(By.id("vfb-6-0"));
        WebElement option2 = driver.findElement(By.id("vfb-6-1"));
        WebElement option3 = driver.findElement(By.id("vfb-6-2"));

        assertFalse(option1.isSelected());
        assertFalse(option2.isSelected());
        assertFalse(option3.isSelected());

//        tick  "Option 2"

        option2.click();
//        check that "Option 1" and "Option 3" are not ticked, but "Option 2" is ticked

        assertFalse(option1.isSelected());
        assertTrue(option2.isSelected());
        assertFalse(option3.isSelected());

//        tick  "Option 3"
        option3.click();

//        click result
        driver.findElement(By.id("result_button_checkbox")).click();

//        check that text 'You selected value(s): Option 2, Option 3' is being displayed
        assertEquals("You selected value(s): Option 2, Option 3",
                driver.findElement(By.id("result_checkbox")).getText());

    }


    @Test
    public void selectRadioButton() throws Exception {
//         TODO:
//        check that none of the radio are selected
        WebElement radio1 = driver.findElement(By.id("vfb-7-1"));
        WebElement radio2 = driver.findElement(By.id("vfb-7-2"));
        WebElement radio3 = driver.findElement(By.id("vfb-7-3"));

        assertFalse(radio1.isSelected());
        assertFalse(radio2.isSelected());
        assertFalse(radio3.isSelected());

//        select  "Option 3"
        radio3.click();
//        check that "Option 1" and "Option 2' are not select, but "Option 3" is selected

        assertFalse(radio1.isSelected());
        assertFalse(radio2.isSelected());
        assertTrue(radio3.isSelected());

//        select  "Option 1"
        radio1.click();

//        check that "Option 2" and "Option 3' are not select, but "Option 1" is selected
        assertTrue(radio1.isSelected());
        assertFalse(radio2.isSelected());
        assertFalse(radio3.isSelected());

//        click result
        driver.findElement(By.id("result_button_ratio")).click();

//        check that 'You selected option: Option 1' text is being displayed
        assertEquals("You selected option: Option 1",
                driver.findElement(By.id("result_radio")).getText());

    }

    @Test
    public void selectOption() throws Exception {
        WebElement dropdown = driver.findElement(By.id("vfb-12"));
        Select dropSelect = new Select(dropdown);
//
//        select "Option 3" in Select
        dropSelect.selectByVisibleText("Option 3");

//        check that selected option is "Option 3"
        List<WebElement> allSelect = dropSelect.getAllSelectedOptions();
        assertEquals(1, allSelect.size());
        assertEquals("Option 3", allSelect.get(0).getText());


//        select "Option 2" in Select
        dropSelect.selectByVisibleText("Option 2");

//        check that selected option is "Option 2"
        List<WebElement> allSelect2 = dropSelect.getAllSelectedOptions();
        assertEquals(1, allSelect2.size());
        assertEquals("Option 2", allSelect2.get(0).getText());

//        click result

        driver.findElement(By.id("result_button_select")).click();
//        check that 'You selected option: Option 2' text is being displayed

        assertEquals("You selected option: Option 2",
                driver.findElement(By.id("result_select")).getText());


    }

    @Test
    public void chooseDateViaCalendarBonus() throws Exception {
//         TODO:
//        enter date '4 of July 2007' using calendar widget
//        check that correct date is added

    }

    @Test
    public void chooseDateViaTextBoxBonus() throws Exception {
//         TODO:
//        enter date '2 of May 1959' using text
//        check that correct date is added

        WebElement calendar = driver.findElement(By.id("vfb-8"));
        calendar.click();
        calendar.sendKeys("05/02/59");

        //Lauma comment:to click somewhere else on screen and remove the displayed calendar
        driver.findElement(By.tagName("h2")).click();

        driver.findElement(By.id("result_button_date")).click();

        assertEquals("You entered date: 05/02/59",
                driver.findElement(By.id("result_date")).getText());


    }
}
