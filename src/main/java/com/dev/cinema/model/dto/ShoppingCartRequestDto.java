package com.dev.cinema.model.dto;

public class ShoppingCartRequestDto {
    private String shoppingCartOwner;
    private String ticketOwner;
    private Long movieSessionId;

    public String getShoppingCartOwner() {
        return shoppingCartOwner;
    }

    public void setShoppingCartOwner(String shoppingCartOwner) {
        this.shoppingCartOwner = shoppingCartOwner;
    }

    public String getTicketOwner() {
        return ticketOwner;
    }

    public void setTicketOwner(String ticketOwner) {
        this.ticketOwner = ticketOwner;
    }

    public Long getMovieSessionId() {
        return movieSessionId;
    }

    public void setMovieSessionId(Long movieSessionId) {
        this.movieSessionId = movieSessionId;
    }
}
