package cz.educanet.repositories;

import cz.educanet.models.Question;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Named
@SessionScoped
public class QuestionRepository implements Serializable {

    public void addQuestion(String text, int authorId, int targetId) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/users?user=root&password=");

        PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO ask.question (question, authorId, targetId, createdAt, updatedAt)" +
                        "VALUES (?, ?, ?, NOW(), NOW())"
        );

        preparedStatement.setString(1, text);
        preparedStatement.setInt(2, authorId);
        preparedStatement.setInt(3, targetId);

        preparedStatement.execute();

        preparedStatement.close();
        connection.close();
    }
}
