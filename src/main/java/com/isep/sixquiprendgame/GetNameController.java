package com.isep.sixquiprendgame;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class GetNameController {

    @FXML
    private Button changeSceneButton;

    @FXML
    private VBox view;
    @FXML
    protected void confirmButtonAction() {
        String filename = "GetName.fxml";
        Tool.load(filename, view, changeSceneButton);
    }
}
