package converter.entity;

import jakarta.persistence.*;
import org.checkerframework.checker.units.qual.A;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double amount;
    @OneToOne
    private Currency fromCurrency;
    @OneToOne
    private Currency toCurrency;
    public Transaction(double amount, Currency fromCurrency, Currency toCurrency) {
        this.amount = amount;
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
    }

    public Transaction() {}
}