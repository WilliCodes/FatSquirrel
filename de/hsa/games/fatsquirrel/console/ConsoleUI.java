package de.hsa.games.fatsquirrel.console;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;

import de.hsa.games.fatsquirrel.*;
import de.hsa.games.fatsquirrel.console.commands.Command;
import de.hsa.games.fatsquirrel.console.commands.CommandScanner;
import de.hsa.games.fatsquirrel.console.commands.GameCommandType;
import de.hsa.games.fatsquirrel.core.BoardView;
import de.hsa.games.fatsquirrel.core.MoveCommand;

public class ConsoleUI implements UI {
	
	private PrintStream outputStream = System.out;
	private BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
	
	private CommandScanner commandScanner = new CommandScanner(GameCommandType.values(), inputReader, outputStream);
	
	public ConsoleUI() {};

	
	private Command nextCommand = new Command(GameCommandType.HELP,null);
	
	@Override
	
	public Command getCommand() {
		return nextCommand;
	}
	
	
	public void commandLoop() {
		while(true) {
		try {
			nextCommand = commandScanner.next();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		}
	}

	@Override
	public void render(BoardView view) {
		System.out.println(view.toString());
		
		// Alternativ hätte hier toString() mit getSize() und getEntityType(int x, int y) implementiert werden können
	}
	
	

}