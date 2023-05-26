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
import java.util.List;

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
        int numberIndex = "hand".length();
        char numberChar = id.charAt(numberIndex);
        int number = Character.getNumericValue(numberChar);
        Card card = this.player.getHand().get(number-1);
        int value = card.getNumber();
        int oxHead = card.getOxHead();
        System.out.println(value);
        System.out.println(oxHead);

    }
}
