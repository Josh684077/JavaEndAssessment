package nl.inholland.endassessment;

import Models.User;

public class MainWindowController {

    User loggedInUser;

    public MainWindowController(User user){
        this.loggedInUser = user;
    }
}
