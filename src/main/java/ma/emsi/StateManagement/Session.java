package ma.emsi.StateManagement;

import ma.emsi.Models.User;

public class Session {
    private static User currentUser;

    // the state manager behaves like a session, meaning it keeps information that's
    // gonna be used through multiple stages
    // like the current user
    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        Session.currentUser = currentUser;
    }

}
