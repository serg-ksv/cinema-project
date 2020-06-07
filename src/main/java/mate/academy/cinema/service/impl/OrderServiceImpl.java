package mate.academy.cinema.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import mate.academy.cinema.dao.OrderDao;
import mate.academy.cinema.model.Order;
import mate.academy.cinema.model.Ticket;
import mate.academy.cinema.model.User;
import mate.academy.cinema.service.OrderService;
import mate.academy.cinema.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao;
    private final ShoppingCartService shoppingCartService;

    @Autowired
    public OrderServiceImpl(OrderDao orderDao, ShoppingCartService shoppingCartService) {
        this.orderDao = orderDao;
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    public Order completeOrder(List<Ticket> tickets, User user) {
        var order = new Order();
        order.setUser(user);
        order.setTickets(tickets);
        order.setOrderDate(LocalDateTime.now());
        orderDao.add(order);
        var shoppingCart = shoppingCartService.getByUser(user);
        shoppingCartService.clear(shoppingCart);
        return order;
    }

    @Override
    public List<Order> getOrderHistory(User user) {
        return orderDao.getOrderHistory(user);
    }
}
