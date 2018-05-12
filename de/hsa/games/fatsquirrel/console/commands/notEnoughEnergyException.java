package de.hsa.games.fatsquirrel.console.commands;

public class notEnoughEnergyException extends RuntimeException {

public notEnoughEnergyException() {super();}
	
	public notEnoughEnergyException(String arg0) {super(arg0);}
	
	public notEnoughEnergyException(Throwable arg0) {super(arg0);}
	
	public notEnoughEnergyException(String arg0, Throwable arg1) {super(arg0, arg1);}
	
}
