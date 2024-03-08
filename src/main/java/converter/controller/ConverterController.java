package converter.controller;

import converter.dao.ConverterDao;
import converter.dao.TransactionDao;
import converter.entity.Currency;
import converter.entity.Converter;
import converter.entity.Transaction;
import converter.view.ConverterGUI;
import java.util.List;

public class ConverterController {
    private ConverterGUI gui;
    private Converter converter = new Converter();
    private ConverterDao converterDao = new ConverterDao();
    private TransactionDao transactionDao = new TransactionDao();

    public ConverterController(ConverterGUI gui) {
        this.gui = gui;
    }
    public void convert(double amount, String from, String to) {
        double fromRate = converterDao.getRate(from);
        double toRate = converterDao.getRate(to);
        String convertedCurrency = converter.convert(amount, fromRate, toRate, to);
        gui.setResult(convertedCurrency);
    }
    public void passCurrencyNamesToGui() {
        List<String> currencyNames = converterDao.getCurrencyNames();
        gui.setCurrencyNames(currencyNames);
    }

    public void addCurrency(String name, double rate, String abbrevation) {
        Currency currency = new Currency(abbrevation, name, rate);
        converterDao.persist(currency);
        List<String> currencyNames = converterDao.getCurrencyNames();
        gui.setCurrencyNames(currencyNames);
    }

    public void createTransaction(String from, String to, double amount) {
        Currency currencyFrom = converterDao.find(from);
        Currency currencyTo = converterDao.find(to);
        transactionDao.persist(new Transaction(amount, currencyFrom, currencyTo));
    }
}