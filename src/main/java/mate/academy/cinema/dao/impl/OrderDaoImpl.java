package mate.academy.cinema.dao.impl;

import java.util.List;
import mate.academy.cinema.dao.OrderDao;
import mate.academy.cinema.exceptions.DataProcessingException;
import mate.academy.cinema.model.Order;
import mate.academy.cinema.model.User;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDaoImpl extends GenericDaoImpl<Order> implements OrderDao {
    private final SessionFactory sessionFactory;

    public OrderDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Order> getOrderHistory(User user) {
        try (var session = sessionFactory.openSession()) {
            var query = session.createQuery("SELECT DISTINCT O FROM Order O "
                                            + "LEFT JOIN FETCH O.tickets "
                                            + "WHERE O.user = :user", Order.class);
            query.setParameter("user", user);
            return query.list();
        } catch (Exception e) {
            throw new DataProcessingException("Can't retrieve order", e);
        }
    }

    @Override
    public Order getById(Long id) {
        return super.getById(id, Order.class);
    }
}
