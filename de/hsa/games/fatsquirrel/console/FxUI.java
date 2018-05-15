package de.hsa.games.fatsquirrel.console;

import de.hsa.games.fatsquirrel.UI;
import de.hsa.games.fatsquirrel.console.commands.Command;
import de.hsa.games.fatsquirrel.core.BoardView;
import de.hsa.games.fatsquirrel.core.XY;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

public class FxUI implements UI {

	public FxUI(Parent parent, Canvas boardCanvas, Label msgLabel) {
        super(parent);
        this.boardCanvas = boardCanvas;
        this.msgLabel = msgLabel;
    }
    
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

        // dummy for rendering a board snapshot, TODO: change it!
        gc.fillText("Where are the beasts?", 100, 100);
        gc.setFill(Color.RED);
        gc.fillOval(150, 150, 50, 50);
        ...
    }
    

    @Override
    public void message(final String msg) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                msgLabel.setText(msg);            
            }      
        });         
    }
}

}
