package de.hsa.games.fatsquirrel;


import de.hsa.games.fatsquirrel.console.GameImpl;
import de.hsa.games.fatsquirrel.core.*;
import de.hsa.games.fatsquirrel.gui.FxUI;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Launcher extends Application {
	
	public static boolean printDebugInfo = false;
	static BoardConfig boardConfig = new BoardConfig();
	static GameImpl game;

	public static void main(String[] args) throws Exception {
		Board board = new Board(boardConfig);
		State state = new State(board);
		game = new GameImpl(state);
		boolean gui = true;
		
		
		if(!gui) {
			startGame(game);
		}
		start2(new Stage());
		
	}
	
	
	public static void startCommandLoop(GameImpl game) {
		game.startCommandLoop();
	}


	
	public static void start2(Stage primaryStage) throws Exception {
		FxUI fxUI = FxUI.createInstance(boardConfig.getSize());
        final Game game2 = game;
         
        primaryStage.setScene(fxUI);
        primaryStage.setTitle("Diligent Squirrel");
        fxUI.getWindow().setOnCloseRequest(new EventHandler<KeyEvent>() {
			
        	@Override
			public void handle(KeyEvent arg0) {
				
			}
        });
        primaryStage.show();   
        
        startGame(game2);    
		
	}


	private static void startGame(Game game) {
		
		InputReader inputReader = new InputReader(((GameImpl) game).getUi());
		inputReader.start();
		game.run();
		
	}


	@Override
	public void start(Stage primaryStage) throws Exception {
		start2(primaryStage);
		
	}
}
