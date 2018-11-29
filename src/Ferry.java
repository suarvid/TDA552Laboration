import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Car Transporter
 * Class representing a ferry.
 * Implements a CarLoader object through delegation.
 *
 * @author Paggan, Atto, MatteB
 * @version 1.0
 * @since 2018-11-22
 */
public class Ferry extends Vehicle implements Loadable<Car> {

    private final CarLoader carLoader = new CarLoader(30, 20000, 90000);

    private boolean engineOn = false;
    private int enginePower;

    /**
     * @param modelname the name of the ferry model
     * @param color     color of the ferry
     * @param x         X-position of the ferry
     * @param y         Y-position of the ferry
     */
    public Ferry(String modelname, int enginePower, Color color, double x, double y) {
        super(modelname, color, 8000, x, y);
        this.enginePower = enginePower;
    }

    /**
     * @return List of Car currently loaded on Ferry, in form of a Queue
     */
    public LinkedList<Car> getLoadedCars() {
        return carLoader.getLoadedCars();

    }

    /**
     * Moves the Ferry and all the Cars currently loaded on it.
     */
    @Override
    public void move() {
        super.move();
        for (Car car : carLoader.getLoadedCars()) {
            car.setPosition(getX(), getY());
        }
    }

    /**
     * Loads a target Car-object on to the Ferry.
     * Requires that the target Car is not already loaded on a CarLoader.
     * @param carToLoad Target Car-object to load on Ferry, adding it to the list of loaded Cars.
     */
    public void load(Car carToLoad) {
        if (!isMoving())
            carLoader.load(carToLoad, this);
    }


    /**
     *Decreases the speed of the Ferry.
     * @param amount how much to decrease the speed with.
     */
    private void decrementSpeed(double amount) {
        setCurrentSpeed(getCurrentSpeed() - amount * (enginePower / 10000));
    }

    /**
     * Increases the speed of the ferry.
     * @param amount how much to decrease the speed with.
     */
    private void incrementSpeed(double amount) {
        setCurrentSpeed(getCurrentSpeed() + amount * (enginePower / 1000));
    }

    /**
     * Unloads a car from the ferry.
     */
    public void unload() {
        carLoader.unload(carLoader.getLoadedCars().getFirst(), this);
    }

    /**
     * Unloads all cars from the ferry.
     */
    public void unloadAll() {
        carLoader.unloadAll(this);
    }

    /**
     *
     * @return Returns full if the Ferry has reached its loading capacity.
     */
    public boolean isFull() {
        return carLoader.isFull();
    }

    /**
     * Starts the engine of the Ferry
     */
    public void startEngine() {
        engineOn = true;
    }

    /**
     * Stops the engine of the Ferry.
     */
    public void stopEngine() {
        engineOn = false;
    }

    /**
     * Increases the speed of the ferry by amount (in consideration to enginePower)
     * @param amount the amount of speed increase.
     */
    public void gas(double amount) {
        if (amount > 1.0) {
            amount = 1.0;
        }
        if (0 < amount && engineOn) {
            incrementSpeed(amount);
        }
    }
}
