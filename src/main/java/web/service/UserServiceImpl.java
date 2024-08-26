package web.service;

import org.springframework.stereotype.Service;
import web.dao.UserDAO;
import web.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public List<User> getUsersByCount(int count) {
        if (count > userDAO.getAllUsers().size()) {
            return userDAO.getAllUsers();
        } else {
            return userDAO.getAllUsers().subList(0, count);
        }
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public User getUserById(int id) {
        return userDAO.get(id);
    }

    public void saveUser(User user) {
        userDAO.save(user);
    }

    public void updateUser(int id, User user) {
        userDAO.update(id, user);
    }

    public void deleteUserById(int id) {
        userDAO.delete(id);
    }

}
