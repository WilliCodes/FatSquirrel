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
	public static String configFile = "boardConfig.properties";
	private static BoardConfig boardConfig = new BoardConfig(configFile);
	private static Board board;
	private static State state;
	
	public static GameMode gameMode = boardConfig.getGameMode();

	
	public static void main(String[] args) throws Exception {
		
		MyLogger.setup();
		board = new Board(boardConfig);
		state = new State(board, boardConfig);
    
		boolean gui = true;
		
		if (!gui) {
			logger.info("consolemode started");
			GameImpl game = new GameImpl(state);
			InputReader inputReader = new InputReader(game.getUi());
			inputReader.start();
			startGame(game);
		} else {
			Application.launch(args);
		}
	}

	
	public void start(Stage primaryStage) throws Exception {
		
		logger.info("guimode started");
		FxUI fxUI = FxUI.createInstance(new XY (boardConfig.getWidth(), boardConfig.getHeight()));
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
			
		}, 1000);
	}
}
