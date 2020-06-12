package mate.academy.cinema.dao.impl;

import mate.academy.cinema.dao.ShoppingCartDao;
import mate.academy.cinema.exceptions.DataProcessingException;
import mate.academy.cinema.model.ShoppingCart;
import mate.academy.cinema.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class ShoppingCartDaoImpl extends GenericDaoImpl<ShoppingCart> implements ShoppingCartDao {
    private final SessionFactory sessionFactory;

    public ShoppingCartDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    @Override
    public ShoppingCart getByUser(User user) {
        try (var session = sessionFactory.openSession()) {
            var query = session.createQuery("FROM ShoppingCart SC "
                                            + "LEFT JOIN FETCH SC.tickets "
                                            + "WHERE SC.user = :user", ShoppingCart.class);
            query.setParameter("user", user);
            return query.uniqueResult();
        } catch (Exception e) {
            throw new DataProcessingException("Can't retrieve shopping cart", e);
        }
    }

    @Override
    public void update(ShoppingCart shoppingCart) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(shoppingCart);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't update shopping cart entity", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
