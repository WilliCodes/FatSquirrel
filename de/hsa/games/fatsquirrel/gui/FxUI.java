package de.hsa.games.fatsquirrel.gui;

import de.hsa.games.fatsquirrel.UI;
import de.hsa.games.fatsquirrel.console.commands.Command;
import de.hsa.games.fatsquirrel.console.commands.CommandScanner;
import de.hsa.games.fatsquirrel.console.commands.GameCommandType;
import de.hsa.games.fatsquirrel.core.BoardView;
import de.hsa.games.fatsquirrel.core.XY;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class FxUI extends Scene implements UI {

	public final static int CELL_SIZE = 5;
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
                new EventHandler() {
                   public void handle(Event keyEvent) {
                      System.out.println("Es wurde folgende Taste gedrückt: " + ((KeyEvent) keyEvent).getCode() + " bitte behandeln!");
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

        // dummy for rendering a board snapshot, TODO: change it!
        gc.fillText("Where are the beasts?", 100, 100);
        gc.setFill(Color.RED);
        gc.fillOval(150, 150, 50, 50);
        
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void commandLoop() {
		// TODO Auto-generated method stub
		
	}

    
}


