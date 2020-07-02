package mate.academy.cinema.controller;

import java.util.Set;
import javax.annotation.PostConstruct;
import mate.academy.cinema.model.Role;
import mate.academy.cinema.model.User;
import mate.academy.cinema.security.AuthenticationService;
import mate.academy.cinema.service.RoleService;
import mate.academy.cinema.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class InjectDataController {
    private final RoleService roleService;
    private final AuthenticationService authenticationService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public InjectDataController(RoleService roleService,
                                AuthenticationService authenticationService,
                                UserService userService,
                                PasswordEncoder passwordEncoder) {
        this.roleService = roleService;
        this.authenticationService = authenticationService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void injectData() {
        var roleAdmin = Role.of("ADMIN");
        var roleUser = Role.of("USER");
        roleService.add(roleAdmin);
        roleService.add(roleUser);
        var admin = new User();
        admin.setEmail("admin@mail.com");
        admin.setPassword(passwordEncoder.encode("admin"));
        admin.setRoles(Set.of(roleAdmin));
        userService.add(admin);
        authenticationService.register("user@mail.com", "1234");
    }
}
