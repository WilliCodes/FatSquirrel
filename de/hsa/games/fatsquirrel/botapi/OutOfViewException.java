package de.hsa.games.fatsquirrel.botapi;

public class OutOfViewException extends RuntimeException {

	private static final long serialVersionUID = -4689614019956735708L;

	public OutOfViewException() {
		super();
	}
	
	public OutOfViewException(String string) {
		super(string);
	}

}
