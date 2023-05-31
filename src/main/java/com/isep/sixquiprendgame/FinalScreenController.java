package com.isep.sixquiprendgame;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.util.*;

public class FinalScreenController extends Controller {

    @FXML
    private TableView<Player> tableView;
    @FXML
    private TableColumn<Player, String> playerColumn;
    @FXML
    private TableColumn<Player, Integer> pointsColumn;
    @FXML
    private TableColumn<Player, Integer> rankingColumn;
    @FXML
    private Text congratText;

    public void setClassement(HumanPlayer player, AiPlayer[] aiPlayers) {
        ArrayList<Player> orderedPlayers = createOrderedPlayerList(player, aiPlayers);

        for (int i = 0; i < orderedPlayers.size(); i++) {
            orderedPlayers.get(i).setRanking(i + 1);
        }

        if (player.getRanking() == 1) {
            this.congratText.setText("Bravo, vous avez gagné !");
        } else {
            this.congratText.setText("Ooh, dommage ! Vous avez perdu.");
        }

        this.playerColumn.setCellValueFactory(cellData -> {
            Player playerFinal = cellData.getValue();
            String name = playerFinal.getName();
            return new SimpleStringProperty(name);
        });
        this.pointsColumn.setCellValueFactory(new PropertyValueFactory<>("totalOxHead"));
        this.rankingColumn.setCellValueFactory(new PropertyValueFactory<>("ranking"));

        this.tableView.getItems().addAll(orderedPlayers);
    }

    public static ArrayList<Player> createOrderedPlayerList(HumanPlayer player, AiPlayer[] aiPlayers) {
        ArrayList<Player> orderedPlayers = new ArrayList<>();

        // Ajouter le HumanPlayer à la liste des joueurs
        orderedPlayers.add(player);

        // Ajouter les AiPlayers à la liste des joueurs
        orderedPlayers.addAll(Arrays.asList(aiPlayers));

        // Trier la liste des joueurs en fonction de leur totalOxHead
        orderedPlayers.sort(Comparator.comparingInt(Player::getTotalOxHead));

        return orderedPlayers;
    }
}