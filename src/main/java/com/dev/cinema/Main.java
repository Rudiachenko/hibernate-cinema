package com.dev.cinema;

import com.dev.cinema.config.AppConfig;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.Movie;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.Ticket;
import com.dev.cinema.model.User;
import com.dev.cinema.security.AuthenticationService;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import com.dev.cinema.service.MovieSessionService;
import com.dev.cinema.service.OrderService;
import com.dev.cinema.service.ShoppingCartService;
import exceptions.AuthenticationException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) throws AuthenticationException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        logger.info("Application started successfully");
        MovieService movieService = context.getBean(MovieService.class);
        Movie harryPotter = new Movie();
        harryPotter.setTitle("Harry Potter");
        harryPotter.setDescription("Harry Potter is a series of seven fantasy "
                + "novels written by British author J. K. Rowling.");
        movieService.add(harryPotter);

        Movie rocknRolla = new Movie();
        rocknRolla.setTitle("RocknRolla");
        rocknRolla.setDescription("RocknRolla is a 2008 action crime film"
                + "written and directed by Guy Ritchie");
        movieService.add(rocknRolla);

        movieService.getAll().forEach(logger::info);

        CinemaHallService cinemaHallService = context.getBean(CinemaHallService.class);
        CinemaHall blueHall = new CinemaHall();
        blueHall.setDescription("Blue hall");
        blueHall.setCapacity(100);
        cinemaHallService.add(blueHall);

        CinemaHall orangeHall = new CinemaHall();
        orangeHall.setDescription("Orange Hall");
        orangeHall.setCapacity(150);
        cinemaHallService.add(orangeHall);

        cinemaHallService.getAll().forEach(logger::info);

        MovieSession movieSession1 = new MovieSession();
        movieSession1.setMovie(harryPotter);
        movieSession1.setCinemaHall(blueHall);
        LocalDate date1 = LocalDate.of(2020, 11, 12);
        LocalTime time1 = LocalTime.of(12, 0);
        LocalDateTime showTime1 = LocalDateTime.of(date1, time1);
        movieSession1.setShowTime(showTime1);
        MovieSessionService movieSessionService = context.getBean(MovieSessionService.class);
        movieSessionService.add(movieSession1);

        MovieSession movieSession2 = new MovieSession();
        movieSession2.setMovie(rocknRolla);
        movieSession2.setCinemaHall(orangeHall);
        LocalDate date2 = LocalDate.of(2020, 12, 15);
        LocalTime time2 = LocalTime.of(15, 30);
        LocalDateTime showTime2 = LocalDateTime.of(date2, time2);
        movieSession2.setShowTime(showTime2);
        movieSessionService.add(movieSession2);

        logger.info(movieSessionService.findAvailableSessions(1L, date1));
        logger.info(movieSessionService.findAvailableSessions(2L, date2));

        AuthenticationService authenticationService = context.getBean(AuthenticationService.class);
        User userBob = authenticationService.register("bob@gmail.com", "qwerty123456");
        User userAlice = authenticationService.register("alice@gmail.com", "qwerty123456");
        logger.info(authenticationService.login("alice@gmail.com", "qwerty123456"));

        ShoppingCartService shoppingCartService = context.getBean(ShoppingCartService.class);
        shoppingCartService.addSession(movieSession1, userBob);
        shoppingCartService.addSession(movieSession2, userAlice);
        logger.info(shoppingCartService.getByUser(userAlice));

        OrderService orderService = context.getBean(OrderService.class);
        List<Ticket> bobTickets = shoppingCartService.getByUser(userBob).getTickets();
        orderService.completeOrder(bobTickets, userBob);
        orderService.getOrderHistory(userBob).forEach(logger::info);

        logger.info("Application finished successfully");
    }
}
