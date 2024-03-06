package converter.datasource;

import jakarta.persistence.*;
public class MariaDbJpaConnection {
    private static EntityManagerFactory emf = null;
    private static EntityManager em = null;
    public static EntityManager getInstance() {
        if (em==null) {
            if (emf==null) {
                emf = Persistence.createEntityManagerFactory("ConverterMariaDbUnit");
            }
            em = emf.createEntityManager();
        }
        return em;
    }
}