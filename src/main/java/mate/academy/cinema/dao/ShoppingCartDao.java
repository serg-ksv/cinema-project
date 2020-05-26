package mate.academy.cinema.dao;

import mate.academy.cinema.model.ShoppingCart;
import mate.academy.cinema.model.User;

public interface ShoppingCartDao extends GenericDao<ShoppingCart> {
    ShoppingCart getByUser(User user);

    void update(ShoppingCart shoppingCart);
}
