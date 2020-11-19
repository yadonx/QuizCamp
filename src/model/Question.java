package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Question implements Serializable {
    private final String questionText;
    private final List<String> answers = new ArrayList<>();

    final String correctAnswer;

    Question(String questionText, String answer1, String answer2, String answer3, String answer4) {
        this.questionText = questionText;
        this.answers.add(answer1);
        this.answers.add(answer2);
        this.answers.add(answer3);
        this.answers.add(answer4);
        correctAnswer = answer1;
    }

    public String getQuestionText() {
        return questionText;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
}
