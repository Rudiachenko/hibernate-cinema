package com.dev.cinema.model.dto;

import javax.validation.constraints.NotNull;

public class MovieSessionRequestDto {
    @NotNull
    private String showTime;
    @NotNull
    private Long cinemaHallId;
    @NotNull
    private Long movieId;

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }

    public Long getCinemaHallId() {
        return cinemaHallId;
    }

    public void setCinemaHallId(Long cinemaHallId) {
        this.cinemaHallId = cinemaHallId;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }
}
