package mate.academy.cinema.dao;

import java.util.List;
import mate.academy.cinema.model.CinemaHall;

public interface CinemaHallDao extends GenericDao<CinemaHall> {
    CinemaHall getById(Long id);

    List<CinemaHall> getAll();
}
