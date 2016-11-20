package controller;


import facade.UserFacade;
import model.User;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class UserController implements Serializable {

    private String name, email, password;
    int id;

    @EJB
    UserFacade userFacade;

    /**
     * Создать нового пользователя
     */
    public void save() {
        User user = new User(name, email, password);
        userFacade.create(user);
    }

    /**
     * Посмотреть всех пользователей
     * @return
     */
    public List<User> getAllUsers() {
        List<User> users = userFacade.getAllUsers();

        return users;
    }

    public String editUser(int id) {
        this.setId(id);
        return "updateUser";
    }

    /**
     * Обновить пользователя
     * @param id
     * @return
     */
    public String updateUser() {
        userFacade.update(id, name, email, password);

        return "getAllEmployees";
    }

    /**
     * Удалить сотрудника
     * @return
     */
    public String destroyUser(User user) {
        userFacade.destroyUser(user);

        return "home?faces-redirect=true";
    }

    /**
     * Вход для пользователя
     */
    public String login() {
        User user = new User(name, email, password);
        String returnView = userFacade.checkLogin(user);

        return returnView;
    }

    /**
     * Блокировка или разблокировка пользователя
     * @param id
     * @return
     */
    public String blockUser(int id) {
        userFacade.block(id);

        return "getAllEmployees";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
