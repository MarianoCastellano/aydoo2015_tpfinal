package ar.edu.tp.exception;

public class TravelNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public TravelNotFoundException(String message) {
		super(message);
	}
}