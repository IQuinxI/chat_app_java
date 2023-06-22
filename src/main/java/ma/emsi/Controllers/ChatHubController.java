package ma.emsi.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import ma.emsi.Managers.ChatManager;
import ma.emsi.Managers.FriendsManager;
import ma.emsi.Models.Chat;
import ma.emsi.Models.User;

public class ChatHubController implements Initializable {
    @FXML
    private ListView<User> friendsListView;
    private ObservableList<User> friendsItems = FXCollections.observableArrayList();
    private int counter = 0;

    @FXML
    private ListView<Chat> chatListView;
    private ObservableList<Chat> chatItems = FXCollections.observableArrayList();

    private FriendsManager friendsManager;
    private ChatManager chatManager;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        friendsManager = new FriendsManager(friendsListView, friendsItems);
        chatManager = new ChatManager(chatListView, chatItems);
        // initializes the ListView for the friends
        friendsManager.initialize();
        chatManager.initialize();

        addFriendsOnClickListener();

    }

     private void addFriendsOnClickListener() {
        friendsListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            User selectedUser = newValue;        
            if(selectedUser != null)    
                chatManager.updateChat(selectedUser);
        });
        

        // friendsListView.setOnMouseClicked(event -> {
        //     User selectdUser = friendsListView.getSelectionModel().getSelectedItem();
        //     if (selectdUser != null)
        //         setSelectedUser(selectdUser);
        // });

        // System.out.println("The selected user is: "+ selectedUser.getUsername());
    }


    @FXML
    private void addFriend() throws IOException {

        friendsItems.add(new User(counter, "Yassine", "Lahrache", "Quinx", null));
        friendsItems.add(new User(counter, "Yassine", "Lahrache", "Boogie", null));

    }
}