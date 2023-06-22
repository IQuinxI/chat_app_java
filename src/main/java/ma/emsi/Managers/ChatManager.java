package ma.emsi.Managers;

import java.util.Date;

import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import ma.emsi.Models.Chat;
import ma.emsi.Models.User;

public class ChatManager {
    private ListView<Chat> chatListView;
    private ObservableList<Chat> chatItems;
    
    public ChatManager(ListView<Chat> chatListView, ObservableList<Chat> chatItems) {
        this.chatListView = chatListView;
        this.chatItems = chatItems;
    }

    public void initialize() {
        binding();
        setItemtoUsername();
    }

    private void binding() {
        chatListView.setItems(chatItems);
    }

    // show only the username when listing objects in the ListView (You can still reference the object User)
    private void setItemtoUsername() {
        chatListView.setCellFactory(param -> new ListCell<Chat>() {
            @Override
            protected void updateItem(Chat chat, boolean empty) {

                super.updateItem(chat, empty);
                if (empty || chat == null) {
                    setText(null);
                } else {
                    setText(chat.getMessage());
                }
            }
        });
    }

    public void updateChat(User friend) {
        // TODO: retrieve the messages from the Chat table, using the current user and friend (receiver)
        chatItems.add(new Chat(0, friend, friend, "hey how are you", new Date()));
    }
}
