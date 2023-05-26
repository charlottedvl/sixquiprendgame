package com.isep.sixquiprendgame;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



public class HelloController {

    @FXML
    private Button changeSceneButton;

    @FXML
    private TextField nameField;

    @FXML
    private VBox view;

    @FXML
    protected void handleButtonAction() {
        String name = nameField.getText();
        HumanPlayer player = new HumanPlayer(name);
        AiPlayer ai = new AiPlayer();
        Setup setup = new Setup();
        try {
            // Chargement de la nouvelle vue
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Tool.class.getResource("/views/Board.fxml"));
            view = (VBox) loader.load();
            // Chercher le controller du board
            BoardController boardController = loader.getController();
            boardController.showInformation(player, ai);
            // Remplacement de la vue actuelle par la nouvelle
            Scene scene = new Scene(view);
            // obtenir la scène actuelle
            Stage stage = (Stage) changeSceneButton.getScene().getWindow();
            // afficher la nouvelle scène
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
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