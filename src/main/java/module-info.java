module ma.emsi {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;

    opens ma.emsi to javafx.fxml;
    exports ma.emsi;
}
