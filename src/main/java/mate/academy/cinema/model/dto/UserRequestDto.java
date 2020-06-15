package mate.academy.cinema.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import mate.academy.cinema.security.EmailConstraint;
import mate.academy.cinema.security.PasswordsMatch;

@PasswordsMatch
public class UserRequestDto {
    @EmailConstraint
    @Size(min = 8, max = 40)
    private String email;
    @NotBlank(message = "Password can't be empty!")
    private String password;
    @NotBlank(message = "repeatPassword can't be empty!")
    private String repeatPassword;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
}
