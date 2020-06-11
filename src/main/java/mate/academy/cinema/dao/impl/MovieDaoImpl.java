package mate.academy.cinema.dao.impl;

import java.util.List;
import mate.academy.cinema.dao.MovieDao;
import mate.academy.cinema.exceptions.DataProcessingException;
import mate.academy.cinema.model.Movie;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class MovieDaoImpl extends GenericDaoImpl<Movie> implements MovieDao {
    private final SessionFactory sessionFactory;

    public MovieDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Movie getById(Long id) {
        try (var session = sessionFactory.openSession()) {
            var query = session.createQuery("FROM Movie WHERE id = :id", Movie.class);
            query.setParameter("id", id);
            return query.uniqueResult();
        } catch (Exception e) {
            throw new DataProcessingException("Can't retrieve movie. ", e);
        }
    }

    @Override
    public List<Movie> getAll() {
        try (var session = sessionFactory.openSession()) {
            var query = session.createQuery("FROM Movie", Movie.class);
            return query.list();
        } catch (Exception e) {
            throw new DataProcessingException("Error retrieving all movies. ", e);
        }
    }
}
