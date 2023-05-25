package com.isep.sixquiprendgame;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class PlayGame {
    @FXML
    private Label oxHeadNumber;

    public void updateOxHeadNumber(int numberOfOxHeads) {
        oxHeadNumber.setText(Integer.toString(numberOfOxHeads));
    }

}
