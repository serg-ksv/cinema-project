package mate.academy.cinema.dao;

import mate.academy.cinema.model.Role;

public interface RoleDao extends GenericDao<Role> {
    Role getRoleByName(String roleName);
}
