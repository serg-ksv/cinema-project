package mate.academy.cinema.dao;

import java.time.LocalDate;
import java.util.List;
import mate.academy.cinema.model.MovieSession;

public interface MovieSessionDao extends GenericDao<MovieSession> {
    List<MovieSession> getAvailableSessions(Long movieId, LocalDate date);
}
