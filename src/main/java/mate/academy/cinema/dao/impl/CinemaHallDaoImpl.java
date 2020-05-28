package mate.academy.cinema.dao.impl;

import java.util.List;
import mate.academy.cinema.dao.CinemaHallDao;
import mate.academy.cinema.exceptions.DataProcessingException;
import mate.academy.cinema.lib.Dao;
import mate.academy.cinema.model.CinemaHall;
import mate.academy.cinema.util.HibernateUtil;

@Dao
public class CinemaHallDaoImpl extends GenericDaoImpl<CinemaHall> implements CinemaHallDao {
    @Override
    public List<CinemaHall> getAll() {
        try (var session = HibernateUtil.getSessionFactory().openSession()) {
            var criteriaQuery = session.getCriteriaBuilder()
                    .createQuery(CinemaHall.class);
            criteriaQuery.from(CinemaHall.class);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Error retrieving all cinema halls", e);
        }
    }
}
