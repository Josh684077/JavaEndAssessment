package CustomExceptions;

public class DatabaseException extends Exception{
    public DatabaseException(String message) {
        super(message);
    }

    public DatabaseException() {
        super("Something went wrong with the database.");
    }
}
