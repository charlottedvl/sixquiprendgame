package com.isep.sixquiprendgame;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class FinalScreenController {
    @FXML
    private VBox view;

    private HumanPlayer player;
    private AiPlayer ai;

    public void showInformation(HumanPlayer player, AiPlayer ai) {
        this.player = player;
        this.ai = ai;
    }
}
