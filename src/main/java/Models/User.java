package Models;

public class User {
    private String username;
    private String password;
    private String name;

    public User(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }

    public String getName()
    {
        return name;
    }
}
