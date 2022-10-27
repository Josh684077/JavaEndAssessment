package CustomExceptions;

public class ItemNotFoundException extends Exception{

    public ItemNotFoundException() {
        super("There is no item in our database with this ID.");
    }

    public ItemNotFoundException(String message) {
        super(message);
    }
}
