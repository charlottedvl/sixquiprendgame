package com.isep.sixquiprendgame;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class Rules extends Controller {
    @FXML
    private Button changeSceneButton;
    @FXML
    protected void passRules() {
        Stage stage = (Stage) changeSceneButton.getScene().getWindow();
        Controller.load("/views/Board.fxml", stage, player, aiPlayers);    }

}
