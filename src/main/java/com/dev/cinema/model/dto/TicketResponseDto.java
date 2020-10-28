package com.dev.cinema.model.dto;

public class TicketResponseDto {
    private Long id;
    private String userOwnerEmail;
    private MovieSessionResponseDto movieSessionResponseDto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserOwnerEmail() {
        return userOwnerEmail;
    }

    public void setUserOwnerEmail(String userOwnerEmail) {
        this.userOwnerEmail = userOwnerEmail;
    }

    public MovieSessionResponseDto getMovieSessionResponseDto() {
        return movieSessionResponseDto;
    }

    public void setMovieSessionResponseDto(MovieSessionResponseDto movieSessionResponseDto) {
        this.movieSessionResponseDto = movieSessionResponseDto;
    }
}
