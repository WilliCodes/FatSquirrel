package de.hsa.games.fatsquirrel.core;

import java.util.ArrayList;

import de.hsa.games.fatsquirrel.core.Board;

public class State {
	
	private int highscore = 9001; // TODO: replace Dummy
	private Board board;
	private FlattenedBoard flattenedBoard;
	private ArrayList<HandOperatedMasterSquirrel> handOperatedMasterSquirrels = new ArrayList<>();
	
	public State(Board board) {
		this.board = board;
		flattenedBoard = new FlattenedBoard(board);
		handOperatedMasterSquirrels.addAll(board.getHandOperatedMasterSquirrels());
	}
	
	public void update() {
		
		board.updateCharacters((EntityContext) flattenedBoard); 
		board.respawn(flattenedBoard.getRespawnList());
		
		flattenedBoard.update();
		
		handOperatedMasterSquirrels.clear();
		handOperatedMasterSquirrels.addAll(board.getHandOperatedMasterSquirrels());
	}
	
	public FlattenedBoard getFlattenedBoard() {
		return flattenedBoard;
	}
	
	
	public ArrayList<HandOperatedMasterSquirrel> getHandOperatedMasterSquirrels() {
		return handOperatedMasterSquirrels;
	}
	

}
