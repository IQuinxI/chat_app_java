package ma.emsi.Managers;

import java.util.Date;

import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import ma.emsi.Models.Chat;
import ma.emsi.Models.User;

public class ChatManager {
    private ListView<String> chatListView;
    private ObservableList<String> chatItems;
    
    public ChatManager(ListView<String> chatListView, ObservableList<String> chatItems) {
        this.chatListView = chatListView;
        this.chatItems = chatItems;
    }

    public void initialize() {
        binding();
        // setItemtoUsername();
    }
    
    private void binding() {
        chatListView.setItems(chatItems);
    }

    

    public void updateChat(String message) {
        // TODO: retrieve the messages from the Chat table, using the current user and friend (receiver)
        chatItems.add(message);
    }
}
