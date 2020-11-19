package client.controller;

import client.Client;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LobbyController {

    @FXML
    TextField name;
    @FXML
    Label wait;
    @FXML
    Button enter;

    private String player = "";
    private Client client;

    @FXML
    private void nameEntered() {
        player = name.getText();
        wait.setText("WAITING FOR PLAYER #2");
        client.connect();
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getPlayerName() {
        return player;
    }

}
