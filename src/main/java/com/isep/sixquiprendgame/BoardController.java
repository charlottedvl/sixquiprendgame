package com.isep.sixquiprendgame;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
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
    @FXML
    private Stage stage;
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

    public void initiateGame(HumanPlayer player, AiPlayer ai) {
        Setup setup = this.setup;
        ArrayList<Card> deck = this.getDeck();
        setup.distributionCard(player, deck);
        Collections.sort(player.getHand());
        setup.distributionCard(ai, deck);

        this.showInformation(player, ai);
        this.showCardHand(this.getPlayer());
        this.showCardsStack(this.getStacks().get(0).getStack(), 1);
        this.showCardsStack(this.getStacks().get(1).getStack(), 2);
        this.showCardsStack(this.getStacks().get(2).getStack(), 3);
        this.showCardsStack(this.getStacks().get(3).getStack(), 4);
    }

    public void showInformation(HumanPlayer player, AiPlayer ai) {
        this.player = player;
        this.ai = ai;
        //setup.distributionCard(player, deck); Déjà dans le HelloController (il faut choisir entre les 2)
        //Collections.sort(player.getHand());
        //setup.distributionCard(ai, deck);
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
            setOxHeadNumber(player, player.getTotalOxHead());
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

    public Card aiPlays() {
        Random random = new Random();
        Card card = this.ai.getHand().get(random.nextInt(this.ai.getHand().size()));
        this.ai.getHand().remove(card);
        return card;
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
                    initiateGame(player, ai);
                } else if (buttonType == buttonTypeNo) {
                    endGame();
                }
            });
        } else {
            endGame();
        }
    }
    public void endGame() {
        try {
            // Chargement de la nouvelle vue
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Tool.class.getResource("/views/FinalScreen.fxml"));
            view = (VBox) loader.load();
            // Chercher le controller du board
            FinalScreenController controller = loader.getController();
            // Remplacement de la vue actuelle par la nouvelle
            Scene scene = new Scene(view);
            // Afficher la nouvelle scène sur le stage
            stage.setScene(scene);
            stage.show();

            controller.showInformation(player, ai);
            controller.setClassement();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

