package de.hsa.games.fatsquirrel.core;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import de.hsa.games.fatsquirrel.Launcher;
import de.hsa.games.fatsquirrel.core.Board;

public class State {
	
	@SuppressWarnings("unused")
	private int highscore = 9001; // TODO: replace Dummy
	
	private BoardConfig boardConfig;
	private Board board;
	private FlattenedBoard flattenedBoard;
	private Map<String, List<Integer>> scores = new HashMap<>();
	
	private int remainingSteps;
	
	
	public State(Board board, BoardConfig boardConfig) {
		this.boardConfig = boardConfig;
		this.remainingSteps = this.boardConfig.getStepsPerRound();
		this.board = board;
		flattenedBoard = new FlattenedBoard(board);
	}
	
	public void update() {
		
		if (remainingSteps <= 0) {
			reset();
			return;
		}
		board.updateCharacters((EntityContext) flattenedBoard); 
		board.respawn(flattenedBoard.getRespawnList());
		
		
		flattenedBoard.update();
		
		remainingSteps--;
	}
	
	private void reset() {
		
		// Add the Score of every MS to the Map, add new Set if necessary
		board.getMasterSquirrels().forEach(ms -> {
			if (scores.containsKey(ms.playerName))
				scores.get(ms.playerName).add(ms.getEnergy());
			else {
				List<Integer> tmpScore = new ArrayList<>();
				tmpScore.add(ms.getEnergy());
				scores.put(ms.playerName, tmpScore);
			}	
		});
		
		// Sort each ScoreList descending
		scores.values().forEach(list -> {
			Collections.sort(list);
			Collections.reverse(list);
		});
		
			
		scores.forEach((key, value) -> {
			System.out.print(key + "  ");
			value.forEach(s -> System.out.print(" " + s));
			System.out.println();
		});
		
		System.out.println();
		
		
		board = new Board(new BoardConfig(Launcher.configFile));
		flattenedBoard = new FlattenedBoard(board);
		remainingSteps = boardConfig.getStepsPerRound();
	}

	public FlattenedBoard getFlattenedBoard() {
		return flattenedBoard;
	}

	
	
	/* Wird nur aufgerufen im SinglePlayer-Modus */
	public MasterSquirrel getHandOperatedMasterSquirrels() {
		return flattenedBoard.getHandOperatedMasterSquirrel();
	}
	

}
