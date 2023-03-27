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
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    LocalDateTime.parse(resultSet.getString(7).replace(" ", "T")),
                    LocalDateTime.parse(resultSet.getString(8).replace(" ", "T"))
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
                        "WHERE u.userId = ? " +
                        "GROUP BY u.userId"
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
}
