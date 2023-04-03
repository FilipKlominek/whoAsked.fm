package cz.educanet.beans;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;

@Named
@SessionScoped
public class QuestionBean implements Serializable {

    private String text = "";


    public void createQuestion() {


        //TODO: QuestionRepository
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
