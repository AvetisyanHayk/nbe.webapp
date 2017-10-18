package be.hwyk.testapp.ex;

/**
 *
 * @author Hayk
 */
public class DBException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    
    public DBException(Throwable cause) {
        super("Something went wrong with the database connection.", cause);
    }
}
