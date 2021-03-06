package mate.academy.cinema.dao;

import java.util.Optional;
import mate.academy.cinema.model.User;

public interface UserDao extends GenericDao<User> {
    Optional<User> getByEmail(String email);
}
