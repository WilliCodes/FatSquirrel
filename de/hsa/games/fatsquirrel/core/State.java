package de.hsa.games.fatsquirrel.core;

import java.util.ArrayList;
import java.util.List;

import de.hsa.games.fatsquirrel.core.Board;

public class State {
	
	private int highscore = 9001; // TODO: replace Dummy
	private Board board;
	private FlattenedBoard flattenedBoard;
	private ArrayList<PlayerEntity> movablePlayerEntities = new ArrayList<>();
	
	public State(Board board) {
		this.board = board;
		flattenedBoard = new FlattenedBoard(board);
		movablePlayerEntities.addAll(board.getMovablePlayerEntities());
	}
	
	public void update() {
		
		flattenedBoard.resetRespawnList();
		board.updateCharacters((EntityContext) flattenedBoard); 
		board.respawn(flattenedBoard.getRespawnList());
		
		flattenedBoard.update();
		
		movablePlayerEntities.clear();
		movablePlayerEntities.addAll(board.getMovablePlayerEntities());
	}
	
	public FlattenedBoard getFlattenedBoard() {
		return flattenedBoard;
	}
	
	
	public ArrayList<PlayerEntity> getMovablePlayerEntities() {
		return movablePlayerEntities;
	}
	

}
