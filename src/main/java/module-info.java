module ma.emsi {
    requires javafx.controls;
    requires javafx.fxml;

    opens ma.emsi to javafx.fxml;
    exports ma.emsi;
}
