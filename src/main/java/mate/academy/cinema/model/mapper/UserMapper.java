package mate.academy.cinema.model.mapper;

import mate.academy.cinema.model.User;
import mate.academy.cinema.model.dto.UserRequestDto;
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

    public User getUserFromDto(UserRequestDto requestDto) {
        var user = new User();
        user.setEmail(requestDto.getEmail());
        user.setPassword(requestDto.getPassword());
        return user;
    }
}
