package com.isep.sixquiprendgame;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class GetNameController {

    @FXML
    private Button changeSceneButton;
    @FXML
    private TextField nameField;
    @FXML
    private VBox view;
    @FXML
    protected void confirmButtonAction() {
        String filename = "GetName.fxml";
        String name = nameField.getText();
        HumanPlayer player = new HumanPlayer(name);
        Tool.load(filename, view, changeSceneButton);
    }
}
