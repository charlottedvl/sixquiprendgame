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

        /*
        System.out.println(player.getHand().get(0).getNumber());
        System.out.println(player.getHand().get(1).getNumber());
        System.out.println(player.getHand().get(2).getNumber());
        System.out.println(player.getHand().get(3).getNumber());
        System.out.println(player.getHand().get(4).getNumber());
        System.out.println(player.getHand().get(5).getNumber());
        System.out.println(player.getHand().get(6).getNumber());
        System.out.println(player.getHand().get(7).getNumber());
        System.out.println(player.getHand().get(8).getNumber());
        System.out.println(player.getHand().get(9).getNumber());
        */

        try {
            // Chargement de la nouvelle vue
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Tool.class.getResource("/views/Board.fxml"));
            view = (VBox) loader.load();
            // Chercher le controller du board
            BoardController boardController = loader.getController();
            // Remplacement de la vue actuelle par la nouvelle
            Scene scene = new Scene(view);
            // obtenir la scène actuelle
            Stage stage = (Stage) changeSceneButton.getScene().getWindow();
            // afficher la nouvelle scène
            stage.setScene(scene);
            stage.show();

            ArrayList<Card> deck = boardController.getDeck();
            setup.distributionCard(player, deck);
            Collections.sort(player.getHand());
            setup.distributionCard(ai, deck);

            boardController.showInformation(player, ai);
            boardController.showCardHand(boardController.getPlayer());
            boardController.showCardsStack(boardController.getStackOne().getStack(), 1);
            boardController.showCardsStack(boardController.getStackTwo().getStack(), 2);
            boardController.showCardsStack(boardController.getStackThree().getStack(), 3);
            boardController.showCardsStack(boardController.getStackFour().getStack(), 4);

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