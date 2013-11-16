package models;

public class HighscoreException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1026048403008907114L;

	public HighscoreException() {
		super();
	}

	public HighscoreException(String message) {
		super(message);
	}

	public HighscoreException(String message, Throwable cause) {
		super(message, cause);
	}

	public HighscoreException(Throwable cause) {
		super(cause);
	}
}
