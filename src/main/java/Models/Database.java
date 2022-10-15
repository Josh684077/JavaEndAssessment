package Models;

import java.util.ArrayList;
import java.util.List;

public class Database {
    private List<User> users = new ArrayList<>();
    private List<Member> members = new ArrayList<>();


    public Database()
    {
        //Initialise Users
        users.add(new User("user1", "password1"));
        users.add(new User("user2", "password2"));
        users.add(new User("user3", "password3"));
        users.add(new User("user4", "password4"));
    }

    public User validateLogin(String username, String password) throws Exception {
        for (User user: users)
        {
            if (username.equalsIgnoreCase(user.getUsername()))
            {
                if (password.equals(user.getPassword()))
                {
                    //If both username & password match a user in db
                    return user;
                }
                //If username matched a user in db but password did not
                throw new Exception("Incorrect password.");
            }
        }
        //If username didn't match with a user in db
        throw new Exception("Username is not registered in our database.");
    }

    public User getUser(String username){
        return new User("user1", "password1");
    }

}

