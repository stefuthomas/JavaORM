package converter.application;

import converter.dao.ConverterDao;
import converter.entity.Converter;
import converter.view.ConverterGUI;
import java.util.List;

public class ConverterApp {
    private ConverterGUI gui;
    private Converter converter = new Converter();
    private ConverterDao converterDao = new ConverterDao();

    public ConverterApp(ConverterGUI gui) {
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
    public static void main(String[] args) {
        ConverterGUI.launch(ConverterGUI.class);
    }

}