package helperpackage.pomPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

// representing cartaxcheck.co.uk
public class CarTaxHomePage {

    WebDriver driver;
    // input field for rg
    By regTextField = By.name("vrm");

    // get full check 'button'
    By fullCheckLink = By.linkText("Get a Full Check");

    // get free check button
    By freeCheckBtn = By.xpath("//button[contains(text(),'Free Car Check')]");

    // get x off MOT with fixter link
    By getMoneyOff = By.linkText("Get £10 off MOT or Servicing with Fixter");

    //mot check link
    //By motCheckRelink = By.cssSelector("a[href=/mot-check/]");

    // car history check link
    //By carHistoryCheck = By.cssSelector("a[href=/car-history-check/]");

    public CarTaxHomePage(WebDriver driver){
        this.driver = driver;
    }

    public void editText(String str){
        driver.findElement(regTextField).sendKeys(str);
    }

    public String getText(){
        String txt = driver.findElement(regTextField).getText();
        return txt;
    }


    public void clickFreeCheck(){
        driver.findElement(freeCheckBtn).click();
    }

    public void clickFullCheck(){
        driver.findElement(fullCheckLink).click();
    }
}
