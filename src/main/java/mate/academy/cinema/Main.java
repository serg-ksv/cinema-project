package mate.academy.cinema;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import mate.academy.cinema.exceptions.AuthenticationException;
import mate.academy.cinema.lib.Injector;
import mate.academy.cinema.model.CinemaHall;
import mate.academy.cinema.model.Movie;
import mate.academy.cinema.model.MovieSession;
import mate.academy.cinema.model.User;
import mate.academy.cinema.security.AuthenticationService;
import mate.academy.cinema.service.CinemaHallService;
import mate.academy.cinema.service.MovieService;
import mate.academy.cinema.service.MovieSessionService;
import mate.academy.cinema.service.OrderService;
import mate.academy.cinema.service.ShoppingCartService;

public class Main {
    private static final Injector INJECTOR = Injector.getInstance("mate.academy.cinema");

    public static void main(String[] args) {
        var movie = new Movie();
        movie.setTitle("Fast and Furious");
        var movieService = (MovieService)
                INJECTOR.getInstance(MovieService.class);
        movieService.add(movie);
        movieService.getAll().forEach(System.out::println);

        var cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(50);
        cinemaHall.setDescription("some hall");
        var cinemaHallService = (CinemaHallService)
                INJECTOR.getInstance(CinemaHallService.class);
        cinemaHallService.add(cinemaHall);
        cinemaHallService.getAll().forEach(System.out::println);

        var movieSession = new MovieSession();
        movieSession.setCinemaHall(cinemaHall);
        movieSession.setMovie(movie);
        movieSession.setShowTime(LocalDateTime.of(LocalDate.now(), LocalTime.of(19, 30)));
        var movieSessionService = (MovieSessionService)
                INJECTOR.getInstance(MovieSessionService.class);
        movieSessionService.add(movieSession);
        var availableSessions = movieSessionService
                .findAvailableSessions(movie.getId(), LocalDate.now());
        availableSessions.forEach(System.out::println);

        var bob = new User();
        bob.setEmail("bob@mail.com");
        bob.setPassword("password");
        var alice = new User();
        alice.setEmail("alice@mail.com");
        alice.setPassword("123");

        var authenticationService = (AuthenticationService)
                INJECTOR.getInstance(AuthenticationService.class);
        var registeredBob = authenticationService.register(bob.getEmail(), bob.getPassword());
        var registeredAlice = authenticationService.register(alice.getEmail(), alice.getPassword());
        try {
            authenticationService.login(bob.getEmail(), bob.getPassword());
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }

        var shoppingCartService = (ShoppingCartService)
                INJECTOR.getInstance(ShoppingCartService.class);
        var selectedMovieSession = availableSessions.get(0);
        shoppingCartService.addSession(selectedMovieSession, registeredBob);
        shoppingCartService.addSession(selectedMovieSession, registeredAlice);
        var bobCart = shoppingCartService.getByUser(registeredBob);
        var aliceCart = shoppingCartService.getByUser(registeredAlice);

        var orderService = (OrderService) INJECTOR.getInstance(OrderService.class);
        orderService.completeOrder(bobCart.getTickets(), bobCart.getUser());
        orderService.completeOrder(aliceCart.getTickets(), aliceCart.getUser());
        orderService.getOrderHistory(bobCart.getUser()).forEach(System.out::println);
    }
}
