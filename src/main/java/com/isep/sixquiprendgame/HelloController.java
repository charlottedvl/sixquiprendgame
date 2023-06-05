package com.isep.sixquiprendgame;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class HelloController extends Controller {

    @FXML
    private Button changeSceneButton;

    @FXML
    private TextField nameField;
    @FXML
    private Slider aiNumber;


    @FXML
    protected void handleButtonAction() {
        String name = nameField.getText();
        HumanPlayer player = new HumanPlayer(name);
        int aiPlayerNumber = (int) aiNumber.getValue();
        AiPlayer[]  aiPlayers = new AiPlayer[aiPlayerNumber];
        for (int i = 0; i < aiPlayerNumber; i++) {
            aiPlayers[i] = new AiPlayer("Joueur "+ (i + 1));
        }
        Stage stage = (Stage) changeSceneButton.getScene().getWindow();
        Controller controller = Controller.load("/views/Rules.fxml", stage, player, aiPlayers);
    }





    @FXML
    protected void onHelloButtonMouseEntered() {
        changeSceneButton.setText("Clique ici !");
    }
    @FXML
    protected void onHelloButtonMouseExited() {
        changeSceneButton.setText("Start Game");
    }


}