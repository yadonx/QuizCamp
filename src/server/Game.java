package server;

import model.Category;
import model.QuestionGenerator;


import java.util.List;

public class Game extends Thread {

    private final Server player1;//TODO:close servers at the end of the game
    private final Server player2;
    private int score1, score2;

    private List<Category> questions;
    private int numberOfRounds = 2;
    private int numberOfQuestionsInRound = 2;//TODO: should be able to set through properties file


    public Game(Server player1, Server player2) {
        this.player1 = player1;
        this.player2 = player2;
        QuestionGenerator questionGenerator = new QuestionGenerator();
        questions = questionGenerator.getShuffledCategories(3);
    }

    @Override
    public void run() {
        //loop communication between game and server
/*        for (int round = 1; round <= numberOfRounds; round++) {//1we ask player to select category

            Answer selectCategory;
            if (round % 2 == 1) {//mode is odd 1 player, mode even 2 player chooses
                selectCategory = player1.chooseCategory(new QuestionofCategory("a", "b", "c"));
            } else {
                selectCategory = player2.chooseCategory(new QuestionofCategory("a", "b", "c");
            }
        }*/
    }
}
