package mate.academy.cinema.dao.impl;

import java.util.List;
import mate.academy.cinema.dao.OrderDao;
import mate.academy.cinema.exceptions.DataProcessingException;
import mate.academy.cinema.lib.Dao;
import mate.academy.cinema.model.Order;
import mate.academy.cinema.model.User;
import mate.academy.cinema.util.HibernateUtil;

@Dao
public class OrderDaoImpl extends GenericDaoImpl<Order> implements OrderDao {
    @Override
    public List<Order> getOrderHistory(User user) {
        try (var session = HibernateUtil.getSessionFactory().openSession()) {
            var query = session.createQuery("select distinct o from Order o "
                                            + "left join fetch o.tickets "
                                            + "where o.user = :user", Order.class);
            query.setParameter("user", user);
            return query.list();
        } catch (Exception e) {
            throw new DataProcessingException("Can't retrieve order", e);
        }
    }
}
