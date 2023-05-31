package com.isep.sixquiprendgame;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.*;

public class FinalScreenController extends Controller {

    @FXML
    private TableView <Player> tableView;
    @FXML
    private TableColumn<Player, String> playerColumn;
    @FXML
    private TableColumn<Player, Integer> pointsColumn;
    @FXML
    private TableColumn<Player, Integer> rankingColumn;
    @FXML
    private Text congratText;



    public void setClassement() {
        ArrayList<Player> data = new ArrayList<>();
        ArrayList<Player> orderPlayer = (ArrayList<Player>) createOrderedPlayerList(player, aiPlayers);
        for (int i =0; i<orderPlayer.size();i++) {
            orderPlayer.get(i).setRanking(i+1);
        }
        if (player.getRanking()==1){
            this.congratText.setText("Bravo, vous avez gagné !");
        } else {
            this.congratText.setText("Ooh, dommage ! Vous avez perdu.");
        }

        this.playerColumn.setCellValueFactory(
                cellData -> {
                    Player player = cellData.getValue();
                    String name = (player instanceof HumanPlayer) ? ((HumanPlayer) player).getName() : "AI";
                    return new SimpleStringProperty(name);
                });
        this.pointsColumn.setCellValueFactory(
                new PropertyValueFactory<>("totalOxHead"));
        this.rankingColumn.setCellValueFactory(
                new PropertyValueFactory<>("ranking"));
        this.tableView.getItems().addAll(orderPlayer);
    }

    public static List<Player> createOrderedPlayerList(HumanPlayer player, AiPlayer[] aiPlayers) {
        List<Player> orderedPlayers = new ArrayList<>();

        // Ajouter le HumanPlayer à la liste des joueurs
        orderedPlayers.add(player);

        // Ajouter les AiPlayers à la liste des joueurs
        orderedPlayers.addAll(Arrays.asList(aiPlayers));

        // Trier la liste des joueurs en fonction de leur oxHeadNumber
        Collections.sort(orderedPlayers, Comparator.comparingInt(Player::getTotalOxHead));

        return orderedPlayers;
    }

}
