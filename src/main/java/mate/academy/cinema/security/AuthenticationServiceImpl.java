package mate.academy.cinema.security;

import mate.academy.cinema.dao.impl.UserDaoImpl;
import mate.academy.cinema.exceptions.AuthenticationException;
import mate.academy.cinema.lib.Inject;
import mate.academy.cinema.lib.Service;
import mate.academy.cinema.model.User;
import mate.academy.cinema.service.UserService;
import mate.academy.cinema.util.HashUtil;
import org.apache.log4j.Logger;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private static final Logger LOGGER = Logger.getLogger(UserDaoImpl.class);

    @Inject
    private UserService userService;

    @Override
    public User login(String email, String password) throws AuthenticationException {
        var user = userService.findByEmail(email);
        var hashPassword = HashUtil.hashPassword(password, user.getSalt());
        if (user.getPassword().equals(hashPassword)) {
            LOGGER.info("User with email '" + user.getEmail() + "' successfully logged in.");
            return user;
        }
        throw new AuthenticationException("Incorrect email or password.");
    }

    @Override
    public User register(String email, String password) {
        byte[] salt = HashUtil.getSalt();
        var hashPassword = HashUtil.hashPassword(password, salt);
        var user = new User();
        user.setEmail(email);
        user.setPassword(hashPassword);
        user.setSalt(salt);
        return userService.add(user);
    }
}
