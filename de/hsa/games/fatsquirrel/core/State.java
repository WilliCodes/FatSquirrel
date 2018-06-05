package de.hsa.games.fatsquirrel.core;


import de.hsa.games.fatsquirrel.core.Board;

public class State {
	
	@SuppressWarnings("unused")
	private int highscore = 9001; // TODO: replace Dummy
	private Board board;
	private FlattenedBoard flattenedBoard;
	
	
	public State(Board board) {
		this.board = board;
		flattenedBoard = new FlattenedBoard(board);
	}
	
	public void update() {
		
		board.updateCharacters((EntityContext) flattenedBoard); 
		board.respawn(flattenedBoard.getRespawnList());
		
		
		flattenedBoard.update();
	}
	
	public FlattenedBoard getFlattenedBoard() {
		return flattenedBoard;
	}
	
	
	/* Wird nur aufgerufen im SinglePlayer-Modus */
	public MasterSquirrel getHandOperatedMasterSquirrels() {
		return flattenedBoard.getHandOperatedMasterSquirrel();
	}
	

}
