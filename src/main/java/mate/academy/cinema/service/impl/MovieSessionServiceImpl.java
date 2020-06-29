package mate.academy.cinema.service.impl;

import java.time.LocalDate;
import java.util.List;
import mate.academy.cinema.dao.MovieSessionDao;
import mate.academy.cinema.model.MovieSession;
import mate.academy.cinema.service.MovieSessionService;
import org.springframework.stereotype.Service;

@Service
public class MovieSessionServiceImpl implements MovieSessionService {
    private final MovieSessionDao movieSessionDao;

    public MovieSessionServiceImpl(MovieSessionDao movieSessionDao) {
        this.movieSessionDao = movieSessionDao;
    }

    @Override
    public MovieSession add(MovieSession movieSession) {
        return movieSessionDao.add(movieSession);
    }

    @Override
    public MovieSession getById(Long id) {
        return movieSessionDao.getById(id);
    }

    @Override
    public List<MovieSession> getAvailableSessions(Long movieId, LocalDate date) {
        return movieSessionDao.getAvailableSessions(movieId, date);
    }
}
