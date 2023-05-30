package com.isep.sixquiprendgame;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;
import java.util.Random;


@Getter
@Setter
public class BoardController extends Controller {

    private Setup setup;

    private ArrayList<Card> deck;
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
    private ArrayList<Serie> stacks;

    public BoardController() {
        this.setup = new Setup();
        this.deck = setup.createCards();
        Serie stackOne = new Serie(deck);
        Serie stackTwo = new Serie(deck);
        Serie stackThree = new Serie(deck);
        Serie stackFour = new Serie(deck);
        this.stacks = new ArrayList<>();
        stacks.add(stackOne);
        stacks.add(stackTwo);
        stacks.add(stackThree);
        stacks.add(stackFour);
    }

    public void initiateGame(HumanPlayer player, AiPlayer ai, Stage stage) {
        Setup setup = this.setup;
        ArrayList<Card> deck = this.getDeck();
        setup.distributionCard(player, deck);
        setup.distributionCard(ai, deck);

        this.showCardHand(this.getPlayer());
        this.showCardsStack(this.getStacks().get(0).getStack(), 1);
        this.showCardsStack(this.getStacks().get(1).getStack(), 2);
        this.showCardsStack(this.getStacks().get(2).getStack(), 3);
        this.showCardsStack(this.getStacks().get(3).getStack(), 4);
        this.setOxHeadNumber(player, player.getTotalOxHead());
    }


    public void setOxHeadNumber(Player player, int numberOfOxHeads) {
        oxHeadNumber.setText(Integer.toString(numberOfOxHeads));
    }
    @FXML
    public void playCard(MouseEvent event) {
        Pane clickedPane = (Pane) event.getSource();
        String id = clickedPane.getId();
        System.out.println(id);
        String newStr = id.substring(4);
        int number = Integer.parseInt(newStr);
        Card card = this.player.getHand().get(number - 1);
        Card aiCard = this.aiPlays();
        this.determineMinimum (card, aiCard);
        showCardHand(this.player);
        showCardHand(this.ai);
        showCardsStack(this.stacks.get(0).getStack(), 1);
        showCardsStack(this.stacks.get(1).getStack(), 2);
        showCardsStack(this.stacks.get(2).getStack(), 3);
        showCardsStack(this.stacks.get(3).getStack(), 4);
        if (player.getHand().size() == 0) {
            this.newPlay();
        }
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
        ArrayList<Card> playerCards = player.getHand();
            for (int i = 0; i < playerCards.size(); i++) {
                setCardNumbers(hand, i, playerCards.get(i));
            }
        for (int i = playerCards.size(); i < 10; i++) {
            Pane cardPane = (Pane) hand.getChildren().get(i);
            cardPane.setVisible(false);
            cardPane.setManaged(false);
        }
    }

    @FXML
    public void setCardOnBoard(Card card, Player player) {

        int indexSerie = getMinimumStack(card);
        if (indexSerie == -1 && player instanceof HumanPlayer) {
            chooseStackToTake(player, card);
        } else if (indexSerie == -1 && player instanceof AiPlayer){
            minimumStack(card);
        } else {
            Serie serie = stacks.get(indexSerie);
            if (serie.testNumber()){
                serie.getStack().add(card);
                serie.setLastCard(card);
                serie.setTotalHead(serie.getTotalHead()+card.getOxHead());
            } else {
                takeCardFromStack(player, serie, card);
            }
        }
        this.player.getHand().remove(card);
    }

    public int getMinimumStack(Card card){
        int number = card.getNumber();
        int indexSerie = -1;
        int actualSerie = -1;
        int minimumDifference = Integer.MAX_VALUE;
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
        return indexSerie;
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
        ImageView image = (ImageView) cardPane.getChildren().get(0);
        /*Label cardNumberLabel = (Label) cardPane.getChildren().get(0);
        cardNumberLabel.setText(Integer.toString(card.getNumber()));
        Label oxHeadNumberLabel = (Label) cardPane.getChildren().get(1);
        oxHeadNumberLabel.setText(Integer.toString(card.getOxHead()));*/
        String imageUrl = "/card/" + Integer.toString(card.getNumber()) + ".png";
        System.out.println(imageUrl);
        image.setImage(new Image(getClass().getResource(imageUrl).toExternalForm()));
    }

    public void chooseStackToTake(Player player, Card card) {
        // Créer une liste des noms des stacks disponibles
        ArrayList<String> stackNames = new ArrayList<>();
        for (int i = 0; i < stacks.size(); i++) {
            stackNames.add("Stack " + (i + 1));
        }

        // Afficher une boîte de dialogue de choix pour le joueur
        ChoiceDialog<String> dialog = new ChoiceDialog<>(stackNames.get(0), stackNames);
        dialog.setTitle("Choisir un stack");
        dialog.setHeaderText("Sélectionnez le stack auquel vous voulez prendre la carte :");
        dialog.setContentText("Stack :");

        // Attendre la réponse du joueur
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(selectedStack -> {
            int stackNumber = Integer.parseInt(selectedStack.replaceAll("[\\D]", ""));
            Serie serie = getStackByNumberNoFXML(stackNumber);
            takeCardFromStack(player, serie, card);
        });
    }

    public void takeCardFromStack (Player player, Serie serie, Card card) {
        if (serie != null) {
            player.setTotalOxHead(getPlayer().getTotalOxHead() + serie.getTotalHead());
            if (player instanceof HumanPlayer){
                setOxHeadNumber(player, player.getTotalOxHead());
            }
            serie.getStack().clear();
            serie.setLastCard(card);
            serie.getStack().add(card);
            serie.setTotalHead(card.getOxHead());
        } else {
            System.out.println("Please enter a valid stack");
        }
    }

    private Serie getStackByNumberNoFXML(int stackNumber) {
        return switch (stackNumber) {
            case 1 -> stacks.get(0);
            case 2 -> stacks.get(1);
            case 3 -> stacks.get(2);
            case 4 -> stacks.get(3);
            default -> null;
        };
    }

    public Card aiPlays() { //repère

        for (Card card: this.ai.getHand()){ // suppr
            System.out.println(card.getNumber() + "  -  " + evaluateCard(card));
        }
        int size = this.ai.getHand().size();
        ArrayList<Integer> evaluation= new ArrayList<>();
        for (int i=0;i<size; i++ ){
            Card card = this.ai.getHand().get(i);
            evaluation.add(evaluateCard(card));
        }
        int index = evaluation.indexOf(Collections.max(evaluation));
        Card cardToPlay = this.ai.getHand().get(index);
        this.ai.getHand().remove(cardToPlay);

        System.out.println("carte choisie " + cardToPlay.getNumber());
        return cardToPlay;
    }

    public int evaluateCard(Card card) {
        int value = 0;
        int indexSerie = getMinimumStack(card);
        if (indexSerie<0){
            value = - (10 + card.getNumber());
        } else {
            Serie serie = stacks.get(indexSerie);
            int difference = card.getNumber() - serie.getLastCard().getNumber();
            int stackSize = serie.getStack().size();
            if (difference == 1) {
                value = 100;
            } else if (difference <5) {
                value = switch (stackSize) {
                    case 1 -> 95;
                    case 2 -> 90;
                    case 3 -> 80;
                    case 4 -> 70;
                    default -> value;
                };
            } else if(difference < 15) {
                value = switch (stackSize) {
                    case 1 -> 90;
                    case 2 -> 80;
                    case 3 -> 70;
                    case 4 -> 55;
                    default -> value;
                };
            } else if(difference < 30) {
                value = switch (stackSize) {
                    case 1 -> 80;
                    case 2 -> 70;
                    case 3 -> 60;
                    case 4 -> 40;
                    default -> value;
                };
            }else if(difference < 50) {
                value = switch (stackSize) {
                    case 1 -> 60;
                    case 2 -> 50;
                    case 3 -> 30;
                    case 4 -> 15;
                    default -> value;
                };
            }else if(difference < 70) {
                value = switch (stackSize) {
                    case 1 -> 20;
                    case 2 -> 10;
                    case 3, 4 -> 40;
                    default -> value;
                };
            }else {
                value = switch (stackSize) {
                    case 1 -> 0;
                    case 2 -> 10;
                    case 3, 4 -> 70;
                    default -> value;
                };
            }

        }

        return value;
    }

    public void determineMinimum (Card card, Card aiCard) {
        if (card.getNumber() < aiCard.getNumber()) {
            this.setCardOnBoard(card, this.player);
            this.setCardOnBoard(aiCard, this.ai);
        } else {
            this.setCardOnBoard(aiCard, this.ai);
            this.setCardOnBoard(card, this.player);
        }
    }

    public void minimumStack(Card card) {
        Serie minOxHeadSerie = this.stacks.get(0);
        int minOxHead = Integer.MAX_VALUE;
        for (Serie serie : stacks) {
            if (serie.getTotalHead() < minOxHead) {
                minOxHeadSerie = serie;
            }
        }
        takeCardFromStack (this.ai, minOxHeadSerie, card);
    }

    public void newPlay() {
        if (this.player.getTotalOxHead() < 66 && this.ai.getTotalOxHead() < 66) {
            // Afficher une boîte de dialogue demandant au joueur s'il veut jouer une autre manche
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Fin du jeu");
            alert.setHeaderText("La partie est terminée.");
            alert.setContentText("Voulez-vous jouer une autre manche ?");

            // Ajouter les boutons "Oui" et "Non" à la boîte de dialogue
            ButtonType buttonTypeYes = new ButtonType("Oui");
            ButtonType buttonTypeNo = new ButtonType("Non");
            alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

            // Attendre la réponse du joueur
            Optional<ButtonType> result = alert.showAndWait();
            result.ifPresent(buttonType -> {
                if (buttonType == buttonTypeYes) {
                    HumanPlayer player = this.player;
                    AiPlayer ai = this.ai;
                    Controller.load("/views/Board.fxml", this.stage, player, ai);
                } else if (buttonType == buttonTypeNo) {
                    endGame();
                }
            });
        } else {
            endGame();
        }
    }
    public void endGame() {
        Controller.load("/views/FinalScreen.fxml", stage, this.player, this.ai);
    }
}

