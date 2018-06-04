package de.hsa.games.fatsquirrel.gui;

import java.util.logging.Logger;

import de.hsa.games.fatsquirrel.Game;
import de.hsa.games.fatsquirrel.UI;
import de.hsa.games.fatsquirrel.console.commands.Command;
import de.hsa.games.fatsquirrel.console.commands.GameCommandType;
import de.hsa.games.fatsquirrel.core.HandOperatedMasterSquirrel;
import de.hsa.games.fatsquirrel.core.State;

public class GameUi extends Game {

	private Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	private UI ui;
	public GameUi(State state, FxUI ui) {
		super(state);
		this.ui = ui;
		logger.info("UI created");
	}

	@Override
	protected void render() {
		ui.render(state.getFlattenedBoard());
		
	}

	@Override
	protected void processInput() {
		
		// get first and currently only Player
		HandOperatedMasterSquirrel ms = state.getHandOperatedMasterSquirrels();
		
		try {
			Command cmd = ui.getCommand();
			if (cmd == null) {
				//ms.setNextCommand(new MoveCommand(5));
				paused = true;
			} else {
				paused = false;
				cmd.commandTypeInfo.execute(ms, cmd.params);
			}
			
			System.out.println(cmd == null? "null" : cmd.commandTypeInfo.toString() + " " + paused);
				

		} catch (Exception ex) {
			logger.warning(ex.toString());
			ex.printStackTrace();
		}
	}

}
