package com.isep.sixquiprendgame;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

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
            loader.setLocation(Controller.class.getResource(fxmlFile));
            VBox view = (VBox) loader.load();
            // Chercher le controller du board
            controller = loader.getController();
            controller.showInformation(player, ai, stage);
            if (controller instanceof BoardController) {
                ((BoardController) controller).initiateGame(player, ai, stage);
            } else if (controller instanceof FinalScreenController){
                ((FinalScreenController) controller).setClassement();
            }
            // Remplacement de la vue actuelle par la nouvelle
            Scene scene = new Scene(view);
            // Afficher la nouvelle scène sur le stage
            stage.setScene(scene);
            stage.show();

            Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
            stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
            stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);

        } catch (Exception e) {
            e.printStackTrace();
        }
        controller.setPlayer(player);
        controller.setAi(ai);
        controller.setStage(stage);
        return controller;
    }

    public void showInformation(HumanPlayer player, AiPlayer ai, Stage stage) {
        this.player = player;
        this.ai = ai;
        this.stage = stage;
    }
}

