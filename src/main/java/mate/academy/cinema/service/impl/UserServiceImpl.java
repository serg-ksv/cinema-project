package mate.academy.cinema.service.impl;

import mate.academy.cinema.dao.UserDao;
import mate.academy.cinema.lib.Inject;
import mate.academy.cinema.lib.Service;
import mate.academy.cinema.model.User;
import mate.academy.cinema.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Inject
    private UserDao userDao;

    @Override
    public User add(User user) {
        return userDao.add(user);
    }

    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }
}
