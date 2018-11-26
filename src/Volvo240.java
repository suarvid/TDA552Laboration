import java.awt.*;

/**
 * Volvo240 extends abstract class Car.
 * Defining implementation of abstract method speedFactor in Car, depending on volvo240-specific trim factor.
 *
 * @author matte B, Arre S, Alle V
 * @version 1.0
 * @since 2018-11-07
 */

public class Volvo240 extends Car {
    //Static
    private static long n240sCreated = 0;
    private final static double trimFactor = 1.25;

    //Non-static
    private long id;

    /**
     * Takes two in-parameters, and passes some class-specific values to super-constructor
     *
     * @param color       color of the Volvo240.
     * @param enginePower maximum speed of the Volvo240.
     */

    public Volvo240(Color color, double enginePower) {
        super(4, color, enginePower, "Volvo240", 1200,0,0);
        n240sCreated++;
        id = n240sCreated;
    }

    public Volvo240(){
        super(4, Color.RED, 180, "Volvo240", 1200,0,0);
        n240sCreated++;
        id = n240sCreated;
    }

    /**
     * @return speedFactor to calc new speed with, depends on the cars trimFactor
     */

    public double speedFactor() {
        return this.getEnginePower() * 0.01 * trimFactor;
    }

}
