package cellabsorption;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.Point;

@SuppressWarnings("SameParameterValue")
public class CellSimulation {
    

    private List<Cell> cells;
    private CanvasWindow canvas;
    private Random rand = new Random();
    
    

    public static void main(String[] args) {
        new CellSimulation();
    }

    public CellSimulation() {
        canvas = new CanvasWindow("Cell Absorption", 800, 800);
        populateCell();

        //noinspection InfiniteLoopStatement
        runThePoints();
        
    }

    private void populateCell() {
        cells = new ArrayList<>();
        double size = rand.nextInt(5) + 2;
          for (int i = 0; i <= 200; i++) {
              Cell cell = new Cell(
              rand.nextDouble() * (canvas.getWidth() - size),
              rand.nextDouble() * (canvas.getWidth() - size),
              size,
              Color.getHSBColor(rand.nextFloat(), rand.nextFloat() * 0.5f + 0.1f, 1));
          Ellipse theShape = cell.getShape();
          canvas.add(theShape);
          cells.add(cell);
      }
      System.out.println(cells);
    }    
    
    private static double sqr(double x) {
        return x * x;

    }

    private void runThePoints() {
            for (int n = 0; n >= 0; n++) { 
                for (Cell cell: cells) {
                    System.out.println(cell);
            Point canvasCenter = new Point(canvas.getWidth() / 2.0, canvas.getHeight() / 2.0);
            cell.moveAround(canvasCenter);
            
            
        }
        handleCellInteraction();
        canvas.draw();
        canvas.pause(10);
      }
    }

    private void handleCellInteraction() {
            for (int i = 0; i < cells.size(); i++) {
                Cell cell0 = cells.get(i);
                for (int j = i + 1; j < cells.size(); j++) {
                    Cell cell1 = cells.get(j);
                  // TODO: insert call here to make cell0 interact with cell1
                  cell0.interactWith(cell1);
              }
          }
      }
}
