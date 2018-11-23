import java.awt.*;

/**
 * Abstract class Vehicle, implements interface Movable.
 * Specifies the general features and the basic functionality of a Vehicle.
 *
 * @author Matte B, Arre S, Alle V
 * @version 1.0
 * @since 2018-11-07
 */
public abstract class Vehicle implements Movable {

    public enum Directions {LEFT, RIGHT, UP, DOWN}

    private String modelname;
    private Color color;
    private int totalWeight;
    private double currentSpeed;
    private Directions direction = Directions.UP;
    private double x;
    private double y;

    /**
     *
     * @param modelname mode name of the vehicle.
     * @param color color of the vehicle.
     * @param totalWeight weight of the vehicle.
     * @param x initial x-position of the vehicle.
     * @param y initial y-position of the vehicle.
     */
    public Vehicle(String modelname, Color color, int totalWeight, double x, double y) {
        this.modelname = modelname;
        this.color = color;
        this.totalWeight = totalWeight;
        this.currentSpeed = 0;
        this.x = x;
        this.y = y;
    }

    /**
     * Moves the vehicle in the current direction at the current speed.
     *
     */
    public void move() {
        switch (direction) {
            case UP:
                y = y - currentSpeed;
                break;
            case LEFT:
                x = x - currentSpeed;
                break;
            case DOWN:
                y = y + currentSpeed;
                break;
            case RIGHT:
                x = x + currentSpeed;
                break;
        }
    }

//    public abstract void brake(double amount);


    /**
     *Turns the vehicle 90 degrees counter-clockwise
     */
    public void turnLeft() {
        switch (direction) {
            case UP:

                direction = Directions.LEFT;
                break;
            case LEFT:
                direction = Directions.DOWN;
                break;
            case DOWN:
                direction = Directions.RIGHT;
                break;
            case RIGHT:
                direction = Directions.UP;
                break;
        }
    }

    /**
     * Turns the vehicle 90 degrees clockwise
     */
    public void turnRight() {
        switch (direction) {
            case UP:
                direction = Directions.RIGHT;
                break;
            case LEFT:
                direction = Directions.UP;
                break;
            case DOWN:
                direction = Directions.LEFT;
                break;
            case RIGHT:
                direction = Directions.DOWN;
                break;
        }
    }

    /**
     * @return gets the current speed of the vehicle
     */
    double getCurrentSpeed() {
        return currentSpeed;
    }

    /**
     * sets the current speed of the vehicle
     * @param speed speed to be set.
     */
    void setCurrentSpeed(double speed){
        currentSpeed = speed;
    }

    /**
     * checks if the vehicle is moving
     * @return true if vehicle is moving
     */
    boolean isMoving() {
        return currentSpeed > 0;
    }

    /**
     * Prints current speed.
     */
    public void printCurrentSpeed() {
        System.out.println(currentSpeed);
    }

    /**
     * Prints current position.
     */
    public void printPosition() {
        System.out.println(modelname + " is at position: " + "x: " + x + ", y: " + y);
    }

    /**
     * Sets the position of the Vehicle.
     * @param x x-position to set.
     * @param y y-position to set.
     */
    void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return gets the x position of the vehicle
     */
    double getX() {
        return x;
    }

    /**
     * @return gets the y position of the vehicle
     */
    double getY() {
        return y;
    }



    /**
     * @return gets the color of the vehicle
     */
    public Color getColor() {
        return color;
    }

    /**
     * Sets the color of the vehicle to specified color
     *
     * @param color specified color
     */
    void setColor(Color color) {
        this.color = color;
    }


    /**
     * Gets the total weight of the vehicle
     */
    int getTotalWeight() {
        return totalWeight;
    }

    /**
     * Sets the total weight of the vehicle
     * @param weight weight to be set
     */
    void setTotalWeight(int weight) {
        this.totalWeight = weight;
    }


    /**
     * @return gets the current direction of the vehicle
     */
    Directions getDirection() {
        return direction;
    }

//    private void incrementSpeed(double amount) {
//        currentSpeed += amount;
//    }
//
//    private void decrementSpeed(double amount) {
//        currentSpeed -= amount;
//    }


}
