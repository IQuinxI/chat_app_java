package ma.emsi.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import ma.emsi.Managers.ChatManager;
import ma.emsi.Managers.FriendsManager;
import ma.emsi.Models.Chat;
import ma.emsi.Sockets.UserClient;
import ma.emsi.StateManagement.Session;
import ma.emsi.Database.UserDB;

public class ChatHubController implements Initializable {
    @FXML
    private ListView<String> friendsListView;
    private ObservableList<String> friendsItems = FXCollections.observableArrayList();

    @FXML
    private ListView<String> chatListView;
    private ObservableList<String> chatItems = FXCollections.observableArrayList();

    private FriendsManager friendsManager;
    private ChatManager chatManager;
    private UserClient userClient;

    @FXML
    private TextField messageTextField;

    @FXML 
    private VBox chatSideVBox;

    private String selectedFriend = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        friendsManager = new FriendsManager(friendsListView, friendsItems);
        chatManager = new ChatManager(chatListView, chatItems);

        userClient = new UserClient(chatManager);

        // Retrieve friends list
        List<String> friends = new UserDB().getFriendsList();
        friendsItems.addAll(friends);

        // initializes the ListView for the friends
        friendsManager.initialize();
        chatManager.initialize();

        addFriendsOnClickListener();


    }

     private void addFriendsOnClickListener() {
        friendsListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {            // User selectedUser = newValue;        
            chatSideVBox.setVisible(true);
            selectedFriend = newValue;
            // if(selectedUser != null)    
            //     chatManager.updateChat(selectedUser);
        });
    }


    @FXML
    private void sendMessage() {
        if(selectedFriend == null) return;
        int selectedFriendId = new UserDB().getIdWithUsername(selectedFriend);
        
        try {
            String request = selectedFriendId+":"+messageTextField.getText();
            chatManager.updateChat(messageTextField.getText());
            userClient.sendMessage(request);            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cleanUp() {
        userClient.close();
    }
    
}