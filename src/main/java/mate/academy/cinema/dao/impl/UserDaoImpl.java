package mate.academy.cinema.dao.impl;

import java.util.Optional;
import mate.academy.cinema.dao.UserDao;
import mate.academy.cinema.exceptions.DataProcessingException;
import mate.academy.cinema.lib.Dao;
import mate.academy.cinema.model.User;
import mate.academy.cinema.util.HibernateUtil;

@Dao
public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao {
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
