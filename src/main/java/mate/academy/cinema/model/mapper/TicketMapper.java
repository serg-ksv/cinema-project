package mate.academy.cinema.model.mapper;

import mate.academy.cinema.model.Ticket;
import mate.academy.cinema.model.dto.TicketResponseDto;
import org.springframework.stereotype.Component;

@Component
public class TicketMapper {
    public TicketResponseDto getDtoFromTicket(Ticket ticket) {
        var responseDto = new TicketResponseDto();
        responseDto.setId(ticket.getId());
        responseDto.setMovieTitle(ticket.getMovieSession().getMovie().getTitle());
        responseDto.setCinemaHallId(ticket.getMovieSession().getCinemaHall().getId());
        responseDto.setShowTime(ticket.getMovieSession().getShowTime());
        return responseDto;
    }
}
