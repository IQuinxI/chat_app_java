package ma.emsi.StateManagement;

import ma.emsi.Models.User;

public class State {
    private static User currentUser = new User(0, "fname0", "lname0", "username0", "password0",null);

    // the state manager behaves like a session, meaning it keeps information that's
    // gonna be used through multiple stages
    // like the current user
    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        State.currentUser = currentUser;
    }

}
