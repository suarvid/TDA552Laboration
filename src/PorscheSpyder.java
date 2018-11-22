import java.awt.*;

/**
 * PorscheSpyder, subclass to Car
 *
 * @author ArreParre, MatteFnatte, AlleTjalle
 * @version 1.0
 * @since 2018-11-14
 */

public class PorscheSpyder extends Car{
    //Static
    private static long spydersCreated = 0;
    private final static int numOfDoors = 2;

    //Non-static
    private long id;

    public PorscheSpyder(Color color, int enginePower) {
        super(numOfDoors, color, enginePower, "PorscheSpyder",1400,0,0);
        spydersCreated++;
        id = spydersCreated;
    }

    /**
     *
     *
     * @return speedFactor which is used to increase speed, factor depends on number of spyders that have been created.
     */

    public double speedFactor(){
        return this.getEnginePower() * 0.01 * spydersCreated;
    }
}
