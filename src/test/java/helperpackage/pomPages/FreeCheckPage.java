package helperpackage.pomPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

// representing cartaxcheck.co.uk
public class FreeCheckPage {

    WebDriver driver;
    // input field for rg
    By regTextField = By.id("vrm-input");

    By regTableText = By.xpath("//./dt[.='Registration']/following-sibling::dd");

    By makeTableText = By.xpath("//./dt[.='Make']/following-sibling::dd");

    By modelTableText = By.xpath("//./dt[.='Model']/following-sibling::dd");

    By colour = By.xpath("//./dt[.='Colour']/following-sibling::dd");

    By year = By.xpath("//./dt[.='Year']/following-sibling::dd");

    By redo = By.linkText("Try Again");

    public FreeCheckPage(WebDriver driver){
        this.driver = driver;
    }

    public Boolean badReq(){
        if (driver.findElements(redo).size() != 0){
            return true;
        }
        return false;
    }

    public String getRegReturned(){
        String txt = driver.findElement(regTableText).getText();
        return txt;
    }

    public String getMakeReturned(){
        String txt = driver.findElement(makeTableText).getText();
        return txt;
    }

    public String getModelReturned(){
        String txt = driver.findElement(modelTableText).getText();
        return txt;
    }
    public String getColourReturned(){
        String txt = driver.findElement(colour).getText();
        return txt;
    }

    public Integer getYearReturned(){
        String txt = driver.findElement(year).getText();
        return Integer.parseInt(txt);
    }

}
