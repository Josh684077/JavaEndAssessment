package CustomExceptions;

public class MemberNotFoundException extends Exception{

    public MemberNotFoundException() {
        super("No member found with these specifications");
    }

    public MemberNotFoundException(String message) {
        super(message);
    }
}
