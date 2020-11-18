package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Question {
    final String questionText;
    final List<String> answerList = new ArrayList<>();
    final String correctAnswer;

    Question(String questionText, String answer1, String answer2, String answer3, String answer4) {
        this.questionText = questionText;
        answerList.add(answer1);
        answerList.add(answer2);
        answerList.add(answer3);
        answerList.add(answer4);
        correctAnswer = answer1;
    }

    public List<String> getShuffledAnswers(){
        Collections.shuffle(answerList);
        return answerList;
    }
}
