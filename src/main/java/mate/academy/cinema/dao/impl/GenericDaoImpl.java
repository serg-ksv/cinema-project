package mate.academy.cinema.dao.impl;

import mate.academy.cinema.dao.GenericDao;
import mate.academy.cinema.exceptions.DataProcessingException;
import mate.academy.cinema.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public abstract class GenericDaoImpl<T> implements GenericDao<T> {
    public T add(T element) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(element);
            transaction.commit();
            return element;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert " + element, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
