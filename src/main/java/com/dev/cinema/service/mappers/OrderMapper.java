package com.dev.cinema.service.mappers;

import com.dev.cinema.model.Order;
import com.dev.cinema.model.dto.OrderResponseDto;
import com.dev.cinema.model.dto.TicketResponseDto;
import com.dev.cinema.model.dto.UserResponseDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {
    private final TicketMapper ticketMapper;
    private final UserMapper userMapper;


    public OrderMapper(TicketMapper ticketMapper,
                       UserMapper userMapper) {
        this.ticketMapper = ticketMapper;
        this.userMapper = userMapper;
    }

    public OrderResponseDto toOrderResponseDto(Order order) {
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        UserResponseDto userResponseDto = userMapper.toUserResponseDto(order.getUser());
        List<TicketResponseDto> ticketResponseDto = order.getTickets().stream()
                .map(ticketMapper::toTicketResponseDto)
                .collect(Collectors.toList());
        orderResponseDto.setId(order.getId());
        orderResponseDto.setUserResponseDto(userResponseDto);
        orderResponseDto.setTicketResponseDto(ticketResponseDto);
        orderResponseDto.setOrderDate(order.getOrderDate().toString());
        return orderResponseDto;
    }
}
