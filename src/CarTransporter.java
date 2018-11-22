import java.awt.*;
import java.util.Queue;

/**<h1>Car Transporter</h1>
 * Class representing the implementation of a car-carrying truck.
 * Implements a <code>CarLoader</code> object through delegation.
 *
 * @author Paggan, Atto, MatteB
 * @version 1.0
 * @since 2018-11-22
 */
public class CarTransporter extends Car implements Loadable<Car> {

    private final CarLoader carLoader = new CarLoader(15, 3000, 50000);

    private boolean rampRaised;

    /**
     *
     * @param nrDoors The number of Doors on the CarTransporter.
     * @param color The Color of the CarTransporter.
     * @param enginePower Engine Power of the CarTransporter, represents the maximum speed it can have.
     * @param modelName The model name of the CarTransporter.
     * @param x The current x-coordinate of the CarTransporter.
     * @param y The current y-coordinate of the CarTransporter.
     */
    public CarTransporter(int nrDoors, Color color, double enginePower, String modelName, double x, double y) {
        super(nrDoors, color, enginePower, modelName, 7000, x, y);
        rampRaised = true;
    }

    /**
     * Raises ramp of <code>CarTransporter</code>, allowing it to load <code>Car</code>s but disables the ability to move.
     */

    public void raiseRamp() {
        rampRaised = true;
    }

    /**
     *
     * @return List of <class>Car</class>s currently loaded on <class>CarTransporter</class>, in form of a Queue
     */

    public Queue<Car> getLoadedCars() {
        return carLoader.getLoadedCars();
    }

    /**
     * If CarTransporter is not moving, lowers the ramp.
     * Lowering the ramp allows the CarTransporter to load Cars, adding them to the Queue of currently loaded cars.
     * CarTransporter is not allowed to move while its ramp is lowered.
     */

    public void lowerRamp() {
        if (isMoving()) {
            System.out.println("Stop moving before lowering the Ramp!");
        } else {
            rampRaised = false;
        }
    }

    /**
     * Loads a target Car-object on to the CarTransporter.
     * Requires that the CarTransporters ramp is lowered and that the target Car is not already loaded on a transport.
     * @param carToLoad Target Car-object to load on Transporter, adding it to the list of loaded Cars.
     */

    public void load(Car carToLoad) {
        if (!rampRaised && carToLoad != this) {
            carLoader.load(carToLoad, this);
        }
    }

    /**
     * Unloads all Cars currently loaded on the CarTransporter.
     */

    public void unloadAll() {
        carLoader.unloadAll();
    }

    /**
     * Unloads the Car latest loaded on the CarTransporter, according to a Last on - First off principle.
     * Unloaded Cars position is updated according to the CarTransporters direction when unloading.
     *
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
        }else{
            System.out.println("Lower the ramp before unloading...");
        }
    }

    /**
     * Moves the CarTransporter and all the Cars currently loaded on it.
     * Requires that the CarTransporters ramp is raised before moving.
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
     * * @return Boolean reflecting if the CarTransporter has loaded the maximum amount of Cars it can carry.
     */

    public boolean isFull() {
        return carLoader.isFull();
    }

    /**
     *
     * @return Factor used to increase speed when calling method gas(). Is depends on how much is currently loaded on the CarTransporter.
     */

    @Override
    public double speedFactor() {
        return (getEnginePower() / getTotalWeight()) * 50;
    }

    public boolean isRampRaised() {
        return rampRaised;
    }
}
