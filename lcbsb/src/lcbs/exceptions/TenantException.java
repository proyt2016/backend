package lcbs.exceptions;

public class TenantException extends Exception {
	private static final long serialVersionUID = 1L;
	public int code = 0 ;
	public TenantException(String message, int code) {
        super(message);
        this.code = code;
    }
}
