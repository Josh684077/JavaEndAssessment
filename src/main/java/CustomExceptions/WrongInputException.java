package CustomExceptions;

public class WrongInputException extends Exception{

    //Constructors
    public WrongInputException() {
        super("Wrong input detected.");
    }

    public WrongInputException(String message) {
        super(message);
    }


}
