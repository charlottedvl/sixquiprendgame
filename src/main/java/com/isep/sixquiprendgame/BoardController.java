package com.isep.sixquiprendgame;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
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
    private HBox stack1;
    @FXML
    private HBox stack2;
    @FXML
    private HBox stack3;
    @FXML
    private HBox stack4;
    @FXML
    private HBox hand;
    @FXML
    private HBox otherHand;

    public void showInformation(HumanPlayer player, AiPlayer ai){
        this.player = player;
        this.ai = ai;
    }

    public void setOxHeadNumber(int numberOfOxHeads) {
        oxHeadNumber.setText(Integer.toString(numberOfOxHeads));
    }

    public void showCardsStack(int stackNumber, int numberOfCardsToShow) {
        HBox stack = getStackByNumber(stackNumber);
        for (int card = numberOfCardsToShow + 1; card <= 5; card++) {
            Pane cardPane = (Pane) stack.getChildren().get(card - 1);
            cardPane.setVisible(false);
            cardPane.setManaged(false);
        }
    }
    public void showCardHand(Player player, int numberOfCardsToShow) {
        HBox handContainer = (player instanceof HumanPlayer) ? hand : otherHand;
        for (int card = numberOfCardsToShow + 1; card <= 10; card++) {
            Pane cardPane = (Pane) handContainer.getChildren().get(card - 1);
            cardPane.setVisible(false);
            cardPane.setManaged(false);
        }
    }

    private HBox getStackByNumber(int stackNumber) {
        switch (stackNumber) {
            case 1:
                return stack1;
            case 2:
                return stack2;
            case 3:
                return stack3;
            case 4:
                return stack4;
            default:
                throw new IllegalArgumentException("Invalid stack number: " + stackNumber);
        }
    }

}
