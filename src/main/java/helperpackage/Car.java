package helperpackage;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Car {
    private String reg;
    private String make;
    private String model;
    private String color;
    private Integer yearMake;

    public Car() {
    }

    @Override
    public String toString() {
        return "Car{" +
                "reg='" + reg + '\'' +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", yearMake=" + yearMake +
                '}';
    }
    // Ensures register values are correct
    public static Boolean isValid(String reg){
        Matcher matcher = Pattern.compile("([A-Z]{2}[0-9]{2}\\s?[A-Z]{3})").matcher(reg);
        if (matcher.matches()){
            return true;
        }else{
            return false;
        }
    }


    public Car(String reg, String make, String model, String color, Integer yearMake) {
        this.reg = reg;
        this.make = make;
        this.model = model;
        this.color = color;
        this.yearMake = yearMake;
    }



    public String getReg() {
        return reg;
    }

    public void setReg(String reg) {
        this.reg = reg;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getYearMake() {
        return yearMake;
    }

    public void setYearMake(Integer yearMake) {
        this.yearMake = yearMake;
    }
}
