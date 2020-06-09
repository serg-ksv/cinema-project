package mate.academy.cinema.dao.impl;

import java.time.LocalDate;
import java.util.List;
import mate.academy.cinema.dao.MovieSessionDao;
import mate.academy.cinema.exceptions.DataProcessingException;
import mate.academy.cinema.model.MovieSession;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MovieSessionDaoImpl extends GenericDaoImpl<MovieSession> implements MovieSessionDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public MovieSessionDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        try (var session = sessionFactory.openSession()) {
            var query = session.createQuery("FROM MovieSession "
                                            + "WHERE movie.id = :movieId "
                                            + "AND showTime BETWEEN :startOfDay AND :endOfDay",
                                            MovieSession.class);
            query.setParameter("movieId", movieId);
            query.setParameter("startOfDay", date.atStartOfDay());
            query.setParameter("endOfDay", date.plusDays(1).atStartOfDay());
            return query.list();
        } catch (Exception e) {
            throw new DataProcessingException("Can't retrieve movie sessions.", e);
        }
    }
}
