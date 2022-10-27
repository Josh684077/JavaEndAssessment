package CustomExceptions;

public class MemberNotFoundException extends Exception{

    public MemberNotFoundException() {
        super("There is no member in our database with this ID.");
    }

    public MemberNotFoundException(String message) {
        super(message);
    }
}
