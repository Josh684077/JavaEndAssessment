package CustomExceptions;

public class ItemNotFoundException extends Exception{

    public ItemNotFoundException() {
        super("No item found with these specifications");
    }

    public ItemNotFoundException(String message) {
        super(message);
    }
}
