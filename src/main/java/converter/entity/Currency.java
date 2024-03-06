package converter.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "currency")
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "currency_name")
    private String currencyName;
    @Column(name = "conversion_rate")
    private double conversionRate;
    @Column(name = "abbreviation")
    private String abbreviation;

    public Currency(String currencyName, double conversionRate, String abbreviation) {
        this.currencyName = currencyName;
        this.conversionRate = conversionRate;
        this.abbreviation = abbreviation;
    }
    public Currency() {
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getCurrencyName() {
        return currencyName;
    }
    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }
    public double getConversionRate() {
        return conversionRate;
    }
    public void setConversionRate(double conversionRate) {
        this.conversionRate = conversionRate;
    }
    public String getAbbreviation() {
        return abbreviation;
    }
    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }
}
