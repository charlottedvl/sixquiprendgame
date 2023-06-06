package com.isep.sixquiprendgame.controller;

import com.isep.sixquiprendgame.AiPlayer;
import com.isep.sixquiprendgame.HumanPlayer;
import com.isep.sixquiprendgame.Player;
import com.isep.sixquiprendgame.controller.Controller;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.util.*;

public class FinalScreenController extends Controller {

    @FXML
    private Button newGameButton;
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

        orderedPlayers.add(player);
        orderedPlayers.addAll(Arrays.asList(aiPlayers));
        orderedPlayers.sort(Comparator.comparingInt(Player::getTotalOxHead));
        return orderedPlayers;
    }

    public void newGameButtonHandler() {
        Controller controller = Controller.load("/views/hello-view.fxml", stage, player, aiPlayers);
    }
}