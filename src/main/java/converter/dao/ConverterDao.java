package converter.dao;

import converter.datasource.*;
import converter.entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class ConverterDao {
    public void persist(Currency currency) {
        EntityManager em = MariaDbJpaConnection.getInstance();
        em.getTransaction().begin();
        em.persist(currency);
        em.getTransaction().commit();
    }

    public Currency find(int id) {
        EntityManager em = MariaDbJpaConnection.getInstance();
        return em.find(Currency.class, id);
    }

    public void update(Currency currency) {
        EntityManager em = MariaDbJpaConnection.getInstance();
        em.getTransaction().begin();
        em.merge(currency);
        em.getTransaction().commit();
    }

    public void delete(Currency currency) {
        EntityManager em = MariaDbJpaConnection.getInstance();
        em.getTransaction().begin();
        em.remove(currency);
        em.getTransaction().commit();
    }

    public double getRate(String currency) {
        EntityManager em = MariaDbJpaConnection.getInstance();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Double> query = cb.createQuery(Double.class);
        Root<Currency> root = query.from(Currency.class);
        query.select(root.get("conversionRate")).where(cb.equal(root.get("currencyName"), currency));
        return em.createQuery(query).getSingleResult();
    }

    public List<String> getCurrencyNames() {
        EntityManager em = MariaDbJpaConnection.getInstance();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<String> query = cb.createQuery(String.class);
        Root<Currency> root = query.from(Currency.class);
        query.select(root.get("currencyName"));
        return em.createQuery(query).getResultList();
    }
}
