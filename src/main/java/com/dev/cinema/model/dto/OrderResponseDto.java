package com.dev.cinema.model.dto;

import com.dev.cinema.model.Ticket;

import java.time.LocalDateTime;
import java.util.List;

public class OrderResponseDto {
    private Long id;
    private String orderDate;
    private List<TicketResponseDto> ticketResponseDto;
    private UserResponseDto userResponseDto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public List<TicketResponseDto> getTicketResponseDto() {
        return ticketResponseDto;
    }

    public void setTicketResponseDto(List<TicketResponseDto> ticketResponseDto) {
        this.ticketResponseDto = ticketResponseDto;
    }

    public UserResponseDto getUserResponseDto() {
        return userResponseDto;
    }

    public void setUserResponseDto(UserResponseDto userResponseDto) {
        this.userResponseDto = userResponseDto;
    }
}
