import java.awt.*;

/**
 * Class representing a truck, Scania.
 *
 * @author Alexander, Arvid, Matilda
 * @version 1.0
 * @since 2018-11-22
 */
public class Scania extends Car implements Loadable<Integer> {
    private static int loadCapacity = 10000;

    private double flatbedAngle;
    private double currentLoad;

    /**
     *
     * @param nrDoors Number of doors on the Scania,
     * @param color Color of the Scania.
     * @param enginePower Engine Power of the Scania, affects acceleration and specifies maximum speed.
     * @param x The x-coordinate of the Scanias position.
     * @param y The y-coordinate of the Scanias position.
     */
    public Scania(int nrDoors, Color color, double enginePower, double x, double y) {
        super(nrDoors, color, enginePower, "Scania", 8000, x, y);
        flatbedAngle = 0;
        currentLoad = 0;
    }

    /**
     *
     * @return current Angle of the Scanias Flatbed. Interval of 0-70 degrees.
     */
    public double getFlatbedAngle() {
        return flatbedAngle;
    }

    /**
     * @param loadWeight Weight of the unspecified object(s) being loaded.
     */
    public void load(Integer loadWeight) {
        if (currentLoad + loadWeight <= loadCapacity) {
            currentLoad += loadWeight;
            setTotalWeight(getTotalWeight() + loadWeight);
        } else {
            System.out.println("Can't load the truck with that much weight!");
        }
    }

    /**
     * @param weight Weight of the unspecified object(s) being unloaded.
     */
    public void unload(Integer weight) {
        if (currentLoad - weight >= 0) {
            currentLoad -= weight;
            setTotalWeight(getTotalWeight() - weight);
        } else {
            System.out.println("Truck is not loaded with that much weight! Can't unload more than: " + currentLoad + " kg");
        }
    }

    /**
     * Empties the Scanias entire load.
     */
    public void unload() {
        currentLoad = 0;
    }

    /**
     * @return Boolean representing if Scanias load is at maximum weight-capacity.
     */

    public boolean isFull() {
        if (currentLoad == loadCapacity) {
            return true;
        }
        return false;
    }

    /**
     * Changes the Scanias position, depending on its current speed.
     * Moving requires the flatbedAngle to be 0.
     */

    @Override
    public void move() {
        if (flatbedAngle == 0) {
            super.move();
        } else {
            System.out.println("Lower flatbed before driving!");
        }
    }

    /**
     * Increases the angle of the flatbed by 1.
     * Requires that the Scania is not moving, and that the current angle is between 0 - 70 degrees.
     */

    public void raiseFlatbed() {
        if (isMoving()) {
            System.out.println("Flatbed can only be raised when truck is not moving!");
        } else {
            if (flatbedAngle < 70) {
                flatbedAngle += 1.0;
            } else {
                flatbedAngle = 70.0;
                System.out.println("Flatbed is raised to the max!");
            }
        }
    }

    /**
     * Reduces the angle of the flatbed by 1.
     * Requires that the Scania is not moving, and that the current angle is between 0 - 70 degrees.
     */

    public void lowerFlatbed() {
        if (!(isMoving())) {
            if (flatbedAngle > 0) {
                flatbedAngle -= 1.0;
            } else {
                flatbedAngle = 0.0;
                System.out.println("Flatbed is at lowest position!");
            }
        } else {
            System.out.println("Stop vehicle before lowering flatbed!");
        }
    }


    /**
     *
     * @return speedFactor used for accelerating and braking.
     */

    @Override
    public double speedFactor() {
        return (getEnginePower() / getTotalWeight()) * 50;
    }
}
