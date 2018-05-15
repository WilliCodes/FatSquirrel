package de.hsa.games.fatsquirrel;


import de.hsa.games.fatsquirrel.console.FxUI;
import de.hsa.games.fatsquirrel.console.GameImpl;
import de.hsa.games.fatsquirrel.core.*;
import javafx.application.Application;
import javafx.stage.Stage;

public class Launcher extends Application {
	
	public static boolean printDebugInfo = false;
	

	public static void main(String[] args) {
		Board board = new Board(new BoardConfig());
		State state = new State(board);
		GameImpl game = new GameImpl(state);
		
		InputReader inputReader = new InputReader(game.getUi());
		
		inputReader.start();
		game.run();
		
	}
	
	
	public static void startCommandLoop(GameImpl game) {
		game.startCommandLoop();
	}


	@Override
	public void start(Stage primaryStage) throws Exception {
		FxUI fxUI = FxUI.createInstance(boardConfig.getSize());
        final Game game = ...
         
        primaryStage.setScene(fxUI);
        primaryStage.setTitle("Diligent Squirrel");
        fxUI.getWindow().setOnCloseRequest(new EventHandler<KeyEvent>() {
            @Override
            public void handle(WindowEvent evt) {
                System.exit(-1);     
            }
        });
        primaryStage.show();   
        
        startGame(game);    
		
	}


	private void startGame(Game game) {
		
		InputReader inputReader = new InputReader(((GameImpl) game).getUi());
		
		inputReader.start();
		game.run();
		
	}
}
