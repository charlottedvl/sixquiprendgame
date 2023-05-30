module com.isep.sixquiprendgame {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires java.desktop;


    opens com.isep.sixquiprendgame to javafx.fxml;
    exports com.isep.sixquiprendgame;
}