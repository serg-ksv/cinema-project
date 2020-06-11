package mate.academy.cinema.model.mapper;

import mate.academy.cinema.model.MovieSession;
import mate.academy.cinema.model.dto.MovieSessionRequestDto;
import mate.academy.cinema.model.dto.MovieSessionResponseDto;
import mate.academy.cinema.service.CinemaHallService;
import mate.academy.cinema.service.MovieService;
import org.springframework.stereotype.Component;

@Component
public class MovieSessionMapper {
    private final MovieService movieService;
    private final CinemaHallService cinemaHallService;

    public MovieSessionMapper(MovieService movieService,
                              CinemaHallService cinemaHallService) {
        this.movieService = movieService;
        this.cinemaHallService = cinemaHallService;
    }

    public MovieSessionResponseDto getDtoFromMovieSession(
            MovieSession movieSession) {
        var dto = new MovieSessionResponseDto();
        dto.setMovieSessionId(movieSession.getId());
        dto.setMovieId(movieSession.getMovie().getId());
        dto.setMovieTitle(movieSession.getMovie().getTitle());
        dto.setCinemaHallId(movieSession.getCinemaHall().getId());
        dto.setShowTime(movieSession.getShowTime());
        return dto;
    }

    public MovieSession getMovieSessionFromDto(MovieSessionRequestDto requestDto) {
        var movieSession = new MovieSession();
        movieSession.setMovie(movieService.getById(requestDto.getMovieId()));
        movieSession.setShowTime(requestDto.getShowTime());
        movieSession.setCinemaHall(cinemaHallService.getById(requestDto.getCinemaHallId()));
        return movieSession;
    }
}
