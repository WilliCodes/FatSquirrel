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
			Command cmd = ui.getCommand();
			
			switch((GameCommandType) cmd.commandTypeInfo) {
			case ALL:
				break;
			case DOWN:
				e.setNextCommand(new MoveCommand(2));
				break;
			case EXIT:
				break;
			case HELP:
				break;
			case LEFT:
				break;
			case MASTER_ENERGY:
				break;
			case RIGHT:
				break;
			case SPAWN_MINI:
				break;
			case UP:
				break;
			default:
				break;
			
			}
			
			
		}
	}

}
