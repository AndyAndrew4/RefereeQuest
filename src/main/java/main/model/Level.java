package main.model;

import java.util.ArrayList;
import java.util.List;

public class Level {
    private List<Question> questions;

    public Level() {
        this.questions = new ArrayList<>();
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public List<Question> getQuestions() {
        return questions;
    }
}
