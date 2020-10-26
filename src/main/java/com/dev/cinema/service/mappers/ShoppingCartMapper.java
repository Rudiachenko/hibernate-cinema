package com.dev.cinema.service.mappers;

import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.Ticket;
import com.dev.cinema.model.User;
import com.dev.cinema.model.dto.ShoppingCartRequestDto;
import com.dev.cinema.model.dto.ShoppingCartResponseDto;
import com.dev.cinema.model.dto.TicketResponseDto;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ShoppingCartMapper {
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;
    private final TicketMapper ticketMapper;

    @Autowired
    public ShoppingCartMapper(UserService userService,
                              ShoppingCartService shoppingCartService,
                              TicketMapper ticketMapper) {
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
        this.ticketMapper = ticketMapper;
    }

    public ShoppingCartResponseDto toShoppingCartResponseDto(Long userId) {
        ShoppingCart shoppingCart = shoppingCartService.getByUser(userService.findById(userId));
        ShoppingCartResponseDto shoppingCartResponseDto = new ShoppingCartResponseDto();

        Long id = shoppingCart.getId();
        User user = shoppingCart.getUser();
        List<TicketResponseDto> ticketResponseDtoList = shoppingCart.getTickets()
                .stream()
                .map(ticketMapper::toTicketResponseDto)
                .collect(Collectors.toList());

        shoppingCartResponseDto.setId(id);
        shoppingCartResponseDto.setUserCartOwnerEmail(user.getEmail());
        shoppingCartResponseDto.setTicketResponseDtoList(ticketResponseDtoList);
        return shoppingCartResponseDto;
    }
}
