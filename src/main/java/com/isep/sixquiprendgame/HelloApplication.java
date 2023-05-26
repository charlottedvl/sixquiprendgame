package com.isep.sixquiprendgame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;


public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/views/Board.fxml"));
        Parent root = fxmlLoader.load();
        BoardController game = fxmlLoader.getController();


        //Je le fais manuellement pour l'instant
        HumanPlayer player = new HumanPlayer("player");
        AiPlayer ai = new AiPlayer();
        Board board = new Board();
        game.setPlayer(player);
        game.setAi(ai);

        player.hand.add(new Card(27, 3));
        player.hand.add(new Card(102, 1));
        player.hand.add(new Card(6, 4));
        player.hand.add(new Card(89, 7));
        player.hand.add(new Card(12, 1));
        player.hand.add(new Card(3, 9));
        player.hand.add(new Card(45, 0));
        player.hand.add(new Card(34, 1));

        ai.hand.add(new Card(12, 1));
        ai.hand.add(new Card(3, 9));
        ai.hand.add(new Card(45, 0));
        ai.hand.add(new Card(34, 1));

        ArrayList<Card> stack1 = game.getStackOne().getStack();
        ArrayList<Card> stack2 = game.getStackTwo().getStack();
        ArrayList<Card> stack3 = game.getStackThree().getStack();
        ArrayList<Card> stack4 = game.getStackFour().getStack();

        stack1.add(new Card(18,6));
        stack1.add(new Card(15,2));
        stack1.add(new Card(28,1));
        stack1.add(new Card(87,3));

        stack2.add(new Card(8,5));
        stack2.add(new Card(65,4));
        stack2.add(new Card(99,3));

        stack3.add(new Card(18,6));
        stack3.add(new Card(18,6));
        stack3.add(new Card(18,6));
        stack3.add(new Card(19,6));

        stack4.add(new Card(18,6));

        game.setOxHeadNumber(player,49);
        game.setOxHeadNumber(ai,27);
        game.showCardsStack(stack1,1);
        game.showCardsStack(stack2,2);
        game.showCardsStack(stack3,3);
        game.showCardsStack(stack4,4);
        game.showCardHand(player);
        game.showCardHand(ai);


        Scene scene = new Scene(root, 780, 470);


        stage.setTitle("6 qui prend");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}