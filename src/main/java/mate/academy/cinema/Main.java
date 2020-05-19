package mate.academy.cinema;

import mate.academy.cinema.lib.Injector;
import mate.academy.cinema.model.Movie;
import mate.academy.cinema.service.MovieService;

public class Main {
    private static final Injector INJECTOR = Injector.getInstance("mate.academy.cinema");

    public static void main(String[] args) {
        var movie = new Movie();
        movie.setTitle("Fast and Furious");
        var movieService = (MovieService) INJECTOR.getInstance(MovieService.class);
        movieService.add(movie);
        movieService.getAll().forEach(System.out::println);
    }
}
