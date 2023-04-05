package cz.educanet.beans;

import cz.educanet.models.User;
import cz.educanet.repositories.UserRepository;
import jakarta.annotation.ManagedBean;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

@ManagedBean
@Named
@SessionScoped
public class UserBean implements Serializable {
    private boolean isLoggedIn = false;

    private int loggedUser = -1;

    private final UserRepository usersRepository = new UserRepository();
    private String name = "";
    private String email = "";
    private String unHashedPasswordTest = "";


    public boolean login() throws SQLException {

        if (this.name.equals("") || this.email.equals("") || this.unHashedPasswordTest.equals("")) return false;

        List<User> userList = usersRepository.getUsers();

        for (User u : userList) {

            if (u.getFullName().equals(this.name) && u.getEmail().equals(this.getEmail())) {
                if (u.getHashedPassword().equals(this.hash(this.unHashedPasswordTest + u.getSalt()))) {
                    this.loggedUser = u.getUserId();
                    this.isLoggedIn = true;
                    return true;
                }
            }
        }

        return false;
    }

    private String hash(String unHashedPassword) {
        return DigestUtils.sha256Hex(unHashedPassword);
    }

    public void logout() {
        this.name = "";
        this.email = "";
        this.unHashedPasswordTest = "";
        this.isLoggedIn = false;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
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

    public String getUnHashedPasswordTest() {
        return unHashedPasswordTest;
    }

    public void setUnHashedPasswordTest(String unHashedPasswordTest) {
        this.unHashedPasswordTest = unHashedPasswordTest;
    }

    public int getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(int currentUser) {
        this.loggedUser = currentUser;
    }
}
