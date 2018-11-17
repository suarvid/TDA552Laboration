import java.awt.*;
import java.util.ArrayList;

/**
 * Abstract class Car, implements interface Movable.
 * Specifies the general features and the basic functionality of a car.
 *
 *
 * @author Matte B, Arre S, Alle V
 * @version 1.0
 * @since 2018-11-07
 */


public abstract class Car implements Movable {

    public enum Directions {LEFT, RIGHT, UP, DOWN}

    private String modelname;
    private int nrDoors;
    private Color color;
    private double enginePower;
    private double currentSpeed;
    private Directions direction = Directions.UP;
    private double x;
    private double y;
    private boolean engineOn = false;


    /**
     *
     * @param nrDoors number of doors on the Car.
     * @param color color of the Car.
     * @param enginePower maximum speed of the Car.
     * @param modelname name of the Car model.
     */
    public Car(int nrDoors, Color color, double enginePower, String modelname) {
        this.nrDoors = nrDoors;
        this.color = color;
        this.enginePower = enginePower;
        this.modelname = modelname;
    }


    /**
     * @return gets the current direction of the car
     */
    public Directions getDirection() {
        return direction;
    }

    /**
     *
     * @return gets the car's number of doors
     */
    public int getNrDoors() {
        return nrDoors;
    }

    /**
     *
     * @return gets the engine power (maximum speed of the car)
     */
    public double getEnginePower() {
        return enginePower;
    }

    /**
     *
     * @return gets the current speed of the car
     */
    public double getCurrentSpeed() {
        return currentSpeed;
    }


    /**
     *
     * @return gets the color of the car
     */
    public Color getColor() {
        return color;
    }

    /**
     * Sets the color of the car to specified color
     * @param color specified color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     *
     * @return gets the x position of the car
     */
    public double getX(){
        return x;
    }
    /**
     *
     * @return gets the y position of the car
     */
    public double getY() {
        return y;
    }

    /**
     * Starts engine if it's not already on
     * Sets currentspeed != 0
     */

    public void startEngine() {
        if (engineOn == false) {
            currentSpeed = 0.1;
            engineOn = true;
        }
    }

    /**
     * Prints cars current position in console window.
     */

    public void printPosition() {
        System.out.println("Car is at position: " + "x: " + x + ", y: " + y);
    }

    @Override
    public String toString() {
        String name = this.getClass().toString();
        return "Model: " + name + " | nDoors: " + nrDoors + " | " + color + " | Engine-power: " + enginePower;
    }

    /**
     * Stops the engine.
     * If current speed > 0, reduce speed until current speed = 0.
     */

    public void stopEngine() {
        while (currentSpeed > 0) {
            decrementSpeed(10);
        }
        engineOn = false;
    }


    /**
     * Set new direction to the left of current direction
     */

    @Override
    public void turnLeft() {
        if (engineOn) {
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
    }

    /**
     * Set new direction to the right of current direction.
     */
    @Override
    public void turnRight() {
        if (engineOn) {
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
    }

    /**
     * moves car forward with currentSpeed in current direction.
     */
    @Override
    public void move() {
        if (engineOn) {
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

    }

    protected abstract double speedFactor();

    /**
     * Increments speed by given amount
     *
     * @param amount of increase in speed
     */

    private void incrementSpeed(double amount) {
        currentSpeed = getCurrentSpeed() + speedFactor() * amount;
        if (currentSpeed > enginePower) {
            currentSpeed = enginePower;
        }
    }

    /**
     * Decrements speed by given amount
     *
     * @param amount of decrease in speed
     */

    private void decrementSpeed(double amount) {
        currentSpeed = getCurrentSpeed() - speedFactor() * amount;
        if (currentSpeed < 0) {
            currentSpeed = 0;
        }
    }

    /**
     * Increases speed by a limited factor, only applies amount in interval 0 - 1
     *
     * @param amount 0 - 1, the limiting factor to how much the speed will increase.
     */

    public void gas(double amount) {
        if (amount > 1.0) {
            amount = 1.0;
        }
        if (0 < amount) {
            incrementSpeed(amount);
        }
    }

    /**
     * Decreases speed by a limited factor, only applies amount in interval 0 - 1
     *
     * @param amount 0 - 1, the limiting factor to how much the speed will decrease.
     */
    public void brake(double amount) {
        if (amount > 1.0) {
            amount = 1.0;
        }
        if (0 < amount) {
            decrementSpeed(amount);
        }
    }
}
