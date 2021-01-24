package proj.api.exceptions;

public class WrongURIFormatException extends RuntimeException {
    public WrongURIFormatException(String message) {
        super(message);
    }
}
