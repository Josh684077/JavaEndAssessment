package Models;

import java.util.ArrayList;
import java.util.List;

public class Database {
    public List<User> users = new ArrayList<>();

    public Database()
    {
        users.add(new User("user1", "password1"));
        users.add(new User("user2", "password2"));
        users.add(new User("user3", "password3"));
        users.add(new User("user4", "password4"));
    }

    public int validateLogin(User loginUser)
    {
        for (User user: users)
        {
            if (loginUser.username == user.username)
            {
                if (loginUser.password == user.password)
                {
                    //If username & password match
                    return 0;
                }
                //If username matched but password didn't
                return 1;
            }
        }
        //If username didn't match
        return 2;
    }
}

