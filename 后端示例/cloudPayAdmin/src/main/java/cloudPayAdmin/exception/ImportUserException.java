package cloudPayAdmin.exception;

public class ImportUserException extends RuntimeException {

	private static final long serialVersionUID = 6134219042729193188L;

	public ImportUserException(String msg) {
		super(msg);
	}
	
	public ImportUserException() {
		super();
	}
}
