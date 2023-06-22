package ma.emsi.Models;

import java.util.List;

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String username;
    private List<User> friendsList;

    public User(int id, String firstName, String lastName, String username, List<User> friendsList) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.friendsList = friendsList;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public List<User> getFriendsList() {
        return friendsList;
    }
    public void setFriendsList(List<User> friendsList) {
        this.friendsList = friendsList;
    }

    
}
