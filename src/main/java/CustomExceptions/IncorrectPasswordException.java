package CustomExceptions;

public class IncorrectPasswordException extends Exception{

    public IncorrectPasswordException() {
        super("The password is incorrect.");
    }
}
