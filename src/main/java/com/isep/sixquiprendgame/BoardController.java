package com.isep.sixquiprendgame;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;



@Getter
@Setter
public class BoardController {

    private Setup setup;
    private HumanPlayer player;
    private AiPlayer ai;
    private ArrayList<Card> deck;
    @FXML
    private VBox view;
    @FXML
    private Label oxHeadNumberIa;
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

    private Serie stackOne;
    private Serie stackTwo;
    private Serie stackThree;
    private Serie stackFour;
    private ArrayList<Serie> stacks;

    public BoardController() {
        this.setup = new Setup();
        this.deck = setup.createCards();
        this.stackOne = new Serie(deck);
        this.stackTwo = new Serie(deck);
        this.stackThree = new Serie(deck);
        this.stackFour = new Serie(deck);
        this.stacks = new ArrayList<>();
        stacks.add(stackOne);
        stacks.add(stackTwo);
        stacks.add(stackThree);
        stacks.add(stackFour);
    }

    public void showInformation(HumanPlayer player, AiPlayer ai) {
        this.player = player;
        this.ai = ai;
        setup.distributionCard(player, deck);
        Collections.sort(player.getHand());
        setup.distributionCard(ai, deck);
    }

    public void setOxHeadNumber(Player player, int numberOfOxHeads) {
        Label oxHead = (player instanceof HumanPlayer) ? oxHeadNumber : oxHeadNumberIa;
        oxHead.setText(Integer.toString(numberOfOxHeads));
    }
    @FXML
    public void playCard(MouseEvent event) {
        Pane clickedPane = (Pane) event.getSource();
        String id = clickedPane.getId();
        System.out.println(id);
        String newStr = id.substring(4);
        int number = Integer.parseInt(newStr);
        Card card = this.player.getHand().get(number-1);
        System.out.println(card.getNumber());
        this.setCardOnBoard(card, this.player);
        showCardHand(this.player);
        showCardHand(this.ai);
        showCardsStack(this.stackOne.getStack(),1);
        showCardsStack(this.stackTwo.getStack(),2);
        showCardsStack(this.stackThree.getStack(),3);
        showCardsStack(this.stackFour.getStack(),4);
    }

    public void showCardsStack(ArrayList<Card> stack, int stackNumber) {
        HBox stackHBox = getStackByNumber(stackNumber);
        for (int i = 0; i < stack.size(); i++) {
            setCardNumbers(stackHBox, i, stack.get(i));
            Pane cardPane = (Pane) stackHBox.getChildren().get(i);
            cardPane.setVisible(true);
            cardPane.setManaged(true);
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
                setCardNumbers(handContainer, i, playerCards.get(i));
            }
        }
        for (int i = playerCards.size(); i < 10; i++) {
            Pane cardPane = (Pane) handContainer.getChildren().get(i);
            cardPane.setVisible(false);
            cardPane.setManaged(false);
        }
    }

    @FXML
    public void setCardOnBoard(Card card, Player player) {
        int number = card.getNumber();
        int indexSerie = -1;
        int actualSerie = -1;
        int minimumDifference = Integer.MAX_VALUE;
        System.out.println("max value " + minimumDifference);
        for (Serie serie : stacks){
            actualSerie++;
            if (number > serie.getLastCard().getNumber()){
                int difference = Math.abs(number - serie.getLastCard().getNumber());
                if (difference < minimumDifference) {
                    minimumDifference = difference;
                    indexSerie = actualSerie;
                }
            }
        }
        if (indexSerie == -1) {
            System.out.println("inférieur à toutes les cartes");
        } else {
            Serie serie = stacks.get(indexSerie);
            if (serie.testNumber()){
                serie.getStack().add(card);
                serie.setLastCard(card);
            } else {
                player.setTotalOxHead(getPlayer().getTotalOxHead() + serie.getTotalHead());
                serie.getStack().clear();
                serie.setLastCard(card);
                serie.getStack().add(card);
            }
        }
        this.player.getHand().remove(card);
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

        public void setCardNumbers (HBox deck,int number, Card card){
            Pane cardPane = (Pane) deck.getChildren().get(number);
            Label cardNumberLabel = (Label) cardPane.getChildren().get(0);
            cardNumberLabel.setText(Integer.toString(card.getNumber()));
            Label oxHeadNumberLabel = (Label) cardPane.getChildren().get(1);
            oxHeadNumberLabel.setText(Integer.toString(card.getOxHead()));
        }

}

