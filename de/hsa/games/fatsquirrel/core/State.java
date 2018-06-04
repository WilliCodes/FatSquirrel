package de.hsa.games.fatsquirrel.core;

import java.util.ArrayList;
import java.util.List;

import de.hsa.games.fatsquirrel.core.Board;

public class State {
	
	private int highscore = 9001; // TODO: replace Dummy
	private Board board;
	private FlattenedBoard flattenedBoard;
	private List<MasterSquirrel> masterSquirrels = new ArrayList<>();
	
	public State(Board board) {
		this.board = board;
		flattenedBoard = new FlattenedBoard(board);
		masterSquirrels = (board.getMasterSquirrels());
	}
	
	public void update() {
		
		board.updateCharacters((EntityContext) flattenedBoard); 
		board.respawn(flattenedBoard.getRespawnList());
		
		for (MasterSquirrel ms : masterSquirrels)
			if (ms.getSpawmMini() > 0) {
				XY pos = ms.getSpawnMiniPos();
				if (pos == null)
					pos = flattenedBoard.getRandomFreeNeighbourCellDirection(ms.getPosition());
				board.spawnMini(pos.plus(ms.getPosition()), ms);
			}
		
		flattenedBoard.update();
	}
	
	public FlattenedBoard getFlattenedBoard() {
		return flattenedBoard;
	}
	
	
	/* Wird nur aufgerufen im SinglePlayerModus */
	public MasterSquirrel getHandOperatedMasterSquirrels() {
		
		return masterSquirrels.get(0);
	}
	

}
