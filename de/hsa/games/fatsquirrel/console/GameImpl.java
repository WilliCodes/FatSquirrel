package de.hsa.games.fatsquirrel.console;

import de.hsa.games.fatsquirrel.Game;
import de.hsa.games.fatsquirrel.core.HandOperatedMasterSquirrel;
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
			e.setNextCommand(ui.getCommand());
		}
	}

}
