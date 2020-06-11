package mate.academy.cinema.model.mapper;

import java.util.stream.Collectors;
import mate.academy.cinema.model.ShoppingCart;
import mate.academy.cinema.model.dto.ShoppingCartResponseDto;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartMapper {
    private final TicketMapper ticketMapper;

    public ShoppingCartMapper(TicketMapper ticketMapper) {
        this.ticketMapper = ticketMapper;
    }

    public ShoppingCartResponseDto getDtoFromShoppingCart(ShoppingCart shoppingCart) {
        var responseDto = new ShoppingCartResponseDto();
        responseDto.setId(shoppingCart.getId());
        responseDto.setTickets(shoppingCart.getTickets().stream()
                .map(ticketMapper::getDtoFromTicket).collect(Collectors.toList()));
        responseDto.setUserId(shoppingCart.getUser().getId());
        return responseDto;
    }
}
