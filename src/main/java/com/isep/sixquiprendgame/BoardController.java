package com.isep.sixquiprendgame;

import java.util.Collections;
import javafx.fxml.FXML;
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
import lombok.Getter;
import lombok.Setter;

import java.util.*;


@Getter
@Setter
public class BoardController extends Controller {

    private Setup setup;

    private ArrayList<Card> deck;
    @FXML
    private Label oxHeadNumber;
    @FXML
    private Label oxHeadNumber1;
    @FXML
    private Label oxHeadNumber2;
    @FXML
    private Label oxHeadNumber3;
    @FXML
    private Label oxHeadNumber4;
    @FXML
    private Label oxHeadNumber5;
    @FXML
    private Label oxHeadNumber6;
    @FXML
    private Label oxHeadNumber7;
    @FXML
    private Label oxHeadNumber8;
    @FXML
    private Label oxHeadNumber9;

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
    private HBox aiPlayersView;
    @FXML
    private VBox player1;
    @FXML
    private VBox player2;
    @FXML
    private VBox player3;
    @FXML
    private VBox player4;
    @FXML
    private VBox player5;
    @FXML
    private VBox player6;
    @FXML
    private VBox player7;
    @FXML
    private VBox player8;
    @FXML
    private VBox player9;
    @FXML
    private Label playerName;
    private ArrayList<Serie> stacks;
    private Object Comparator;

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

    public void initiateGame(HumanPlayer player, AiPlayer[] aiPlayers) {
        Setup setup = this.setup;
        ArrayList<Card> deck = this.getDeck();
        setup.distributionCard(player, deck);
        for (AiPlayer ai: aiPlayers){
            setup.distributionCard(ai, deck);
        }
        this.showCardHand(player);
        this.showCardsStack(this.getStacks().get(0).getStack(), 1);
        this.showCardsStack(this.getStacks().get(1).getStack(), 2);
        this.showCardsStack(this.getStacks().get(2).getStack(), 3);
        this.showCardsStack(this.getStacks().get(3).getStack(), 4);
        this.setOxHeadNumber(player, player.getTotalOxHead());
        for (AiPlayer ai : aiPlayers) {
            this.setOxHeadNumber(ai, ai.getTotalOxHead());
        }
        for (int i = aiPlayers.length; i<9; i++){
            aiPlayersView.getChildren().get(i).setVisible(false);
            aiPlayersView.getChildren().get(i).setManaged(false);
        }
        playerName.setText(player.getName());
    }


    public void setOxHeadNumber(Player player, int numberOfOxHeads) {
        if (player instanceof HumanPlayer) {
            oxHeadNumber.setText(Integer.toString(numberOfOxHeads));
        } else {
            int playerIndex = Integer.parseInt(String.valueOf(player.getName().charAt(player.getName().length() - 1)));
            switch (playerIndex) {
                case 1 -> oxHeadNumber1.setText(Integer.toString(numberOfOxHeads));
                case 2 -> oxHeadNumber2.setText(Integer.toString(numberOfOxHeads));
                case 3 -> oxHeadNumber3.setText(Integer.toString(numberOfOxHeads));
                case 4 -> oxHeadNumber4.setText(Integer.toString(numberOfOxHeads));
                case 5 -> oxHeadNumber5.setText(Integer.toString(numberOfOxHeads));
                case 6 -> oxHeadNumber6.setText(Integer.toString(numberOfOxHeads));
                case 7 -> oxHeadNumber7.setText(Integer.toString(numberOfOxHeads));
                case 8 -> oxHeadNumber8.setText(Integer.toString(numberOfOxHeads));
                case 9 -> oxHeadNumber9.setText(Integer.toString(numberOfOxHeads));
            }
        }
    }
    @FXML
    public void playCard(MouseEvent event) {
        ImageView clickedImage = (ImageView) event.getSource();
        Pane clickedPane = (Pane) clickedImage.getParent();
        String id = clickedPane.getId();
        String newStr = id.substring(4);
        int number = Integer.parseInt(newStr);
        Card card = this.player.getHand().get(number - 1);
        Card[] aiCards = new Card[aiPlayers.length];
        for (int i = 0; i<aiCards.length; i++) {
            aiCards[i] = aiPlays(aiPlayers[i]);
        }
        this.determineMinimum (card, aiCards);
        showCardHand(this.player);
        showCardsStack(this.stacks.get(0).getStack(), 1);
        showCardsStack(this.stacks.get(1).getStack(), 2);
        showCardsStack(this.stacks.get(2).getStack(), 3);
        showCardsStack(this.stacks.get(3).getStack(), 4);
        if (player.getHand().size() == 0) {
            newPlay();
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

    public void showCardHand(HumanPlayer player) {
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
            minimumStack(card, player);
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
        String imageUrl = "/card/" + card.getNumber() + ".png";
        image.setImage(new Image(Objects.requireNonNull(getClass().getResource(imageUrl)).toExternalForm()));
    }

    public void chooseStackToTake(Player player, Card card) {
        // Créer une liste des noms des stacks disponibles
        ArrayList<String> stackNames = new ArrayList<>();
        for (int i = 0; i < stacks.size(); i++) {
            stackNames.add("Rangée " + (i + 1));
        }

        // Afficher une boîte de dialogue de choix pour le joueur
        ChoiceDialog<String> dialog = new ChoiceDialog<>(stackNames.get(0), stackNames);
        dialog.setTitle("Choisir une rangée de cartes");
        dialog.setHeaderText("Sélectionnez la rangée de cartes à récupérer :");
        dialog.setContentText("Rangée :");

        // Attendre la réponse du joueur
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(selectedStack -> {
            int stackNumber = Integer.parseInt(selectedStack.replaceAll("\\D", ""));
            Serie serie = getStackByNumberNoFXML(stackNumber);
            takeCardFromStack(player, serie, card);
        });
    }

    public void takeCardFromStack (Player player, Serie serie, Card card) {
        if (serie != null) {
            player.setTotalOxHead(getPlayer().getTotalOxHead() + serie.getTotalHead());
            if (player instanceof HumanPlayer){
                setOxHeadNumber(player, player.getTotalOxHead());
            } else {
                setOxHeadNumber(player,player.getTotalOxHead());
            }
            serie.getStack().clear();
            serie.setLastCard(card);
            serie.getStack().add(card);
            serie.setTotalHead(card.getOxHead());
        } else {
            System.out.println("Veuillez choisir une rangée valide");
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

    public Card aiPlays(AiPlayer ai) { //repère

        int size = ai.getHand().size();
        ArrayList<Integer> evaluation = new ArrayList<>();
        for (int i=0;i<size; i++ ){
            Card card = ai.getHand().get(i);
            evaluation.add(evaluateCard(card));
        }
        int index = evaluation.indexOf(Collections.max(evaluation));
        Card cardToPlay = ai.getHand().get(index);
        ai.getHand().remove(cardToPlay);
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

    public void determineMinimum (Card card, Card[] aiCards) {
        ArrayList<Card> orderCard = new ArrayList<>();
        orderCard.add(card);
        Collections.addAll(orderCard, aiCards);
        ArrayList<Player> orderPlayer = new ArrayList<>();
        orderPlayer.add(player);
        Collections.addAll(orderPlayer, aiPlayers);
        List<Integer> sortedIndexes = new ArrayList<>();
        for (int i = 0; i < orderCard.size(); i++) {
            sortedIndexes.add(i);
        }
        sortedIndexes.sort((index1, index2) -> {
            int number1 = orderCard.get(index1).getNumber();
            int number2 = orderCard.get(index2).getNumber();
            return Integer.compare(number1, number2);
        });
        List<Card> sortedCards = new ArrayList<>(orderCard);
        List<Player> sortedPlayers = new ArrayList<>(orderPlayer);
        for (int i = 0; i < sortedIndexes.size(); i++) {
            int originalIndex = sortedIndexes.get(i);
            orderCard.set(i, sortedCards.get(originalIndex));
            orderPlayer.set(i, sortedPlayers.get(originalIndex));
        }
        for (int i=0; i<orderPlayer.size();i++){
            this.setCardOnBoard(orderCard.get(i),orderPlayer.get(i));
        }
    }

    public void minimumStack(Card card, Player ai) {
        Serie minOxHeadSerie = this.stacks.get(0);
        int minOxHead = Integer.MAX_VALUE;
        for (Serie serie : stacks) {
            if (serie.getTotalHead() < minOxHead) {
                minOxHeadSerie = serie;
            }
        }
        takeCardFromStack (ai, minOxHeadSerie, card);
    }

    public void newPlay() {
        boolean allAIsHaveLessThan66 = true;
        for (Player ai : aiPlayers) {
            if (ai.getTotalOxHead() >= 66) {
                allAIsHaveLessThan66 = false;
                break;
            }
        }
        if (player.getTotalOxHead() < 66 && allAIsHaveLessThan66) {
            // Afficher une boîte de dialogue demandant au joueur s'il veut jouer une autre manche
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Fin du jeu");
            alert.setHeaderText("La manche est terminée.");
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
                    AiPlayer[] aiPlayers = this.aiPlayers;
                    Controller.load("/views/Board.fxml", this.stage, player, aiPlayers);
                } else if (buttonType == buttonTypeNo) {
                    endGame();
                }
            });
        } else {
            endGame();
        }
    }
    public void endGame() {
        Controller.load("/views/FinalScreen.fxml", stage, this.player, aiPlayers);
    }
}

