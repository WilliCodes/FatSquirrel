package de.hsa.games.fatsquirrel.gui;

import de.hsa.games.fatsquirrel.Game;
import de.hsa.games.fatsquirrel.UI;
import de.hsa.games.fatsquirrel.core.State;

public class GameUi extends Game {

	private UI ui;
	public GameUi(State state, FxUI ui) {
		super(state);
		this.ui = ui;
	}

	@Override
	protected void render() {
		ui.render(state.getFlattenedBoard());
		
	}

	@Override
	protected void processInput() {
		
		
	}

}
