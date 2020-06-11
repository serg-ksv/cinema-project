package mate.academy.cinema.controller;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.cinema.model.dto.OrderRequestDto;
import mate.academy.cinema.model.dto.OrderResponseDto;
import mate.academy.cinema.model.mapper.OrderMapper;
import mate.academy.cinema.service.OrderService;
import mate.academy.cinema.service.ShoppingCartService;
import mate.academy.cinema.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public void completeOrder(@RequestBody OrderRequestDto requestDto) {
        var user = userService.getById(requestDto.getUserId());
        var tickets = shoppingCartService.getByUser(user).getTickets();
        orderService.completeOrder(tickets, user);
    }

    @GetMapping
    public List<OrderResponseDto> getOrdersHistory(@RequestParam("user-id") Long userId) {
        return orderService.getOrderHistory(userService.getById(userId)).stream()
                .map(orderMapper::getDtoFromOrder)
                .collect(Collectors.toList());
    }
}
