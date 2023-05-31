package com.isep.sixquiprendgame;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;

public class HelloController extends Controller {

    @FXML
    private Button changeSceneButton;

    @FXML
    private TextField nameField;


    @FXML
    protected void handleButtonAction() {
        String name = nameField.getText();
        this.player.setName(name);
        Stage stage = (Stage) changeSceneButton.getScene().getWindow();
        Controller controller = Controller.load("/views/Board.fxml", stage, player, ai);
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