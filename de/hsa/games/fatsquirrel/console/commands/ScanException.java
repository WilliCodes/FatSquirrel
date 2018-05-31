package de.hsa.games.fatsquirrel.console.commands;

import java.util.logging.Logger;

@SuppressWarnings("serial")
public class ScanException extends RuntimeException {

	Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	public ScanException() {super();}
	
	public ScanException(String arg0) {super(arg0);}
	
	public ScanException(Throwable arg0) {super(arg0);}
	
	public ScanException(String arg0, Throwable arg1) {super(arg0, arg1);}

}
