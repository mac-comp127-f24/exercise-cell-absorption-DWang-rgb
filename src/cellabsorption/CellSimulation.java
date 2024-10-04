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
        while (true) {
            Point canvasCenter = new Point(canvas.getWidth() / 2.0, canvas.getHeight() / 2.0);
            cell.moveAround(canvasCenter);
            cell.grow(0.02);

            canvas.draw();
            canvas.pause(10);
        }
    }

    private void populateCell() {
        cells = new ArrayList<>();
        double size = rand.nextInt(5) + 2;
        for (double i == 0; i <= 200; i ++) {}
        
        cell = new Cell(
            rand.nextDouble() * (canvas.getWidth() - size),
            rand.nextDouble() * (canvas.getWidth() - size),
            size,
            Color.getHSBColor(rand.nextFloat(), rand.nextFloat() * 0.5f + 0.1f, 1));
        Ellipse theShape = cell.getShape();
        canvas.add(theShape);
        
    }    
    
    private static double sqr(double x) {
        return x * x;

    }   
}
