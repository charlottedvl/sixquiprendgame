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
import java.util.List;

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
        ArrayList<Card> deck = setup.createCards();
        setup.distributionCard(player, deck);
        Collections.sort(player.getHand());
        System.out.println(player.getHand().get(1).getNumber());
        System.out.println(player.getHand().get(2).getNumber());
        System.out.println(player.getHand().get(3).getNumber());
        System.out.println(player.getHand().get(4).getNumber());
        System.out.println(player.getHand().get(5).getNumber());
        System.out.println(player.getHand().get(6).getNumber());
        System.out.println(player.getHand().get(7).getNumber());
        System.out.println(player.getHand().get(8).getNumber());
        System.out.println(player.getHand().get(9).getNumber());
        System.out.println(player.getHand().get(0).getNumber());
        setup.distributionCard(ai, deck);
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