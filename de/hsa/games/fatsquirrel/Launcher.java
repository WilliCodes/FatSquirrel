package de.hsa.games.fatsquirrel;


import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

import de.hsa.games.fatsquirrel.console.GameImpl;
import de.hsa.games.fatsquirrel.core.*;
import de.hsa.games.fatsquirrel.gui.FxUI;
import de.hsa.games.fatsquirrel.gui.GameUi;
import de.hsa.games.fatsquirrel.logger.MyLogger;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Launcher extends Application {
	
	private static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	private static BoardConfig boardConfig = new BoardConfig();
	private static Board board;
	private static State state;
	
	public enum GameMode {
		SINGLE_PLAYER, MULTI_PLAYER, AI_GAME;
	}
	public static final GameMode gameMode = boardConfig.gameMode;

	
	public static void main(String[] args) throws Exception {
		
		MyLogger.setup();
		board = new Board(boardConfig);
		state = new State(board);
    
		boolean gui = true;
		
		if (!gui) {
			logger.info("consolemode started");
			GameImpl game = new GameImpl(state);
			InputReader inputReader = new InputReader(game.getUi());
			inputReader.start();
			game.run();
		} else {
			Application.launch(args);
		}
	}

	
	public void start(Stage primaryStage) throws Exception {
		
		logger.info("guimode started");
		FxUI fxUI = FxUI.createInstance(boardConfig.getSize());
        final Game game = new GameUi(state, fxUI);
         
        primaryStage.setScene(fxUI);
        primaryStage.setTitle("Overweight Squirrel");
        
        fxUI.getWindow().setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent evt) {
                System.exit(-1);     
            }
        });
        primaryStage.show();   
        
        startGame(game);    
	}


	private static void startGame(Game game) throws InterruptedException {
		
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				logger.info("game started");
				game.run();	
			}
			
		}, 3000);
	}
}
