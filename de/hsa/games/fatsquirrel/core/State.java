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
	
	/**
	 * updates the state of the game
	 */
	public void update() {
		
		board.updateCharacters((EntityContext) flattenedBoard); 
		board.respawn(flattenedBoard.getRespawnList());
		
		
		flattenedBoard.update();
	}
	
	/**
	 * 
	 * @return FlattenBoard
	 */
	public FlattenedBoard getFlattenedBoard() {
		return flattenedBoard;
	}
	
	
	/**
	 * wird nur im SinglePlayer aufgerufen
	 * @return HandOperatedMasterSquirrel
	 */
	public MasterSquirrel getHandOperatedMasterSquirrels() {
		return flattenedBoard.getHandOperatedMasterSquirrel();
	}
	

}
