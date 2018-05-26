package de.hsa.games.fatsquirrel;


import java.util.Timer;
import java.util.TimerTask;

import de.hsa.games.fatsquirrel.console.GameImpl;
import de.hsa.games.fatsquirrel.core.*;
import de.hsa.games.fatsquirrel.gui.FxUI;
import de.hsa.games.fatsquirrel.gui.GameUi;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Launcher extends Application {
	
	public static boolean printDebugInfo = false;
	private static BoardConfig boardConfig = new BoardConfig();
	private static Board board = new Board(boardConfig);
	private static State state = new State(board);
	
	public static void main(String[] args) throws Exception {
		
		boolean gui = true;
		
		
		if(!gui) {
			GameImpl game = new GameImpl(state);
			InputReader inputReader = new InputReader(game.getUi());
			inputReader.start();
			startGame(game);
		}else {
		Application.launch(args);
		}
	}

	
	public void start(Stage primaryStage) throws Exception {
		
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
				game.run();
				
			}
			
		}, 3000);
		
	}

}
