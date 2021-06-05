package helperpackage;

import helperpackage.pomPages.CarTaxHomePage;
import helperpackage.pomPages.FreeCheckPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class StepDefinitions {

    String drivePath = "./src/test/drivers/chromedriver.exe";
    String currentUrl;
    WebDriver driver;
    CarTaxHomePage homePage;
    FreeCheckPage freePage;

    @Given("Valid Registration value of {string}")
    public void validRegistrationValueOf(String arg0) {
        Car.isValid(arg0);
    }

    @When("the website {string} is live")
    public void theWebsiteIsLive(String arg0) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", drivePath);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://"+arg0+"/");
        Thread.sleep(500);
    }

    @Then("user should be taken to the {string} page")
    public void userShouldBeTakenToThePage(String arg0) {
        Assert.assertTrue(true);
    }

    @And("user types register of {string}")
    public void userTypesRegisterOf(String arg0) throws InterruptedException {
        homePage = new CarTaxHomePage(driver);
        Thread.sleep(500);
        homePage.editText(arg0);
    }

    @And("user clicks on the free button")
    public void userClicksOnFreeButton() throws InterruptedException{
        homePage.clickFreeCheck();
    }

    @Then("user should arrive on url {string}")
    public void userShouldArriveOnUrl(String str) throws InterruptedException {
        freePage = new FreeCheckPage(driver);
        String url = driver.getCurrentUrl();
        assertTrue(url.matches("^(https:\\/\\/" + str +  "\\/)"));
        Thread.sleep(500);
    }

    @And("car of color {string} is returned")
    public void carOfColorReturned(String str) throws InterruptedException {
        freePage = new FreeCheckPage(driver);
        Thread.sleep(500);
        String color = freePage.getColourReturned();
        assertEquals(str,color);
        Thread.sleep(500);
        driver.close();
    }


}
