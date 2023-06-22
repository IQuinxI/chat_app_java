package ma.emsi;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

public class ChatHubController implements Initializable {
    @FXML
    private ListView<String> friendsListView;
    private ObservableList<String> friendsItems = FXCollections.observableArrayList();
    private int counter = 0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        friendsListView.setItems(friendsItems);
    }

    @FXML
    private void addFriend() throws IOException {

        friendsItems.add("friend"+counter++);
    }
}