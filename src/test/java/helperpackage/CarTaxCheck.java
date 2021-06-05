package helperpackage;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import helperpackage.pomPages.CarTaxHomePage;
import helperpackage.pomPages.FreeCheckPage;
import org.junit.*;

import org.junit.runners.Parameterized;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.junit.experimental.categories.Category;
@RunWith(Parameterized.class)
public class CarTaxCheck {
    private String currentReg;
    static String drivePath = "./src/test/drivers/chromedriver.exe";
    String currentUrl;
    static WebDriver driver;
    CarTaxHomePage homePage;
    FreeCheckPage freePage;
    static FileHandler fh;
    static Logger logger = Logger.getLogger(CarTaxCheck.class.getName());
    List<Car> output_values = TestValueExtractor.getOutputValues();

    public CarTaxCheck(String str) throws IOException {
        this.currentReg = str;
    }

    //for info
    public void logInfo (String str){
        logger.log(Level.INFO,str);
    }

    //for info
    public void logFine (String str){
        logger.log(Level.FINE,str);
    }

    //for tests that fail
    public void logWarning (String str){
        logger.log(Level.WARNING,str);
    }

    @Before
    public void initialize() throws IOException {
        String date = java.time.LocalDate.now().toString();
        String name = "log " + date + ".txt";
        fh = new FileHandler("./src/test/logs/" + name,0,1, true);
        logger.addHandler(fh);
        logger.setUseParentHandlers(false);
        System.setProperty("webdriver.chrome.driver", drivePath);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://cartaxcheck.co.uk/");
    }

    @Before
    public void reset(){
        driver.navigate().to("https://cartaxcheck.co.uk/");
    }

    public Car getOutputCar(String reg) throws IOException {
        List<Car> outputs = TestValueExtractor.getOutputValues();
        Car returnCar = new Car();
        for (int y = 0; y < outputs.size() ; ++y){
            Car car = outputs.get(y);
            if (car.getReg().toUpperCase(Locale.ROOT).equals(reg) ){
                returnCar = car;
            }
        }
        return returnCar;
    }

    public void compareOutputCars(Car realCar, Car expected){
        if ( (realCar.getReg().equals(expected.getReg()))
                && (realCar.getColor().equals(expected.getColor()))
                && (realCar.getMake().equals(expected.getMake()))
                && (realCar.getModel().equals(expected.getModel()))
                && (realCar.getYearMake().equals(expected.getYearMake())) ){
            logInfo("MATCHING OUTPUT - EXPECTED : " + expected.toString() + "\n REAL OUTPUT : " + realCar.toString());
        }else{
            logWarning("FAILURE - Expected : " + expected.toString() +  "\n" +  "Returned : " + realCar.toString());
        }
    }


    @Test
    public void testInputOutput() throws InterruptedException, IOException {
        String regNoSpace = this.currentReg.replace(" ","");
        String capitalisedReg = regNoSpace.toUpperCase(Locale.ROOT);
        homePage = new CarTaxHomePage(driver);
        Thread.sleep(1000);
        homePage.editText(capitalisedReg);
        Thread.sleep(1000);
        homePage.clickFreeCheck();
        Thread.sleep(1000);
        freePage = new FreeCheckPage(driver);

        // badReq() returns true if the "Vehicle not found" error appears on the site This causes the test to fail.
        if (freePage.badReq()){
            String error = "Bad request given, register of : " + capitalisedReg;
            logWarning(error);
            //reset();
            fh.close();
            logger.removeHandler(fh);

            driver.close();
            Assert.assertTrue(false);
            Thread.sleep(1000);
            return;
        }
        currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.matches("^(https:\\/\\/cartaxcheck.co.uk\\/)\\S+"));
        Thread.sleep(1000);
        String reg = freePage.getRegReturned();
        String make = freePage.getMakeReturned();
        String model = freePage.getModelReturned();
        String color = freePage.getColourReturned();
        int year = freePage.getYearReturned();
        Car realCar = new Car(reg,make,model,color,year);
        Car outputExpected = getOutputCar(capitalisedReg);
        compareOutputCars(realCar,outputExpected);
        Assert.assertTrue(outputExpected.getReg().equals(reg));
        Assert.assertTrue(outputExpected.getMake().equals(make));
        Assert.assertTrue(outputExpected.getModel().equals(model));
        Assert.assertTrue(outputExpected.getColor().equals(color));
        Assert.assertTrue(outputExpected.getYearMake().equals(year));
        driver.navigate().to("https://cartaxcheck.co.uk/");
        //reset();
        fh.close();
        logger.removeHandler(fh);
        Thread.sleep(1000);

        currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.matches("^(https:\\/\\/cartaxcheck.co.uk\\/)"));
        driver.close();

    }


    @Parameterized.Parameters
    public static Collection primeNumbers() throws IOException {
        List<String> input_values = TestValueExtractor.getInputValuesBase();
        return input_values;
    }
}
