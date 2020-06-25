package mate.academy.cinema.dao.impl;

import mate.academy.cinema.dao.RoleDao;
import mate.academy.cinema.exceptions.DataProcessingException;
import mate.academy.cinema.model.Role;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl extends GenericDaoImpl<Role> implements RoleDao {
    private final SessionFactory sessionFactory;

    public RoleDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Role getRoleByName(String roleName) {
        try (var session = sessionFactory.openSession()) {
            var query = session.createQuery("FROM Role WHERE name = :name", Role.class);
            query.setParameter("name", Role.RoleName.valueOf(roleName));
            return query.uniqueResult();
        } catch (Exception e) {
            throw new DataProcessingException("Can't retrieve role with name: " + roleName, e);
        }
    }
}
