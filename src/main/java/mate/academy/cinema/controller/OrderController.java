package mate.academy.cinema.controller;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.cinema.model.dto.OrderResponseDto;
import mate.academy.cinema.model.mapper.OrderMapper;
import mate.academy.cinema.service.OrderService;
import mate.academy.cinema.service.ShoppingCartService;
import mate.academy.cinema.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;
    private final OrderMapper orderMapper;

    public OrderController(OrderService orderService, UserService userService,
                           ShoppingCartService shoppingCartService, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
        this.orderMapper = orderMapper;
    }

    @PostMapping("/complete")
    public void completeOrder(Authentication authentication) {
        var user = userService.findByEmail(authentication.getName());
        var tickets = shoppingCartService.getByUser(user).getTickets();
        orderService.completeOrder(tickets, user);
    }

    @GetMapping
    public List<OrderResponseDto> getOrdersHistory(Authentication authentication) {
        var user = userService.findByEmail(authentication.getName());
        return orderService.getOrderHistory(user).stream()
                .map(orderMapper::getDtoFromOrder)
                .collect(Collectors.toList());
    }
}
