module ma.emsi {
    requires javafx.controls;
    requires javafx.fxml;

    opens ma.emsi.Controllers to javafx.fxml;
    exports ma.emsi;
}
