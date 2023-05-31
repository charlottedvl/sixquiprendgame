package com.isep.sixquiprendgame;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        this.player.setRanking(1);
        this.ai.setRanking(2);
        this.congratText.setText("Bravo, vous avez gagné !");
        if (this.player.getTotalOxHead() > this.ai.getTotalOxHead()) {
            this.ai.setRanking(1);
            this.player.setRanking(2);
            this.congratText.setText("Ooh, dommage ! Vous avez perdu.");
        } else if (this.player.getTotalOxHead() == this.ai.getTotalOxHead()) {
            this.ai.setRanking(1);
            this.congratText.setText("Vous avez à moitié gagné... et à moitié perdu !");
        }
        data.add(player);
        data.add(ai);

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
        this.tableView.getItems().addAll(data);
    }


}
