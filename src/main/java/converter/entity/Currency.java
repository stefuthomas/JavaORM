package converter.entity;

import jakarta.persistence.*;

@Entity
@Table
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "abbreviation")
    private String abbreviation;
    @Column(name = "currency_name")
    private String currencyName;
    @Column(name = "conversion_rate")
    private double conversionRate;

    public Currency(String abbreviation, String currencyName, double conversionRate) {
        this.abbreviation = abbreviation;
        this.currencyName = currencyName;
        this.conversionRate = conversionRate;
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
