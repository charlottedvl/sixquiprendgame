package com.isep.sixquiprendgame;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class BoardController {

    public Pane hand6;
    private HumanPlayer player;
    private AiPlayer ai;

    @FXML
    private VBox view;
    @FXML
    private Label oxHeadNumber;
    @FXML
    private Label oxHeadNumberIa;
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

    public void showInformation(HumanPlayer player, AiPlayer ai) {
        this.player = player;
        this.ai = ai;
    }

    public void setOxHeadNumber(Player player, int numberOfOxHeads) {
        Label oxHead = (player instanceof HumanPlayer) ? oxHeadNumber : oxHeadNumberIa;
        oxHead.setText(Integer.toString(numberOfOxHeads));
    }

    public void showCardsStack(ArrayList<Card> stack, int stackNumber) {
        HBox stackHBox = getStackByNumber(stackNumber);
        for (int i = 0; i < stack.size(); i++) {
            setCardNumbers(stackHBox, i, stack.get(i).getNumber(), stack.get(i).getOxHead());
        }
        for (int i = stack.size(); i < 5; i++) {
            Pane cardPane = (Pane) stackHBox.getChildren().get(i);
            cardPane.setVisible(false);
            cardPane.setManaged(false);
        }
    }

    public void showCardHand(Player player) {
        HBox handContainer = (player instanceof HumanPlayer) ? hand : otherHand;
        ArrayList<Card> playerCards = player.getHand();
        if (handContainer == hand) {
            for (int i = 0; i < playerCards.size(); i++) {
                setCardNumbers(handContainer, i, playerCards.get(i).getNumber(), playerCards.get(i).getOxHead());
            }
        }
        for (int i = playerCards.size(); i < 10; i++) {
            Pane cardPane = (Pane) handContainer.getChildren().get(i);
            cardPane.setVisible(false);
            cardPane.setManaged(false);
        }
    }

    private HBox getStackByNumber(int stackNumber) {
        return switch (stackNumber) {
            case 1 -> stack1;
            case 2 -> stack2;
            case 3 -> stack3;
            case 4 -> stack4;
            default -> null;
        };
    }

        public void setCardNumbers (HBox deck,int number, int cardNumber, int oxHeadNumber){
            Pane card = (Pane) deck.getChildren().get(number);
            Label cardNumberLabel = (Label) card.getChildren().get(0);
            cardNumberLabel.setText(Integer.toString(cardNumber));
            Label oxHeadNumberLabel = (Label) card.getChildren().get(1);
            oxHeadNumberLabel.setText(Integer.toString(oxHeadNumber));
        }

    }

