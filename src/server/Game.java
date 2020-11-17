package server;

import model.Category;
import model.QuestionGenerator;



import java.io.ObjectOutputStream;
import java.util.List;

public class Game {

    private ObjectOutputStream client1Out;
    private ObjectOutputStream client2Out;
    private int score1, score2;

    private List<Category> questions;
    private List<ObjectOutputStream> pair;


    public Game(List<ObjectOutputStream> pair) {
        this.pair = pair;

        client1Out = pair.get(0);
        client2Out = pair.get(1);
        QuestionGenerator questionGenerator = new QuestionGenerator();
        questions = questionGenerator.getShuffledCategories(3);
    }



}
