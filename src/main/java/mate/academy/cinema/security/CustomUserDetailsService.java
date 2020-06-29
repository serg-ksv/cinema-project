package mate.academy.cinema.security;

import mate.academy.cinema.service.UserService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;

    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var user = userService.getByEmail(email);
        UserBuilder builder = null;
        if (user != null) {
            builder = User.withUsername(email);
            builder.password(user.getPassword());
            String[] roles = user.getRoles().stream()
                    .map(role -> role.getName().toString())
                    .toArray(String[]::new);
            builder.roles(roles);
        } else {
            throw new UsernameNotFoundException("User not found.");
        }
        return builder.build();
    }
}
