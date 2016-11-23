package lcbs.exceptions;

public class UserException extends Exception {
	private static final long serialVersionUID = 1L;
	public int code = 0 ;
	public UserException(String message, int code) {
        super(message);
        this.code = code;
    }
}
