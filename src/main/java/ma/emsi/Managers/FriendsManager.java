package ma.emsi.Managers;

import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import ma.emsi.Models.User;

public class FriendsManager {

    private ListView<String> friendsListView;
    private ObservableList<String> friendsItems;
    private User selectedUser = null;

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }

    public User getSelectedUser() {
        return selectedUser;
    }

    public FriendsManager(ListView<String> friendsListView, ObservableList<String> friendsItems) {
        this.friendsListView = friendsListView;
        this.friendsItems = friendsItems;
    }

    public void initialize() {
        binding();
        // setItemtoUsername();
    }

    // binds the ListView to the ObservableList
    private void binding() {
        friendsListView.setItems(friendsItems);
    }

    // show only the username when listing objects in the ListView (You can still reference the object User)
    // private void setItemtoUsername() {
    //     friendsListView.setCellFactory(param -> new ListCell<User>() {
    //         @Override
    //         protected void updateItem(User user, boolean empty) {

    //             super.updateItem(user, empty);
    //             if (empty || user == null) {
    //                 setText(null);
    //             } else {
    //                 setText(user.getUsername());
    //             }
    //         }
    //     });
    // }
   
    // private void showAlert(String message) {
    //     Alert alert = new Alert(AlertType.INFORMATION);
    //     alert.setTitle("Information");
    //     alert.setHeaderText(null);
    //     alert.setContentText(message);
    //     alert.showAndWait();
    // }
}
