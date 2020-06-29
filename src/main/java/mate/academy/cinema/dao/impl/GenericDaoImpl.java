package mate.academy.cinema.dao.impl;

import mate.academy.cinema.dao.GenericDao;
import mate.academy.cinema.exceptions.DataProcessingException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public abstract class GenericDaoImpl<T> implements GenericDao<T> {
    private final SessionFactory sessionFactory;

    public GenericDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public T add(T element) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
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

    public T getById(Long id, Class<T> type) {
        try (var session = sessionFactory.openSession()) {
            return session.get(type, id);
        } catch (Exception e) {
            throw new DataProcessingException("Can't retrieve " + type.getSimpleName(), e);
        }
    }
}
