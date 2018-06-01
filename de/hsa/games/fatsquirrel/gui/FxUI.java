package de.hsa.games.fatsquirrel.gui;

import java.util.Optional;
import java.util.logging.Logger;

import de.hsa.games.fatsquirrel.UI;
import de.hsa.games.fatsquirrel.console.commands.Command;
import de.hsa.games.fatsquirrel.console.commands.CommandTypeInfo;
import de.hsa.games.fatsquirrel.console.commands.GameCommandType;
import de.hsa.games.fatsquirrel.core.BoardView;
import de.hsa.games.fatsquirrel.core.XY;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class FxUI extends Scene implements UI {

	public final static int CELL_SIZE = 40;
	private Canvas boardCanvas;
	private Label msgLabel;
	private static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	private static Command command = null;
	
	public FxUI(Parent parent, Canvas boardCanvas, Label msgLabel) {
        super(parent);
        this.boardCanvas = boardCanvas;
        this.msgLabel = msgLabel;
        logger.fine("FxUI created");
    }
    
    @SuppressWarnings("unchecked")
	public static FxUI createInstance(XY boardSize) {
        Canvas boardCanvas = new Canvas(boardSize.x * CELL_SIZE, boardSize.y * CELL_SIZE);
        logger.fine("FxUI: canvas created");
        Label statusLabel = new Label();
        logger.fine("FxUI: label created");
        VBox top = new VBox();
        logger.fine("FxUI: vbox created");
        top.getChildren().add(boardCanvas);
        top.getChildren().add(statusLabel);
        statusLabel.setText("Hallo Welt");
        final FxUI fxUI = new FxUI(top, boardCanvas, statusLabel); 
        fxUI.setOnKeyPressed(
                new EventHandler<KeyEvent>() {
                	@Override
                   public void handle(KeyEvent keyEvent) {
                      System.out.println("Es wurde folgende Taste gedr�ckt: " + keyEvent.getCode() + " bitte behandeln!");
                      switch (keyEvent.getCode()) {
                      	case W:
                      		command = new Command(GameCommandType.UP, null);
                      		break;
                      	case A:
                      		command = new Command(GameCommandType.LEFT, null);
                      		break;
                      	case S:
                      		command = new Command(GameCommandType.DOWN, null);
                      		break;
                      	case D:
                      		command = new Command(GameCommandType.RIGHT, null);
                      		break;
                      	case H:
                      		command = null;
                      		displayHelp();
                      		break;
                      	case SPACE:
                      		command = null;
                      		displaySpawnMiniDialog();
                      		break;
                      	default:
                      		break;
                      }
                      keyEvent.consume();
                   }

					private void displaySpawnMiniDialog() {
						logger.info("opened spanwMiniDialog");
						TextInputDialog dialog = new TextInputDialog("100");
                  		dialog.setTitle("Spawn MiniSquirrel");
                  		dialog.setHeaderText("Spawning MiniSquirrel...");
                  		dialog.setContentText("Please enter MiniSquirrel's Energy:");

                  		Optional<String> result = dialog.showAndWait();
                  		if (result.isPresent()){
                  			int energy = Integer.parseInt(result.get());
                  			command = new Command(GameCommandType.SPAWN_MINI, new Object[] {energy});
                  			logger.info("MiniSquirrel spawned with " + energy);
                  		}
					}
                }
          );
        logger.info("closed spawnMiniDialog");
        return fxUI;
    }

    

    @Override
    public void render(final BoardView view) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                repaintBoardCanvas(view);            
            }      
        });  
    }
    
    private void repaintBoardCanvas(BoardView view) {
        GraphicsContext gc = boardCanvas.getGraphicsContext2D();
        gc.clearRect(0, 0, boardCanvas.getWidth(), boardCanvas.getHeight());
        XY viewSize = view.getSize();
        
        message(Integer.toString(view.getMasterSquirrelEnergy()));

       for(int a = 0; a < viewSize.x; a++) {
    	   for(int b = 0; b < viewSize.y; b++) {
    		   switch(view.getEntityType(a, b)) {
			case BadBeast:
				gc.setFill(Color.RED);
				gc.fillOval(a*CELL_SIZE, b*CELL_SIZE, CELL_SIZE, CELL_SIZE);
				break;
			case BadPlant:
				gc.setFill(Color.RED);
				gc.fillRect(a*CELL_SIZE, b*CELL_SIZE, CELL_SIZE, CELL_SIZE);
				break;
			case Empty:
				break;
			case GoodBeast:
				gc.setFill(Color.GREEN);
				gc.fillOval(a*CELL_SIZE, b*CELL_SIZE, CELL_SIZE, CELL_SIZE);
				break;
			case GoodPlant:
				gc.setFill(Color.GREEN);
				gc.fillRect(a*CELL_SIZE, b*CELL_SIZE, CELL_SIZE, CELL_SIZE);
				break;
			case HandOperatedMasterSquirrel:
				gc.setFill(Color.BLUE);
				gc.fillOval(a*CELL_SIZE, b*CELL_SIZE, CELL_SIZE, CELL_SIZE);
				break;
			case MasterSquirrel:
				gc.setFill(Color.GREY);
				gc.fillOval(a*CELL_SIZE, b*CELL_SIZE, CELL_SIZE, CELL_SIZE);
				break;
			case MiniSquirrel:
				gc.setFill(Color.PURPLE);
				gc.fillOval(a*CELL_SIZE, b*CELL_SIZE, CELL_SIZE, CELL_SIZE);
				break;
			case Wall:
				gc.setFill(Color.YELLOW);
				gc.fillRect(a*CELL_SIZE, b*CELL_SIZE, CELL_SIZE, CELL_SIZE);
				break;
			default:
				break;
    		   
    		   }
    	   }
       }
        
    }
    
    public static void displayHelp() {
    	logger.info("opened help");
    	Alert alert = new Alert(AlertType.INFORMATION);
  		alert.setTitle("Help");
  		alert.setHeaderText("How To Play");
  		alert.setContentText("Movement: WASD\nYou are the blue dot, avoid Walls (Yellow Rectangles), BadBeasts and BadPlants (Red Circles and Rectangles) and eat GoodBeasts and GoodPlants (Green Circles and Rectangles");

  		alert.showAndWait();
  		logger.info("closed help");
    }
    

    public void message(final String msg) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                msgLabel.setText(msg);            
            }      
        });         
    }

	@Override
	public Command getCommand() {
		
		if (command != null && (command.commandTypeInfo == GameCommandType.SPAWN_MINI || command.commandTypeInfo == GameCommandType.HELP)) {
			Command tmpCommand = command;
			command = null;
			return tmpCommand;
		}
		
		return command;
	}

    
}

