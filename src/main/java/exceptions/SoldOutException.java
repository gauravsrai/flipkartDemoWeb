package exceptions;

public class SoldOutException extends Throwable{

    public SoldOutException(String message) {
        super(message);
    }
}
