package facade;


import model.User;

import javax.ejb.Local;
import java.util.List;

@Local
public interface UserFacade {
    User findUserById(int id);

    void create(User user);

    void update(int id, String name, String email, String password);

    void block(int id);

    List<User> getAllUsers();

    String checkLogin(User user);

    void destroyUser(User user);
}
