package com.isep.sixquiprendgame;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class Rules extends Controller {
    @FXML
    private Button changeSceneButton;
    @FXML
    protected void passRules() {
        HumanPlayer player = new HumanPlayer("defaultName");
        AiPlayer ai = new AiPlayer();
        Stage stage = (Stage) changeSceneButton.getScene().getWindow();
        Controller.load("/views/hello-view.fxml", stage, player, ai);    }

}
