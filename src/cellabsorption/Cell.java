package cellabsorption;

import java.awt.Color;

import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.Point;

public class Cell {
    public static final double
        WIGGLINESS = 0.2,
        WANDER_FROM_CENTER = 60000;

    private double radius;
    private double direction;
    private Ellipse shape;
    

    public Cell(double x, double y, double radius, Color color) {
        shape = new Ellipse(x, y, radius * 2, radius * 2);
        shape.setFillColor(color);
        this.radius = radius;
        direction = normalizeRadians(Math.random() * Math.PI * 2);
    }

    public void grow(double amount) {
        setRadius(radius + amount);
    }

    private void setRadius(double newRadius) {
        if (newRadius < 0) {
            newRadius = 0;
        }
        radius = newRadius;
        Point previousCenter = shape.getCenter();
        shape.setSize(new Point(newRadius * 2, newRadius * 2));
        shape.setCenter(previousCenter);
    }

    public void moveAround(Point centerOfGravity) {
        shape.moveBy(Math.cos(direction), Math.sin(direction));

        double distToCenter = shape.getCenter().distance(centerOfGravity);
        double angleToCenter = centerOfGravity.subtract(shape.getCenter()).angle();
        double turnTowardCenter = normalizeRadians(angleToCenter - direction);

        direction = normalizeRadians(
            direction
                + (Math.random() - 0.5) * WIGGLINESS
                + turnTowardCenter * Math.tanh(distToCenter / WANDER_FROM_CENTER));
    }


    private static double normalizeRadians(double theta) {
        double pi2 = Math.PI * 2;
        return ((theta + Math.PI) % pi2 + pi2) % pi2 - Math.PI;
    }

    public Ellipse getShape() {
        return shape;
    }

    public Point getCenter() {
        return shape.getCenter();
    }

    public void interactWith(Cell otherCell) {
        if (radius == 0 || otherCell.radius == 0) {
            return;
        }
        if (overlapAmount(otherCell) < 0) {
            return;
        }

        if (radius > otherCell.radius) {
            absorb(otherCell);
        } else {
            otherCell.absorb(this);
        }
    }

    private double overlapAmount(Cell otherCell) {
        return radius + otherCell.radius - getCenter().distance(otherCell.getCenter());
    }

    private void absorb(Cell otherCell) {
        double d = getCenter().distance(otherCell.getCenter());
        double a = sqr(radius) + sqr(otherCell.radius);
        double newRadius = (d + Math.sqrt(2 * a - sqr(d))) / 2;

        setRadius(newRadius);
        otherCell.setRadius(d - newRadius);
    }

    private static double sqr(double x) {
        return x * x;
    }
    
}