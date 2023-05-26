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
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/views/Board.fxml"));
        Parent root = fxmlLoader.load();
        BoardController game = fxmlLoader.getController();


        //Je le fais manuellement pour l'instant
        game.setOxHeadNumber(49);
        game.showCardsStack(1,5);
        game.showCardsStack(2,5);
        game.showCardsStack(3,5);
        game.showCardsStack(4,5);
        HumanPlayer player = new HumanPlayer("player");
        AiPlayer ai = new AiPlayer();
        game.showCardHand(player, 10);
        game.showCardHand(ai, 10);


        Scene scene = new Scene(root, 780, 470);


        stage.setTitle("6 qui prend");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}