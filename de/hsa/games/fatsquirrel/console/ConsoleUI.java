package de.hsa.games.fatsquirrel.console;

import java.util.InputMismatchException;
import java.util.Scanner;

import de.hsa.games.fatsquirrel.*;
import de.hsa.games.fatsquirrel.core.BoardView;
import de.hsa.games.fatsquirrel.core.MoveCommand;

public class ConsoleUI implements UI {
	
	public ConsoleUI() {};

	@Override
	public MoveCommand getCommand() {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		do {
			int direction;
			System.out.print("MasterSquirrel: ");
			try {
				direction = input.nextInt(); 
				} catch (InputMismatchException e) {
					input.next();
					System.err.println("Input a number on your numpad indicating a direction!");
					continue;
				}
			
			if (direction > 0 && direction < 10 && direction != 5) 
				return new MoveCommand(direction);
			else
				System.err.println("Input a number on your numpad indicating a direction!");	
		} while (true);
	}

	@Override
	public void render(BoardView view) {
		System.out.println(view.toString());
	}
	
	

}