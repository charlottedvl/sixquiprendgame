package com.isep.sixquiprendgame;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Tool {
    public static void load(String fxmlFile, VBox view, Button changeSceneButton) {
        try {
            // Chargement de la nouvelle vue
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Tool.class.getResource("/views/" + fxmlFile));
            view = (VBox) loader.load();

            // Chercher le controller du board
            //BoardController boardController = loader.getController();
            //boardController.showInformation()
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
}
