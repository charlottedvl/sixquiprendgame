package com.isep.sixquiprendgame;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class BoardController {

    private HumanPlayer player;
    private AiPlayer ai;

    @FXML
    private VBox view;
    @FXML
    private Label oxHeadNumber;
    @FXML
    private Pane s1c1;
    @FXML
    private Pane s1c2;
    @FXML
    private Pane s1c3;
    @FXML
    private Pane s1c4;
    @FXML
    private Pane s1c5;
    @FXML
    private Pane s2c1;
    @FXML
    private Pane s2c2;
    @FXML
    private Pane s2c3;
    @FXML
    private Pane s2c4;
    @FXML
    private Pane s2c5;
    @FXML
    private Pane s3c1;
    @FXML
    private Pane s3c2;
    @FXML
    private Pane s3c3;
    @FXML
    private Pane s3c4;
    @FXML
    private Pane s3c5;
    @FXML
    private Pane s4c1;
    @FXML
    private Pane s4c2;
    @FXML
    private Pane s4c3;
    @FXML
    private Pane s4c4;
    @FXML
    private Pane s4c5;

    public void showInformation(HumanPlayer player, AiPlayer ai){
        this.player = player;
        this.ai = ai;
    }

    public void updateOxHeadNumber(int numberOfOxHeads) {
        oxHeadNumber.setText(Integer.toString(numberOfOxHeads));
    }

    public void showCardsStack(int stackNumber, int numberOfCardsToShow){
        Pane[] cardPanes = null;
        switch (stackNumber){
            case 1:
                cardPanes = new Pane[]{s1c1, s1c2, s1c3, s1c4, s1c5};
                break;
            case 2:
                cardPanes = new Pane[]{s2c1, s2c2, s2c3, s2c4, s2c5};
                break;
            case 3:
                cardPanes = new Pane[]{s3c1, s3c2, s3c3, s3c4, s3c5};
                break;
            case 4:
                cardPanes = new Pane[]{s4c1, s4c2, s4c3, s4c4, s4c5};
                break;
        }
        for (int card = numberOfCardsToShow +1; card <= 5; card++){
            cardPanes[card-1].setVisible(false);
            cardPanes[card-1].setManaged(false);
        }
    }
}
