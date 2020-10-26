package com.dev.cinema.controllers;

import com.dev.cinema.model.Ticket;
import com.dev.cinema.model.User;
import com.dev.cinema.model.dto.OrderRequestDto;
import com.dev.cinema.service.OrderService;
import com.dev.cinema.service.mappers.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @Autowired
    public OrderController(OrderService orderService, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @PostMapping
    public void complete(@RequestBody OrderRequestDto orderRequestDto) {
        List<Ticket> tickets = orderMapper.toOrderModel(orderRequestDto).getTickets();
        User user = orderMapper.toOrderModel(orderRequestDto).getUser();
        orderService.completeOrder(tickets, user);
    }
}
