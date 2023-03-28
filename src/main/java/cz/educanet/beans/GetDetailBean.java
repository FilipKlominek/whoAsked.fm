package cz.educanet.beans;

import cz.educanet.models.User;
import cz.educanet.repositories.UserRepository;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.sql.SQLException;
import java.time.LocalDateTime;

@Named
@SessionScoped
public class GetDetailBean implements Serializable {

    private int currentId = -1;
    private String fullName = "invalid user id selected";
    private String bio = "try accessing this page via link on index.xhtml or entering a valid id";
    private int questionCount = -1;
    private LocalDateTime updatedAt;

    private final UserRepository userRepository = new UserRepository();

    public User getDetail(int id) throws SQLException {
        this.currentId = id;
        User user = userRepository.getUserDetail(id);
        this.fullName = user.getFullName();
        this.bio = user.getBio();
        this.questionCount = user.getQuestionCount();
        this.updatedAt = user.getUpdatedAt();
        return user;
    }

    public int getCurrentId() {
        return currentId;
    }

    public void setCurrentId(int currentId) {
        this.currentId = currentId;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public int getQuestionCount() {
        return questionCount;
    }

    public void setQuestionCount(int questionCount) {
        this.questionCount = questionCount;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
