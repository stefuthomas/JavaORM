package converter.application;

import converter.controller.ConverterController;
import converter.entity.Currency;
import converter.view.ConverterGUI;
import converter.view.ConverterGUI;
import converter.datasource.MariaDbJpaConnection;
import jakarta.persistence.EntityManager;

public class ConverterApp {
    public static void main(String[] args) {
        Currency eur = new Currency("EUR", "Euro", 1.0);
        Currency usd = new Currency("USD", "US Dollar", 1.08);
        Currency gbp = new Currency("GBP", "British Pound", 0.86);
        Currency jpy = new Currency("JPY", "Japanese Yen", 0.0061);
        Currency sek = new Currency("SEK", "Swedish Krona", 0.089);
        Currency chf = new Currency("CHF", "Swiss Franc", 1.04);
        Currency gip = new Currency("GIP", "Gibraltar Pound", 1.16);
        Currency jod = new Currency("JOD", "Jordanian Dinar", 1.3);

        EntityManager em = MariaDbJpaConnection.getInstance();
        em.getTransaction().begin();
        em.persist(eur);
        em.persist(usd);
        em.persist(gbp);
        em.persist(jpy);
        em.persist(sek);
        em.persist(chf);
        em.persist(gip);
        em.persist(jod);
        em.getTransaction().commit();

        ConverterGUI.launch(ConverterGUI.class);
    }
    
}
