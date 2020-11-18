package model;

import java.io.Serializable;
import java.util.List;

public class Result implements Serializable {//TODO: add set score method
    private List<Integer> playerOneScores;
    private List<Integer> playerTwoScores;

    public List<Integer> getPlayerOneScores() {
        return playerOneScores;
    }

    public List<Integer> getPlayerTwoScores() {
        return playerTwoScores;
    }
}
