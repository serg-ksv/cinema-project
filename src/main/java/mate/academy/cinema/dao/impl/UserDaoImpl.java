package mate.academy.cinema.dao.impl;

import java.util.Optional;
import mate.academy.cinema.dao.UserDao;
import mate.academy.cinema.exceptions.DataProcessingException;
import mate.academy.cinema.model.User;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao {
    private final SessionFactory sessionFactory;

    public UserDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User getById(Long id) {
        return super.getById(id, User.class);
    }

    @Override
    public Optional<User> getByEmail(String email) {
        try (var session = sessionFactory.openSession()) {
            var query = session.createQuery("FROM User U "
                                            + "LEFT JOIN FETCH U.roles "
                                            + "WHERE U.email = :email", User.class);
            query.setParameter("email", email);
            return Optional.ofNullable(query.uniqueResult());
        } catch (Exception e) {
            throw new DataProcessingException("Can't retrieve user with email: " + email, e);
        }
    }
}
