package mate.academy.cinema.dao.impl;

import java.util.List;
import mate.academy.cinema.dao.CinemaHallDao;
import mate.academy.cinema.exceptions.DataProcessingException;
import mate.academy.cinema.model.CinemaHall;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class CinemaHallDaoImpl extends GenericDaoImpl<CinemaHall> implements CinemaHallDao {
    private final SessionFactory sessionFactory;

    public CinemaHallDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    @Override
    public CinemaHall getById(Long id) {
        return super.getById(id, CinemaHall.class);
    }

    @Override
    public List<CinemaHall> getAll() {
        try (var session = sessionFactory.openSession()) {
            var query = session.createQuery("FROM CinemaHall", CinemaHall.class);
            return query.list();
        } catch (Exception e) {
            throw new DataProcessingException("Error retrieving all cinema halls", e);
        }
    }
}
