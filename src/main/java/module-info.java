module com.isep.sixquiprendgame {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.isep.sixquiprendgame to javafx.fxml;
    exports com.isep.sixquiprendgame;
}