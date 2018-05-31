package de.hsa.games.fatsquirrel.console.commands;

import java.util.logging.Logger;

public class notEnoughEnergyException extends RuntimeException {
	
	Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	public notEnoughEnergyException() {super();}
	
	public notEnoughEnergyException(String arg0) {super(arg0);}
	
	public notEnoughEnergyException(Throwable arg0) {super(arg0);}
	
	public notEnoughEnergyException(String arg0, Throwable arg1) {super(arg0, arg1);}
	
}
