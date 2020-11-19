package server;

import model.*;


import java.io.IOException;
import java.util.List;

public class Game extends Thread {

    private final Server player1;
    private final Server player2;
    private int score1, score2;

    private List<Category> questions;
    private int numberOfRounds = 2;
    private int numberOfQuestionsInRound = 2;//TODO: should be able to set through properties file


    public Game(Server player1, Server player2) {
        this.player1 = player1;
        this.player2 = player2;
        Answer answer1 = player1.chooseCategory(new QuestionOfCategory("A", "B", "C", "D"));
        System.out.println(answer1.getSelectedAnswer());
        Answer answer2 = player2.chooseCategory(new QuestionOfCategory("A1", "B1", "C1", "D1"));
        System.out.println(answer2.getSelectedAnswer());
        QuestionGenerator questionGenerator = new QuestionGenerator();
        questions = questionGenerator.getShuffledCategories(4);
    }

    @Override
    public void run() {

    }
}
