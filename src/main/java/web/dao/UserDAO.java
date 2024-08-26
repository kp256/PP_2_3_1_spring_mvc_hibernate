package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;

import java.util.List;

@Repository
public class UserDAO {
    private List<User> users;

    public List<User> getAllUsers() {
        return users;
    }

    public User get(int id) {
        return users.stream().filter(user -> user.getId() == id).findFirst().orElse(null);
    }

    public void save(User user) {
        users.add(user);
    }

    public void update(int id, User user) {
        User old = get(id);
        old.setName(user.getName());
        old.setSurname(user.getSurname());
        old.setEmail(user.getEmail());
    }

    public void delete(int id) {
        users.removeIf(user -> user.getId() == id);
    }
}
