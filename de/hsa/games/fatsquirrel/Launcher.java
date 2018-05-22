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
		launch(args);
		
	}
	
	
	public static void startCommandLoop(GameImpl game) {
		game.startCommandLoop();
	}


	
	public void start(Stage primaryStage) throws Exception {
		FxUI fxUI = FxUI.createInstance(boardConfig.getSize());
        final Game game = this.game;
         
        primaryStage.setScene(fxUI);
        primaryStage.setTitle("Diligent Squirrel");
        fxUI.getWindow().setOnCloseRequest(new EventHandler<WindowEvent>() {
			
        	@Override
			public void handle(WindowEvent arg0) {
				System.exit(0);
			}
        });
        primaryStage.show();   
        
        startGame(game);    
		
	}


	private static void startGame(Game game) {
		
		InputReader inputReader = new InputReader(((GameImpl) game).getUi());
		inputReader.start();
		game.run();
		
	}

}
