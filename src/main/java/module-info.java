module ma.emsi {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    
    opens ma.emsi.Controllers to javafx.fxml;
    exports ma.emsi;
}
