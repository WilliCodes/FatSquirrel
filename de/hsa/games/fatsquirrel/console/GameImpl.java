package de.hsa.games.fatsquirrel.console;

import java.util.logging.Logger;

import de.hsa.games.fatsquirrel.Game;
import de.hsa.games.fatsquirrel.UI;
import de.hsa.games.fatsquirrel.console.commands.Command;
import de.hsa.games.fatsquirrel.console.commands.GameCommandType;
import de.hsa.games.fatsquirrel.core.HandOperatedMasterSquirrel;
import de.hsa.games.fatsquirrel.core.MoveCommand;
import de.hsa.games.fatsquirrel.core.State;

public class GameImpl extends Game {

	private Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	public GameImpl(State state) {
		super(state);
		ui = new ConsoleUI();
	}
	public UI getUi() {
		return ui;
	}

	@Override
	protected void render() {
		ui.render(state.getFlattenedBoard());
	}

	@Override
	protected void processInput() {
		HandOperatedMasterSquirrel masterSquirrel = state.getHandOperatedMasterSquirrels();
			
			
				Command cmd;

				while(true) {

          try {
            cmd = ui.getCommand();
            cmd.commandTypeInfo.execute(masterSquirrel, cmd.params);
            break;
          } catch (Exception ex) {
        	  logger.info(ex.toString());
            ex.printStackTrace();
            continue;
          }
				

			
			
			
			
			
			
		}
	}

}
