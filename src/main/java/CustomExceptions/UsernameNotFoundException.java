package CustomExceptions;

public class UsernameNotFoundException extends Exception{

    public UsernameNotFoundException() {
        super("Username is not registered in our database.");
    }
}
