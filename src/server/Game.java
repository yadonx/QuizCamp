package server;


import model.Category;
import model.GameUpdater;
import model.Pair;


public class Game {


    private int score1, score2;
    private String clientName1, clientName2;

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
                System.out.println("[SERVER] receives from client1: " + input.toString());
                updater1 = (GameUpdater) input;
                if (clientName1 == null) {
                    clientName1 = updater1.getClientName();
                }
                updater1.setReady(true);
            } else if (((GameUpdater) input).getId() == GameUpdater.CLIENT_2) {
                System.out.println("[SERVER] receives from client2: " + input.toString());
                updater2 = (GameUpdater) input;
                if (clientName2 == null) {
                    clientName2 = updater2.getClientName();
                }
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

    public void updateCategories() {
        Category category = protocol.getCategory();
        updater1.setCategory(category);
        updater2.setCategory(category);
    }

    public void gameUpdate() {
        updater1.setOpponentName(clientName2);
        updater2.setOpponentName(clientName1);

        score1 = updater1.getClientScore();
        score2 = updater2.getClientScore();
        updater1.setOpponentScore(score2);
        updater2.setOpponentScore(score1);
        updater1.setReady(false);
        updater2.setReady(false);
        updateCategories();
        pair.writeGameUpdaters(updater1, updater2);
    }
}
