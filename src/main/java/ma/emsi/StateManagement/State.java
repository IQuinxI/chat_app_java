package ma.emsi.StateManagement;

import ma.emsi.Models.User;

public class State {
    private static User currentUser;

    // the state manager behaves like a session, meaning it keeps information that's
    // gonna be used through multiple stages
    // like the current user
    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        State.currentUser = currentUser;
    }

}
