package com.dev.cinema.controllers;

import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.Movie;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.dto.MovieSessionRequestDto;
import com.dev.cinema.model.dto.MovieSessionResponseDto;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MovieSessionMapper {
    private final MovieService movieService;
    private final CinemaHallService cinemaHallService;

    @Autowired
    public MovieSessionMapper(MovieService movieService, CinemaHallService cinemaHallService) {
        this.movieService = movieService;
        this.cinemaHallService = cinemaHallService;
    }

    protected MovieSession toMovieSessionModel(MovieSessionRequestDto movieSessionRequestDto) {
        MovieSession movieSession = new MovieSession();
        CinemaHall cinemaHall = cinemaHallService.get(movieSessionRequestDto.getCinemaHallId());
        Movie movie = movieService.get(movieSessionRequestDto.getMovieId());

        movieSession.setShowTime(LocalDateTime.parse(movieSessionRequestDto.getShowTime(),
                DateTimeFormatter.ISO_DATE_TIME));
        movieSession.setCinemaHall(cinemaHall);
        movieSession.setMovie(movie);
        return movieSession;
    }

    protected MovieSessionResponseDto toMovieSessionResponseDto(MovieSession movieSession) {
        MovieSessionResponseDto movieSessionResponseDto
                = new MovieSessionResponseDto();
        movieSessionResponseDto.setCinemaHallDescription(movieSession
                .getCinemaHall().getDescription());
        movieSessionResponseDto.setId(movieSession.getId());
        movieSessionResponseDto.setMovie(movieSession.getMovie().getTitle());
        movieSessionResponseDto.setShowTime(movieSession.getShowTime()
                .format(DateTimeFormatter.ISO_LOCAL_TIME));
        return movieSessionResponseDto;
    }
}
