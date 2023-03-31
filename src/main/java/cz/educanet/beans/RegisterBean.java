package cz.educanet.beans;

import cz.educanet.repositories.UserRepository;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.sql.SQLException;

@Named
@SessionScoped
public class RegisterBean implements Serializable {

    @Inject
    private UserRepository userRepository;
    private String name = "";
    private String email = "";
    private String unHashedPassword = "";
    private String confirmUnHashedPassword = "";



    public boolean register() throws SQLException {

        if (this.name.equals("") || this.email.equals("") || this.unHashedPassword.equals("") || this.confirmUnHashedPassword.equals(""))
            return false;

        if (!this.unHashedPassword.equals(this.confirmUnHashedPassword)) return false;

        userRepository.addUser(this.name, this.email, this.unHashedPassword);

        this.name = "";
        this.email = "";
        this.unHashedPassword = "";
        this.confirmUnHashedPassword = "";

        return true;
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

    public String getUnHashedPassword() {
        return unHashedPassword;
    }

    public void setUnHashedPassword(String unHashedPassword) {
        this.unHashedPassword = unHashedPassword;
    }

    public String getConfirmUnHashedPassword() {
        return confirmUnHashedPassword;
    }

    public void setConfirmUnHashedPassword(String confirmUnHashedPassword) {
        this.confirmUnHashedPassword = confirmUnHashedPassword;
    }
}
