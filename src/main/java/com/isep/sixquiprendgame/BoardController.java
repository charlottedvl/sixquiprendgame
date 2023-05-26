package com.isep.sixquiprendgame;

import javafx.fxml.FXML;
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
    private HBox stack1;
    @FXML
    private HBox stack2;
    @FXML
    private HBox stack3;
    @FXML
    private HBox stack4;

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
        this.stacks = new ArrayList<Serie>();
        stacks.add(stackOne);
        stacks.add(stackTwo);
        stacks.add(stackThree);
        stacks.add(stackFour);
    }
    public void showInformation(HumanPlayer player, AiPlayer ai){
        this.player = player;
        this.ai = ai;
        setup.distributionCard(player, deck);
        Collections.sort(player.getHand());
        setup.distributionCard(ai, deck);
    }

    @FXML
    public void playCard(MouseEvent event) {
        Pane clickedPane = (Pane) event.getSource();
        String id = clickedPane.getId();
        System.out.println(id);
        String newStr = id.substring(4);
        int number = Integer.parseInt(newStr);
        System.out.println(number);
        Card card = this.player.getHand().get(number-1);
        System.out.println(card.getNumber());
        this.setCardOnBoard(card, this.player);
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
            if (serie.testNumber() == true){
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


}
