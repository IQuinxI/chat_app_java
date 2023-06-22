package ma.emsi.Database;


import ma.emsi.Models.User;
import ma.emsi.StateManagement.State;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDB {
    static final String DB_URL = "jdbc:mysql://localhost:3306/chat_app_java";
    static final String USER = "root";
    static final String PASS = "";

    public void insertDummyData() {
        clean();
        String sql = "INSERT INTO user (id, friend_id, firstName, lastName, username, password) VALUES (?,null,?,?,?,?)";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                PreparedStatement stmt = conn.prepareStatement(sql);) {

            for (int i = 0; i < 10; i++) {

                // insert id
                stmt.setInt(1, i);
                // insert firstname
                stmt.setString(2, "fname" + i);
                // isnert last name
                stmt.setString(3, "lname" + i);
                // insert username
                stmt.setString(4, "username" + i);
                // insert password
                stmt.setString(5, "password" + i);

                stmt.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void clean() {
        String sql = "delete from user";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // retrive friends list based on the current user
    public List<String> getFriendsList() {
        List<Integer> friendsIds = getFriendsIds();
        List<String> friendsUsername = new ArrayList<>();

        for(Integer id : friendsIds) 
            friendsUsername.add(getUsernameWithId(id));

        return friendsUsername;
    }

    

    private String getUsernameWithId(int id) {
        String sql = "select username from user where id=?";
        String username = "";
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setInt(1, id);
            ResultSet userRs = stmt.executeQuery();
            while(userRs.next()) 
                username = userRs.getString(1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return username;
    }

    private List<Integer> getFriendsIds() {
        String sql = "select friend_id from user where username=?";
        User currentUser = State.getCurrentUser();

        List<Integer> friendsIds = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setString(1, currentUser.getUsername());
            ResultSet friendsIdsRs = stmt.executeQuery();

            while(friendsIdsRs.next())
                friendsIds.add(friendsIdsRs.getInt(1));            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return friendsIds;
    }
}
