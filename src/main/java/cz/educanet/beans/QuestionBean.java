package cz.educanet.beans;

import cz.educanet.repositories.QuestionRepository;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.annotation.ManagedProperty;
import jakarta.inject.Named;

import java.io.Serializable;
import java.sql.SQLException;

@Named
@SessionScoped
public class QuestionBean implements Serializable {

    QuestionRepository questionRepository = new QuestionRepository();

    private String text = "";

    @ManagedProperty(value = "#{UserBean}")
    UserBean userBean;

    @ManagedProperty(value = "#{DetailBean}")
    DetailBean detailBean;
    public DetailBean getDetailBean() {
        return detailBean;
    }

    public void setDetailBean(DetailBean detailBean) {
        this.detailBean = detailBean;
    }

    public void ask() throws SQLException {
        this.createQuestion(this.text, userBean.getLoggedUser(), detailBean.getCurrentId());
    }

    public void createQuestion(String text, int authorId, int targetId) throws SQLException {
        questionRepository.addQuestion(text, authorId, targetId);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
