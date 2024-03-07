package converter.entity;
public class Converter {

    public String convert(double amount, double from, double to, String abbrevation) {
        double conversion;
        String formattedConversion;
        conversion = amount * (from / to);
        formattedConversion = String.format("%.2f", conversion);
        return formattedConversion + " " + abbrevation;
    }
}