package mate.academy.cinema.model.mapper;

import mate.academy.cinema.model.User;
import mate.academy.cinema.model.dto.UserResponseDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponseDto getDtoFromUser(User user) {
        var responseDto = new UserResponseDto();
        responseDto.setId(user.getId());
        responseDto.setEmail(user.getEmail());
        return responseDto;
    }
}
