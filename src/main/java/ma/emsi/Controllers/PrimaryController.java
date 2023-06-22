package ma.emsi.Controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import ma.emsi.App;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
