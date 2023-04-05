package cz.educanet.models;

import java.time.LocalDateTime;

public class Question {
    private int questionId = -1;
    private String question = "";
    private String answer = "";
    private int authorId = -1;
    private int targetId = -1;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = null;

    public Question(String question, int authorId, int targetId) {
        this.question = question;
        this.authorId = authorId;
        this.targetId = targetId;
    }

    public Question(int questionId, String question, String answer, int authorId, int targetId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.questionId = questionId;
        this.question = question;
        this.answer = answer;
        this.authorId = authorId;
        this.targetId = targetId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getQuestionId() {
        return questionId;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public int getAuthorId() {
        return authorId;
    }

    public int getTargetId() {
        return targetId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
