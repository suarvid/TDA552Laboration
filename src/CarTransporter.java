import java.awt.*;
import java.util.Queue;

/**
 * Class CarTransporter, describes a Transportation vehicle for cars,
 * implements Loadable interface,
 * inherits basic Car functionality from class Car.
 *
 * @author Matte B, Arre S, Alle V
 * @version 1.0
 * @since 2018-11-07
 */
public class CarTransporter extends Car implements Loadable<Car> {

    //Delegation to CarLoader for Car-loading functionality.
    private final CarLoader carLoader = new CarLoader(15, 3000, 50000);
    //Keeps track of the ramp status.
    private boolean rampRaised;

    /**
     * @param nrDoors     specifies the number of doors the carTransporter should have.
     * @param color       specifies the color of the carTransporter
     * @param enginePower specifies the power of the carTransporter's engine
     * @param modelName   specifies the carTransporter's model name.
     * @param x           specifies initial x-position
     * @param y           specifis initial y-position
     */
    public CarTransporter(int nrDoors, Color color, double enginePower, String modelName, double x, double y) {
        super(nrDoors, color, enginePower, modelName, 7000, x, y);
        rampRaised = true;
    }

    /**
     * @return the list of cars currently loaded on the carTransporter.
     */
    public Queue<Car> getLoadedCars() {
        return carLoader.getLoadedCars();
    }

    /**
     * Raises the ramp to a raised poisition.
     * Ramp needs to be raised before moving the carTransporter.
     */
    public void raiseRamp() {
        rampRaised = true;
    }

    /**
     * Lowers the ramp to a lowered position.
     * Ramp needs to be lowered before loading a car onto the carTransporter.
     */
    public void lowerRamp() {
        if (isMoving()) {
            System.out.println("Stop moving before lowering the Ramp!");
        } else {
            rampRaised = false;
        }
    }

    /**
     * Using methods from carLoader; loads a specified car onto the carTransporter
     * <b>if</b> the ramp is lowered and we are not trying to load the carTransporter onto itself.
     *
     * @param carToLoad
     */
    public void load(Car carToLoad) {
        if (!rampRaised && carToLoad != this) {
            carLoader.load(carToLoad, this);
        }
    }

    /**
     * Unloads <b>every</b> car from the carTransporter if the ramp is lowered.
     */
    public void unloadAll() {
        if (!rampRaised) {
            carLoader.unloadAll();

        }else {
            System.out.println("Lower the ramp before unloading...");
        }
    }

    /**
     * Unloads <b>one</b> car from the carTransporter if the ramp is lowered.
     * Order is First In Last Out.
     */
    public void unload() {
        if (!rampRaised) {
            Car carToUnload = carLoader.getLoadedCars().getLast();
            carLoader.unload(carToUnload, this);
            switch (getDirection()) {
                case UP:
                    carToUnload.setPosition(getX(), getY() + 0.5);
                    break;
                case DOWN:
                    carToUnload.setPosition(getX(), getY() - 0.5);
                    break;
                case RIGHT:
                    carToUnload.setPosition(getX() - 0.5, getY());
                    break;
                case LEFT:
                    carToUnload.setPosition(getX() + 0.5, getY());
                    break;
            }
        } else {
            System.out.println("Lower the ramp before unloading...");
        }
    }

    /**
     * Moves the carTransporter if the ramp is raised.
     * Moves each car on the carTransporter with the it.
     *
     * Movement is based on currentSpeed which is increased with {@link Car#gas(double)}.
     */
    public void move() {
        if (rampRaised) {
            super.move();
            for (Car loadedCar : carLoader.getLoadedCars()) {
                loadedCar.setPosition(getX(), getY());
            }
        } else {
            System.out.println("Raise the ramp before driving!");
        }
    }

    /**
     * Checks wether the carTransporter is full or not
     * @return true if the carTransporter has reached it's maximum number of loaded Cars.
     */
    public boolean isFull() {
        return carLoader.isFull();
    }

    /**
     *
     * @return the specific speed factor for the carTransporter.
     */
    @Override
    public double speedFactor() {
        return (getEnginePower() / getTotalWeight()) * 50;
    }

    /**
     * Checks wether the ramp is raised or not.
     * @return true if the ramp is raised.
     */
    public boolean isRampRaised() {
        return rampRaised;
    }
}
