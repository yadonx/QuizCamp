package model;

import java.io.Serializable;

public class Answer implements Serializable {
    private final String selectedAnswer;

    public Answer(String selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
    }

    public String getSelectedAnswer() {
        return selectedAnswer;
    }
}
