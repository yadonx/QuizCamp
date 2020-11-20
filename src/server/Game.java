package server;


import model.GameUpdater;
import model.Pair;


public class Game {


    private int score1, score2;

    private Pair pair;
    private GameUpdater updater1 = new GameUpdater();
    private GameUpdater updater2 = new GameUpdater();
    private GameProtocol protocol = new GameProtocol();


    public Game(Pair pair) {
        this.pair = pair;
        updater1.setId(GameUpdater.CLIENT_1);
        updater2.setId(GameUpdater.CLIENT_2);
    }

    public void checkInputObject(Object input) {
        if (input instanceof String) {
            System.out.println(input);
            System.out.println("String");
            pair.writeToClients(input);
        } else if (input instanceof GameUpdater) {
            if (((GameUpdater) input).getId() == GameUpdater.CLIENT_1) {
                updater1 = (GameUpdater) input;
                updater1.setReady(true);
            } else if (((GameUpdater) input).getId() == GameUpdater.CLIENT_2) {
                updater2 = (GameUpdater) input;
                updater2.setReady(true);
            }

            if (updater1.ready() && updater2.ready()) {
                gameUpdate();
            }

        }

    }

    public void startGame() {
        pair.writeGameUpdaters(updater1, updater2);
    }

    public void gameUpdate() {
        score1 = updater1.getClientScore();
        score2 = updater2.getClientScore();
        updater1.setOpponentScore(score2);
        updater2.setOpponentScore(score1);
        updater1.setReady(false);
        updater2.setReady(false);
        pair.writeGameUpdaters(updater1, updater2);

    }
}
