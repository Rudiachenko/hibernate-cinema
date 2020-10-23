package com.dev.cinema.model.dto;

public class MovieSessionResponseDto {
    private String showTime;
    private String cinemaHallDescription;
    private String movie;

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }

    public String getCinemaHallDescription() {
        return cinemaHallDescription;
    }

    public void setCinemaHallDescription(String cinemaHallDescription) {
        this.cinemaHallDescription = cinemaHallDescription;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }
}
