module com.isep.sixquiprendgame {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;


    opens com.isep.sixquiprendgame to javafx.fxml;
    exports com.isep.sixquiprendgame;
}