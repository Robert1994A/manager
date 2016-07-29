package ro.inf.ucv.admitere.exceptions;

public class UserAlreadyExist extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String message;

    private int userId;

    public UserAlreadyExist(String message) {
        super(message);
    }

    public UserAlreadyExist(String message, int userId) {
        super(message);
        this.userId = userId;
    }

    public UserAlreadyExist(String message, int userId, Throwable cause) {
        super(message, cause);
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String toString() {
        return super.toString();
    }

}
