package de.hsa.games.fatsquirrel.console.commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

public class CommandScanner {
	
	private CommandTypeInfo[] commandTypeInfos;
	private BufferedReader inputReader;
	private PrintStream outputStream;

	public CommandScanner(CommandTypeInfo[] commandTypes, BufferedReader inputReader, PrintStream outputStream) {
		this.commandTypeInfos = commandTypes;
		this.inputReader = inputReader;
		this.outputStream = outputStream;
	}
	
	public Command next() throws NoSuchMethodException, SecurityException {
		
		String input;
		
		try {
			input = inputReader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		String[] inputs = input.split(" ");
		
		if (inputs.length < 1) {
			throw new ScanException("Invalid Input");
		}
		
		CommandTypeInfo cmd = GameCommandType.valueOf(inputs[0]);
		
		if (cmd == null) {
			throw new ScanException("Unkown Command");
		}
		
		Class<?>[] paramShapes = cmd.getParamTypes();
		
		if(paramShapes == null)
			return new Command(cmd, null);
		
		if(paramShapes.length != inputs.length - 1) 
			throw new ScanException("Number of Arguments does not match");
		
		Object[] params = new Object[paramShapes.length];
		
		for (int i = 0; i < paramShapes.length; i++) {
			if (paramShapes[i] == int.class) {
				params[i] = Integer.parseInt(inputs[i+1]);
				continue;
			}
			
			if (paramShapes[i] == double.class) {
				params[i] = Double.parseDouble(inputs[i+1]);
				continue;
			}
			
			if (paramShapes[i] == float.class) {
				params[i] = Float.parseFloat(inputs[i+1]);
				continue;
			}
			
			if (paramShapes[i] == String.class) {
				params[i] = inputs[i+1];
				continue;
			}
			
		}
		
				
		return new Command(cmd, params);
	}
}
