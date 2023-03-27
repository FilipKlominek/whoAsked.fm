package cz.educanet.models;

import java.time.LocalDateTime;

public class User {

    private final int userId;
    private  String email = "";
    private  String hashedPassword = "";
    private final String fullName;
    private final String bio;
    private  String picture = "";
    private final LocalDateTime createdAt;
    private  LocalDateTime updatedAt = null;

    private final int questionCount;

    public User(int userId, String email, String hashedPassword, String fullName, String bio, String picture, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.userId = userId;
        this.email = email;
        this.hashedPassword = hashedPassword;
        this.fullName = fullName;
        this.bio = bio;
        this.picture = picture;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        questionCount = -1;
    }

    public User(int userId, String fullName, String bio, int questionCount, LocalDateTime createdAt) {
        this.userId = userId;
        this.fullName = fullName;
        this.bio = bio;
        this.questionCount = questionCount;
        this.createdAt = createdAt;
    }

    public int getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public String getFullName() {
        return fullName;
    }

    public String getBio() {
        return bio;
    }

    public String getPicture() {
        return picture;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public int getQuestionCount() {
        return questionCount;
    }
}
