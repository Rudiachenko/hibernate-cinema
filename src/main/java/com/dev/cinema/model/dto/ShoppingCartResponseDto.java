package com.dev.cinema.model.dto;

import java.util.List;

public class ShoppingCartResponseDto {
    private Long id;
    private String userCartOwnerEmail;
    private List<TicketResponseDto> ticketResponseDtoList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCartOwnerEmail() {
        return userCartOwnerEmail;
    }

    public void setUserCartOwnerEmail(String userCartOwner) {
        this.userCartOwnerEmail = userCartOwner;
    }

    public List<TicketResponseDto> getTicketResponseDtoList() {
        return ticketResponseDtoList;
    }

    public void setTicketResponseDtoList(List<TicketResponseDto> ticketResponseDtoList) {
        this.ticketResponseDtoList = ticketResponseDtoList;
    }
}
