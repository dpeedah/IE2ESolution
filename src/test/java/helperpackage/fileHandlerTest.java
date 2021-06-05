package helperpackage;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// tests that the input / output value getters work
public class fileHandlerTest {

    @Test
    public void testGetInputs(){
        List<String> inputs = new ArrayList<String>();
        try {
           inputs = TestValueExtractor.getRegInputValuesCustom();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int inputSize = inputs.size();
        Assert.assertTrue(inputSize > 0);
    }

    @Test
    public void testGetOutputs(){
        List<Car> cars = new ArrayList<Car>();
        try {
            cars = TestValueExtractor.getOutputValues();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int inputSize = cars.size();
        Assert.assertTrue(inputSize > 0);
    }
}
