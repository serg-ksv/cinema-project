package mate.academy.cinema.dao;

import java.util.List;
import mate.academy.cinema.model.Movie;

public interface MovieDao extends GenericDao<Movie> {
    List<Movie> getAll();
}
