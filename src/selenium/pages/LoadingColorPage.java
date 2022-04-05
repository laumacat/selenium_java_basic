package selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static org.junit.Assert.*;


public class LoadingColorPage extends GenericSamplePage {
    @FindBy(how = How.ID, using = "start_green") // By.id("name")
    private WebElement startGreen; // WebElement nameInput = driver.findElement(By.id("name"));
    @FindBy(how = How.ID, using = "loading_green")
    private WebElement loadingGreen;
    @FindBy(how = How.ID, using = "finish_green")
    private WebElement finishGreen;


    public void clickStartGreen() {
        startGreen.click();

    }

    public boolean displayStartGreen() {
        return startGreen.isDisplayed();

    }

    public String textLoadingGreen() {
        return loadingGreen.getText();

    }

    public boolean displayLoadingGreen() {
        return loadingGreen.isDisplayed();

    }

    public String textFinishGreen() {
        return finishGreen.getText();

    }

}
