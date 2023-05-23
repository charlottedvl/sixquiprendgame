package com.isep.sixquiprendgame;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class HelloController {

    @FXML
    private Button changeSceneButton;

    @FXML
    private VBox view;

    @FXML
    protected void handleButtonAction() {
        String filename = "GetName.fxml";
        Tool.load(filename, view, changeSceneButton);
    }

    @FXML
    protected void onHelloButtonMouseEntered() {
        changeSceneButton.setText("Click me !");
    }
    @FXML
    protected void onHelloButtonMouseExited() {
        changeSceneButton.setText("Start Game");
    }
}