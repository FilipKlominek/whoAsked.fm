package cz.educanet.beans;

import cz.educanet.models.User;
import cz.educanet.repositories.UserRepository;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

@Named
@SessionScoped
public class GetUsersBean implements Serializable {
    private final UserRepository userRepository = new UserRepository();

    public List<User> getUsers() throws SQLException {
        return userRepository.getUsers();
    }
}
