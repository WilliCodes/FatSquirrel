package de.hsa.games.fatsquirrel.core;

import java.util.ArrayList;

import de.hsa.games.fatsquirrel.core.Board;

public class State {
	
	private int highscore = 9001; // TODO: replace Dummy
	private Board board;
	private FlattenedBoard flattenedBoard;
	private HandOperatedMasterSquirrel handOperatedMasterSquirrel;
	
	public State(Board board) {
		this.board = board;
		flattenedBoard = new FlattenedBoard(board);
		handOperatedMasterSquirrel = (board.getHandOperatedMasterSquirrel());
	}
	
	public void update() {
		
		board.updateCharacters((EntityContext) flattenedBoard); 
		board.respawn(flattenedBoard.getRespawnList());
		
		HandOperatedMasterSquirrel ms = handOperatedMasterSquirrel;
			if (ms.getSpawmMini() > 0) {
				XY pos = ms.getSpawnMiniPos();
				if (pos == null)
					pos = flattenedBoard.getNextFreeCell(ms.getPosition());
				board.spawnMini(pos, ms);
			}
		
		flattenedBoard.update();
	}
	
	public FlattenedBoard getFlattenedBoard() {
		return flattenedBoard;
	}
	
	
	public HandOperatedMasterSquirrel getHandOperatedMasterSquirrels() {
		return handOperatedMasterSquirrel;
	}
	

}
