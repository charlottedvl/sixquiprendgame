package com.isep.sixquiprendgame;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Controller {

    @FXML
    protected VBox view;

    protected HumanPlayer player;
    protected AiPlayer ai;
    protected Stage stage;


    public static Controller load(String fxmlFile, Stage stage, HumanPlayer player, AiPlayer ai) {
        Controller controller = null;
        try {
            // Chargement de la nouvelle vue
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Tool.class.getResource(fxmlFile));
            VBox view = (VBox) loader.load();
            // Chercher le controller du board
            controller = loader.getController();
            if (controller instanceof BoardController) {
                ((BoardController) controller).initiateGame(player, ai, stage);
            }
            // Remplacement de la vue actuelle par la nouvelle
            Scene scene = new Scene(view);
            // Afficher la nouvelle scène sur le stage
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        controller.setPlayer(player);
        controller.setAi(ai);
        controller.setStage(stage);
        return controller;
    }
}

