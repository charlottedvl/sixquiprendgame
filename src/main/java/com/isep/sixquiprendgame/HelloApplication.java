package com.isep.sixquiprendgame;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;


public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Board.fxml"));
        Parent root = fxmlLoader.load();
        PlayGame game = fxmlLoader.getController();

        int numberOfOxHeads = 20;
        game.updateOxHeadNumber(numberOfOxHeads);

        Scene scene = new Scene(root, 780, 470);

        stage.setTitle("6 qui prend");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}