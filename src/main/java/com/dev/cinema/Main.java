package com.dev.cinema;

import com.dev.cinema.lib.Injector;
import com.dev.cinema.model.Movie;
import com.dev.cinema.service.MovieService;

public class Main {
    private static final Injector injector = Injector.getInstance("com.dev.cinema");

    public static void main(String[] args) {
        MovieService movieService = (MovieService) injector.getInstance(MovieService.class);

        Movie movie = new Movie();
        movie.setTitle("Harry Potter");
        movie.setDescription("Harry Potter is a series of seven fantasy "
                + "novels written by British author J. K. Rowling.");
        movieService.add(movie);
        System.out.println(movieService.getAll());
    }
}
