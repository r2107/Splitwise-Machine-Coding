package exceptions;

public class InputException extends Exception {
	private String message;
	
	public InputException(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	
}
