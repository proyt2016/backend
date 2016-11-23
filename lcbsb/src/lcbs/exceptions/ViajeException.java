package lcbs.exceptions;

public class ViajeException extends Exception {
	private static final long serialVersionUID = 1L;
	public int code = 0 ;
	public ViajeException(String message, int code) {
        super(message);
        this.code = code;
    }
}
