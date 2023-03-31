package cz.educanet.repositories;

import cz.educanet.models.User;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class UserRepository implements Serializable {
    public List<User> getUsers() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/users?user=root&password=");

        ArrayList<User> users = new ArrayList<>();

        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT u.userId, u.email, u.hashedPassword, u.fullName, u.bio, u.picture, u.createdAt, u.updatedAt " +
                        "FROM ask.user AS u "
        );

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            users.add(new User(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3).substring(0, 64), //the hashed password
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    LocalDateTime.parse(resultSet.getString(7).replace(" ", "T")),
                    LocalDateTime.parse(resultSet.getString(8).replace(" ", "T")),
                    resultSet.getString(3).substring(64) //the salt
            ));
        }

        connection.close();
        preparedStatement.close();
        resultSet.close();

        return users;
    }

    public User getUserDetail(int id) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/users?user=root&password=");

        ArrayList<User> users = new ArrayList<>();

        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT u.userId, u.fullName, u.bio, COUNT(q.questionId), u.updatedAt " +
                        "FROM ask.user AS u " +
                        "JOIN ask.question AS q ON (q.authorId = u.userId) " +
                        "WHERE u.userId = ? "
        );

        preparedStatement.setMaxRows(1);

        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            users.add(new User(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    LocalDateTime.parse(resultSet.getString(5).replace(" ", "T"))
            ));
        }
        connection.close();
        preparedStatement.close();
        resultSet.close();

        return users.get(0);
    }

    public void addUser(String name, String email, String unHashedPassword) throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/users?user=root&password=");

        User user = new User(name, email, unHashedPassword); //password gets hashed in constructor and salt gets generated

        PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO ask.user (fullName, Email, hashedPassword, createdAt, updatedAt)" +
                        "VALUES (?, ?, ?, NOW(), NOW())"
        );

        preparedStatement.setString(1, user.getFullName());
        preparedStatement.setString(2, user.getEmail());
        preparedStatement.setString(3, user.getHashedPassword() + user.getSalt()); //salt is stored in the same column after the password

        preparedStatement.close();
        connection.close();
    }
}
