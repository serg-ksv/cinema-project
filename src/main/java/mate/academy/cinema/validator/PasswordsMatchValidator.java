package mate.academy.cinema.security;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import mate.academy.cinema.model.dto.UserRequestDto;

public class PasswordsMatchValidator implements
        ConstraintValidator<PasswordsMatch, UserRequestDto> {

    @Override
    public boolean isValid(UserRequestDto requestDto, ConstraintValidatorContext context) {
        return requestDto.getPassword().equals(requestDto.getRepeatPassword());
    }
}
