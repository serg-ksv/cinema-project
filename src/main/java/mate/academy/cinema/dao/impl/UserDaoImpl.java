package mate.academy.cinema.dao.impl;

import java.util.Optional;
import mate.academy.cinema.dao.UserDao;
import mate.academy.cinema.exceptions.DataProcessingException;
import mate.academy.cinema.lib.Dao;
import mate.academy.cinema.model.User;
import mate.academy.cinema.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class UserDaoImpl implements UserDao {
    private static final Logger LOGGER = Logger.getLogger(UserDaoImpl.class);

    @Override
    public User add(User user) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            LOGGER.info("User with id '" + user.getId() + "' was successfully added.");
            return user;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert user entity", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try (var session = HibernateUtil.getSessionFactory().openSession()) {
            var criteriaBuilder = session.getCriteriaBuilder();
            var criteriaQuery = criteriaBuilder.createQuery(User.class);
            var root = criteriaQuery.from(User.class);
            var predicateEmail = criteriaBuilder.equal(root.get("email"), email);
            criteriaQuery.select(root).where(predicateEmail);
            return Optional.ofNullable(session.createQuery(criteriaQuery).uniqueResult());
        } catch (Exception e) {
            throw new DataProcessingException("Can't retrieve user with email: " + email, e);
        }
    }
}
