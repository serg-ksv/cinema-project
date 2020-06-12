package mate.academy.cinema.model.mapper;

import java.util.stream.Collectors;
import mate.academy.cinema.model.Order;
import mate.academy.cinema.model.dto.OrderResponseDto;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    private final TicketMapper ticketMapper;

    public OrderMapper(TicketMapper ticketMapper) {
        this.ticketMapper = ticketMapper;
    }

    public OrderResponseDto getDtoFromOrder(Order order) {
        var responseDto = new OrderResponseDto();
        responseDto.setId(order.getId());
        responseDto.setOrderDate(order.getOrderDate());
        responseDto.setTickets(order.getTickets().stream()
                .map(ticketMapper::getDtoFromTicket)
                .collect(Collectors.toList()));
        return responseDto;
    }
}
