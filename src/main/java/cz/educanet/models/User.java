package cz.educanet.models;

import org.apache.commons.codec.digest.DigestUtils;

import java.time.LocalDateTime;
import java.util.Random;

public class User {

    private int userId = -1;
    private String email = "";
    private String hashedPassword = "";
    private String fullName = "";
    private String bio = "";
    private String picture = "";
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = null;
    private String salt = "";
    private int questionCount = -1;

    public User(int userId, String email, String hashedPassword, String fullName, String bio, String picture, LocalDateTime createdAt, LocalDateTime updatedAt, String salt) {
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

    public User(String name, String email, String unHashedPassword) {
        this.fullName = name;
        this.email = email;
        this.hashedPassword = this.hash(unHashedPassword);
    }

    private String hash(String unHashedPassword) {
        return DigestUtils.sha256Hex(unHashedPassword + this.generateSalt());
    }

    private String generateSalt() {
        int length = 32;

        Random rn = new Random();

        StringBuilder salt = new StringBuilder();

        for (int i = 0; i < length; i++) {
            salt.append((char) rn.nextInt());
        }

        this.salt = salt.toString();

        return salt.toString();
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

    public String getSalt() {
        return salt;
    }
}
