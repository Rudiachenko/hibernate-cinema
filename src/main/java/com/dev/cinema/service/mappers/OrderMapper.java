package com.dev.cinema.service.mappers;

import com.dev.cinema.model.Order;
import com.dev.cinema.model.Ticket;
import com.dev.cinema.model.User;
import com.dev.cinema.model.dto.OrderRequestDto;
import com.dev.cinema.service.MovieSessionService;
import com.dev.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    private final UserService userService;
    private final MovieSessionService movieSessionService;

    @Autowired
    public OrderMapper(UserService userService, MovieSessionService movieSessionService) {
        this.userService = userService;
        this.movieSessionService = movieSessionService;
    }

    public Order toOrderModel(OrderRequestDto orderRequestDto){
        Long movieSessionId = orderRequestDto.getMovieSessionId();
        String orderOwner = orderRequestDto.getUserOrderOwner();
        String ticketOwner = orderRequestDto.getUserTicketOwner();

        User userOrderOwner = userService.findByEmail(orderOwner).get();
        User userTickerOrder = userService.findByEmail(ticketOwner).get();

        Ticket ticket = new Ticket();
        Order order = new Order();
        ticket.setOwner(userTickerOrder);
        order.setUser(userOrderOwner);
        return order;
    }
}
