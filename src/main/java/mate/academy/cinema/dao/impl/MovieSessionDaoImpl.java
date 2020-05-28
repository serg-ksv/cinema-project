package mate.academy.cinema.dao.impl;

import java.time.LocalDate;
import java.util.List;
import mate.academy.cinema.dao.MovieSessionDao;
import mate.academy.cinema.exceptions.DataProcessingException;
import mate.academy.cinema.lib.Dao;
import mate.academy.cinema.model.MovieSession;
import mate.academy.cinema.util.HibernateUtil;

@Dao
public class MovieSessionDaoImpl extends GenericDaoImpl<MovieSession> implements MovieSessionDao {
    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        try (var session = HibernateUtil.getSessionFactory().openSession()) {
            var criteriaBuilder = session.getCriteriaBuilder();
            var criteriaQuery = criteriaBuilder.createQuery(MovieSession.class);
            var root = criteriaQuery.from(MovieSession.class);
            var predicateMovieId = criteriaBuilder.equal(root.get("movie"), movieId);
            var predicateDate = criteriaBuilder.between(root.get("showTime"),
                    date.atStartOfDay(), date.plusDays(1).atStartOfDay());
            criteriaQuery.select(root).where(criteriaBuilder.and(predicateMovieId, predicateDate));
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't retrieve movie sessions.", e);
        }
    }
}
