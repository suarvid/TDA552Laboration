import java.awt.*;

/**
 * Saab95 extends abstract class Car.
 * Defining implementation of abstract method speedFactor in Car, depending on saab-specific turbo feature.
 *
 * @author matte B, Arre S, Alle V
 * @version 1.0
 * @since 2018-11-07
 */

public class Saab95 extends Car {
    //Static
    private static long saab95sCreated = 0;

    //Non-static
    private boolean turboOn;
    private long id;

    /**
     * Takes two in-parameters, and passes some class-specific values to super-constructor
     *
     * @param color       color of the Saab95.
     * @param enginePower maximum speed of the Saab95.
     */

    public Saab95(Color color, double enginePower) {
        super(4, color, enginePower, "Saab95", 1600);
        saab95sCreated++;
        id = saab95sCreated;
    }


    public void setTurboOn() {
        turboOn = true;
    }

    public void setTurboOff() {
        turboOn = false;
    }

    public boolean isTurboOn() {
        return turboOn;
    }

    /**
     * If turbo is on, speedFactor increases with a greater amount.
     *
     * @return speedFactor to calc new speed with.
     */

    public double speedFactor() {
        double turbo = 1;
        if (turboOn) turbo = 1.3;
        return getEnginePower() * 0.01 * turbo;
    }


}
