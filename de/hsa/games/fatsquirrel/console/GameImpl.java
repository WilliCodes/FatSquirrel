package de.hsa.games.fatsquirrel.console;

import de.hsa.games.fatsquirrel.Game;
import de.hsa.games.fatsquirrel.console.commands.Command;
import de.hsa.games.fatsquirrel.console.commands.GameCommandType;
import de.hsa.games.fatsquirrel.core.HandOperatedMasterSquirrel;
import de.hsa.games.fatsquirrel.core.MoveCommand;
import de.hsa.games.fatsquirrel.core.State;

public class GameImpl extends Game {

	
	public GameImpl(State state) {
		super(state);
		ui = new ConsoleUI();
	}

	@Override
	protected void render() {
		ui.render(state.getFlattenedBoard());
	}

	@Override
	protected void processInput() {
		for (HandOperatedMasterSquirrel e : state.getHandOperatedMasterSquirrels()) {
			
			while(true) {
				Command cmd;
				try {
					cmd = ui.getCommand();
				} catch (Exception ex) {
					ex.printStackTrace();
					continue;
				}
				
				if (cmd.commandTypeInfo.execute(e, cmd.params))
					break;
			}
			
			
			
			
			
			
		}
	}

}
