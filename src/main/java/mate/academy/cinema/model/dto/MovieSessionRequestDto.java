package mate.academy.cinema.model.dto;

import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;

public class MovieSessionRequestDto {
    @NotNull(message = "Field movieId can't be null")
    private Long movieId;
    @NotNull(message = "Field cinemaHallId can't be null")
    private Long cinemaHallId;
    @NotNull(message = "Field showTime can't be null")
    private LocalDateTime showTime;

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public Long getCinemaHallId() {
        return cinemaHallId;
    }

    public void setCinemaHallId(Long cinemaHallId) {
        this.cinemaHallId = cinemaHallId;
    }

    public LocalDateTime getShowTime() {
        return showTime;
    }

    public void setShowTime(LocalDateTime showTime) {
        this.showTime = showTime;
    }
}
