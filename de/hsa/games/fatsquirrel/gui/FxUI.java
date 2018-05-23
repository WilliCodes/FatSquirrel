package de.hsa.games.fatsquirrel.gui;

import de.hsa.games.fatsquirrel.UI;
import de.hsa.games.fatsquirrel.console.commands.Command;
import de.hsa.games.fatsquirrel.console.commands.GameCommandType;
import de.hsa.games.fatsquirrel.core.BoardView;
import de.hsa.games.fatsquirrel.core.XY;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class FxUI extends Scene implements UI {

	public final static int CELL_SIZE = 40;
	private Canvas boardCanvas;
	private Label msgLabel;
	
	public FxUI(Parent parent, Canvas boardCanvas, Label msgLabel) {
        super(parent);
        this.boardCanvas = boardCanvas;
        this.msgLabel = msgLabel;
    }
    
    @SuppressWarnings("unchecked")
	public static FxUI createInstance(XY boardSize) {
        Canvas boardCanvas = new Canvas(boardSize.x * CELL_SIZE, boardSize.y * CELL_SIZE);
        Label statusLabel = new Label();
        VBox top = new VBox();
        top.getChildren().add(boardCanvas);
        top.getChildren().add(statusLabel);
        statusLabel.setText("Hallo Welt");
        final FxUI fxUI = new FxUI(top, boardCanvas, statusLabel); 
        fxUI.setOnKeyPressed(
                new EventHandler<KeyEvent>() {
                	@Override
                   public void handle(KeyEvent keyEvent) {
                      System.out.println("Es wurde folgende Taste gedrückt: " + keyEvent.getCode() + " bitte behandeln!");
                      // TODO handle event 
                   }
                }
          );
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
		
		return null;
	}

    
}


