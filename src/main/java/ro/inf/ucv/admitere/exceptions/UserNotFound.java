package ro.inf.ucv.admitere.exceptions;

public class UserNotFound extends Exception {

    private static final long serialVersionUID = 1608656605589162219L;

    private String message;

    public UserNotFound(String message) {
        super(message);
    }

    public UserNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String toString() {
        return super.toString();
    }
}
