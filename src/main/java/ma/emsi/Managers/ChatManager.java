package ma.emsi.Managers;


import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

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
        chatItems.add(message);
    }
}
