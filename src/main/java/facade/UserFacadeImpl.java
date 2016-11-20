package facade;

import model.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class UserFacadeImpl implements UserFacade {

    @PersistenceContext
    private EntityManager em;

    @Override
    public User findUserById(int id) {
        User user = em.find(User.class, id);
        return user;
    }

    @Override
    public void create(User user) {
        em.persist(user);
    }

    @Override
    public void update(int id, String name, String email, String password) {
        User user = this.findUserById(id);
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setId(id);

        em.flush();
    }

    @Override
    public void destroyUser(User user) {
        em.remove(em.contains(user) ? user : em.merge(user));
    }

    @Override
    public void block(int id) {
        User userToBlockUnblock = this.findUserById(id);
        boolean blockStatus = userToBlockUnblock.isBlocked();
        if (blockStatus == true) {
            userToBlockUnblock.setBlocked(false);
        } else {
            userToBlockUnblock.setBlocked(true);
        }
    }

    @Override
    public List<User> getAllUsers() {
        Query q = em.createQuery("select u from User u");
        List<User> users = q.getResultList();
        return users;
    }


    /**
     * проверка логина-пароля для входа
     * @param user
     * @return
     */
    @Override
    public String checkLogin(User user) {
        List<User> usersForCheck = this.getAllUsers();

        String returnString = null;

        String email = user.getEmail();
        String password = user.getPassword();

        for (User userForCheck : usersForCheck) {
            if (email.equals(userForCheck.getEmail()) & password.equals(userForCheck.getPassword()) & userForCheck.isBlocked() == false) {
                returnString = "userLogined";
                break;
            } else {
                returnString = "index";
            }
        }
        return returnString;
    }


}
