package com.dev.cinema.service.mappers;

import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.User;
import com.dev.cinema.model.dto.ShoppingCartResponseDto;
import com.dev.cinema.model.dto.TicketResponseDto;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartMapper {
    private final TicketMapper ticketMapper;

    @Autowired
    public ShoppingCartMapper(TicketMapper ticketMapper) {
        this.ticketMapper = ticketMapper;
    }

    public ShoppingCartResponseDto toShoppingCartResponseDto(ShoppingCart shoppingCart) {
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
