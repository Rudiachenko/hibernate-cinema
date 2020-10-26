package com.dev.cinema.model.dto;

public class OrderRequestDto {
    private String userOrderOwner;
    private String userTicketOwner;
    private Long movieSessionId;

    public String getUserOrderOwner() {
        return userOrderOwner;
    }

    public void setUserOrderOwner(String userOrderOwner) {
        this.userOrderOwner = userOrderOwner;
    }

    public String getUserTicketOwner() {
        return userTicketOwner;
    }

    public void setUserTicketOwner(String userTicketOwner) {
        this.userTicketOwner = userTicketOwner;
    }

    public Long getMovieSessionId() {
        return movieSessionId;
    }

    public void setMovieSessionId(Long movieSessionId) {
        this.movieSessionId = movieSessionId;
    }
}
