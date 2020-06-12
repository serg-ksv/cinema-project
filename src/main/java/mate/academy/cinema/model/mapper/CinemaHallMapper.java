package mate.academy.cinema.model.mapper;

import mate.academy.cinema.model.CinemaHall;
import mate.academy.cinema.model.dto.CinemaHallRequestDto;
import mate.academy.cinema.model.dto.CinemaHallResponseDto;
import org.springframework.stereotype.Component;

@Component
public class CinemaHallMapper {
    public CinemaHall getCinemaHallFromDto(CinemaHallRequestDto requestDto) {
        var cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(requestDto.getCapacity());
        cinemaHall.setDescription(requestDto.getDescription());
        return cinemaHall;
    }

    public CinemaHallResponseDto getDtoFromCinemaHall(CinemaHall cinemaHall) {
        var responseDto = new CinemaHallResponseDto();
        responseDto.setId(cinemaHall.getId());
        responseDto.setCapacity(cinemaHall.getCapacity());
        responseDto.setDescription(cinemaHall.getDescription());
        return responseDto;
    }
}
