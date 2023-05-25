package com.isep.sixquiprendgame;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class BoardController {

    private HumanPlayer player;
    private AiPlayer ai;

    @FXML
    private VBox view;


    public void showInformation(HumanPlayer player, AiPlayer ai){
        this.player = player;
        this.ai = ai;
    }
}
