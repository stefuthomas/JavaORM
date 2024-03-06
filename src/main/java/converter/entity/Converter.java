package converter.entity;
import jakarta.persistence.*;
public class Converter {

    public String convert(double amount, double from, double to, String abbrevation) {
        double conversion = 0;
        String formattedConversion = null;
        conversion = amount * (from / to);
        formattedConversion = String.format("%.2f", conversion);
        return formattedConversion + " " + abbrevation;
    }
}