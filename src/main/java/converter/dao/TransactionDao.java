package converter.dao;

import converter.entity.Transaction;
import converter.datasource.*;
import jakarta.persistence.EntityManager;

public class TransactionDao {
    public void persist(Transaction transaction) {
        EntityManager em = MariaDbJpaConnection.getInstance();
        em.getTransaction().begin();
        em.persist(transaction);
        em.getTransaction().commit();
    }
}
