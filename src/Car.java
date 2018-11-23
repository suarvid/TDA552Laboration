import java.awt.*;
import java.util.ArrayList;

/**
 * Abstract class Car, implements interface Movable. Also extends Vehicle
 * Specifies the general features and the basic functionality of a car.
 *
 * @author Matte B, Arre S, Alle V
 * @version 1.0
 * @since 2018-11-07
 */


public abstract class Car extends Vehicle implements Movable {

    private int nrDoors;
    private double enginePower;
    private boolean engineOn = false;
    private boolean onTransport = false;


    /**
     * @param nrDoors     number of doors on the Car.
     * @param color       color of the Car.
     * @param enginePower maximum speed of the Car.
     * @param modelName   name of the Car model.
     */
    Car(int nrDoors, Color color, double enginePower, String modelName, int totalWeight, double x, double y) {
        super(modelName, color, totalWeight, x, y);
        this.nrDoors = nrDoors;
        this.enginePower = enginePower;
    }


    /**
     * @return gets the car's number of doors
     */
    public int getNrDoors() {
        return nrDoors;
    }

    /**
     * @return gets the engine power (maximum speed of the car)
     */
    public double getEnginePower() {
        return enginePower;
    }


    /**
     * Starts engine if it's not already on
     * Sets currentspeed != 0
     */

    public void startEngine() {
        if (!engineOn) {
//            setCurrentSpeed(0.1);
            engineOn = true;
        }
    }

    /**
     * Prints Car's current position in console window.
     */


    @Override
    public String toString() {
        String name = this.getClass().toString();
        return "Model: " + name + " | nDoors: " + nrDoors + " | " + getColor() + " | Engine-power: " + enginePower;
    }

    /**
     * Stops the engine.
     * If current speed > 0, reduce speed until current speed = 0.
     */

    public void stopEngine() {
        while (isMoving()) {
            decrementSpeed(10);
        }
        engineOn = false;
    }


    /**
     * Set new direction to the left of Car's current direction
     */

    @Override
    public void turnLeft() {
        if (engineOn) {
            super.turnLeft();
        }
    }

    /**
     * Set new direction to the right of Car's current direction.
     */
    @Override
    public void turnRight() {
        if (engineOn) {
            super.turnRight();
        }
    }

    /**
     * moves car forward with currentSpeed in current direction.
     */
    @Override
    public void move() {
        if (engineOn && !onTransport) {
            super.move();
        } else {
            System.out.println("Can't move, either engine is off, or Car is on Transport!");
        }

    }

    /**
     * @return
     */
    public abstract double speedFactor();


    /**
     * Increments speed by given amount
     *
     * @param amount of increase in speed
     */

    private void incrementSpeed(double amount) {
        setCurrentSpeed(getCurrentSpeed() + speedFactor() * amount);
        if (getCurrentSpeed() > enginePower) {
            setCurrentSpeed(enginePower);
        }
    }

    /**
     * Decrements speed by given amount
     *
     * @param amount of decrease in speed
     */

    private void decrementSpeed(double amount) {
        setCurrentSpeed(getCurrentSpeed() - speedFactor() * amount);
        if (getCurrentSpeed() < 0) {
            setCurrentSpeed(0);
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

    /**
     * Changes boolean onTransport to true, enables each Car to internally keep track of if they are loaded on a transport.
     */

    void setOnTransport() {
        onTransport = true;
    }

    /**
     * Changes boolean onTransport to false, enables each Car to internally keep track of if they are loaded on a transport.
     */

    void takeOffTransport() {
        onTransport = false;
    }

    /**
     * @return Boolean onTransport, affects if Car can move or not.
     */

    public boolean isOnTransport() {
        return onTransport;
    }

    /**
     * @return Boolean engineOn, affects if Car is able to move.
     */

    public boolean isEngineOn() {
        return engineOn;
    }

}
