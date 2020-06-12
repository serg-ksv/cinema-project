package mate.academy.cinema.model.mapper;

import mate.academy.cinema.model.Movie;
import mate.academy.cinema.model.dto.MovieRequestDto;
import mate.academy.cinema.model.dto.MovieResponseDto;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper {
    public MovieResponseDto getDtoFromMovie(Movie movie) {
        var dto = new MovieResponseDto();
        dto.setId(movie.getId());
        dto.setTitle(movie.getTitle());
        return dto;
    }

    public Movie getMovieFromDto(MovieRequestDto requestDto) {
        var movie = new Movie();
        movie.setTitle(requestDto.getTitle());
        movie.setDescription(requestDto.getDescription());
        return movie;
    }
}
