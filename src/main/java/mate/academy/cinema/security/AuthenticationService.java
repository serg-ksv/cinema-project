package mate.academy.cinema.security;

import mate.academy.cinema.model.User;

public interface AuthenticationService {
    User register(String email, String password);
}
