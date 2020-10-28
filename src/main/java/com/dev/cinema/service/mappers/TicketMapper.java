package com.dev.cinema.service.mappers;

import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.Ticket;
import com.dev.cinema.model.dto.MovieSessionResponseDto;
import com.dev.cinema.model.dto.TicketResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TicketMapper {
    private final MovieSessionMapper movieSessionMapper;

    @Autowired
    public TicketMapper(MovieSessionMapper movieSessionMapper) {
        this.movieSessionMapper = movieSessionMapper;
    }

    public TicketResponseDto toTicketResponseDto(Ticket ticket) {
        TicketResponseDto ticketResponseDto = new TicketResponseDto();
        Long id = ticket.getId();
        String email = ticket.getOwner().getEmail();
        MovieSession movieSession = ticket.getMovieSession();
        MovieSessionResponseDto movieSessionResponseDto =
                movieSessionMapper.toMovieSessionResponseDto(movieSession);

        ticketResponseDto.setId(id);
        ticketResponseDto.setUserOwnerEmail(email);
        ticketResponseDto.setMovieSessionResponseDto(movieSessionResponseDto);
        return ticketResponseDto;
    }
}
