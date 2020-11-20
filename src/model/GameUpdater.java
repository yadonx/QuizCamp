package model;

import java.io.Serializable;

/**
 * Created by Emil Johansson
 * Date: 2020-11-20
 * Time: 11:29
 * Project: QuizCamp
 * Package: model
 */
public class GameUpdater implements Serializable {
    Category category;
    int clientScore = 0;
    int opponentScore = 0;
    private boolean ready;
    private int id;
    public static final int CLIENT_1 = 1;
    public static final int CLIENT_2 = 2;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClientScore() {
        return clientScore;
    }

    public void setClientScore(int clientScore) {
        this.clientScore = clientScore;
    }

    public int getOpponentScore() {
        return opponentScore;
    }

    public void setOpponentScore(int opponentScore) {
        this.opponentScore = opponentScore;
    }

    public boolean ready(){
        return ready;
    }

    public void setReady(boolean ready){
        this.ready = ready;
    }

    public void setCategory(Category category){
        this.category = category;
    }

    public Category getCategory(){
        return category;
    }

    public void increaseClientScore(){
        clientScore++;
    }
}