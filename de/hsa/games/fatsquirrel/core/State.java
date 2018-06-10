package de.hsa.games.fatsquirrel.core;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hsa.games.fatsquirrel.Launcher;
import de.hsa.games.fatsquirrel.core.Board;

public class State {
	
	@SuppressWarnings("unused")
	private int highscore = 9001; // TODO: replace Dummy
	
	private BoardConfig boardConfig;
	private Board board;
	private FlattenedBoard flattenedBoard;
	private Map<String, List<Integer>> scores;
	
	private int remainingSteps;
	
	
	public State(Board board, BoardConfig boardConfig) {
		this.boardConfig = boardConfig;
		this.remainingSteps = this.boardConfig.getStepsPerRound();
		this.board = board;
		flattenedBoard = new FlattenedBoard(board);
		
		scores = readScoreBoard(boardConfig.getScoreBoardFile());
		if (scores == null)
			scores = new HashMap<>();
	}
	
	/**
	 * updates the state of the game
	 */
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
		
		
		
		saveScoreBoard(boardConfig.getScoreBoardFile());
		
		
		board = new Board(new BoardConfig(Launcher.configFile));
		flattenedBoard = new FlattenedBoard(board);
		remainingSteps = boardConfig.getStepsPerRound();
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
	
	private Map<String, List<Integer>> readScoreBoard(String path) {
		Map<String, List<Integer>> readScores = new HashMap<>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] fields = line.split(",");
				
				List<Integer> tmpScores = new ArrayList<>();
				for (String field : fields) {
					if (field.equals(fields[0]))
						continue;
					tmpScores.add(Integer.parseInt(field));
				}
				readScores.put(fields[0], tmpScores);
			}
			
		} catch (Exception e) {
			return null;
		}
		
		return readScores;
	}
	
	public boolean saveScoreBoard(String path) {
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
			
			
			scores.forEach((key, value) -> {
				try {
					bw.write(key);
					for (Integer i : value) {
						bw.write(","+i);
					}
					bw.newLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return true;
	}

}
