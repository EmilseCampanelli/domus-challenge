package domus.challenge.exception;

public class MovieApiTechnicalException extends RuntimeException {
    public MovieApiTechnicalException(String message) {
        super(message);
    }

    public MovieApiTechnicalException(String message, Throwable cause) {
        super(message, cause);
    }
}
