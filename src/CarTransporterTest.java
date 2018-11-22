import org.junit.Assert;
import org.junit.Test;

import java.awt.*;

/**
 * Test dedicated to the carTransporter methods.
 *
 * @author Matte B, Arre S, Alle V
 * @version 1.0
 * @since 2018-11-23
 */
public class CarTransporterTest {


    /**
     * test to create a cartransporter with valid parameters
     *
     */
    @Test
    public void testCreateCarTransporter() {
        CarTransporter ct = new CarTransporter(2, Color.RED, 400, "CarTransporter", 0, 0);
    }

    /**
     * Testing that the ramp is lowered and raised as expected
     */
    @Test
    public void testRamp() {
        CarTransporter ct = new CarTransporter(2, Color.RED, 400, "CarTransporter", 0, 0);
        Assert.assertTrue(ct.isRampRaised());
        ct.lowerRamp();
        Assert.assertFalse(ct.isRampRaised());

    }

    /**
     * Testing that getting the loadedcars list executes without error
     */
    @Test
    public void testGetLoadedCarList() {
        CarTransporter ct = new CarTransporter(2, Color.RED, 400, "CarTransporter", 0, 0);
        ct.getLoadedCars();
    }

    /**
     * Testing that the load method works as expected
     * (No one car gets loaded twice/ only load when not moving/)
     * (/Ramp needs to be lowered)
     */
    @Test
    public void testLoad(){
        CarTransporter ct = new CarTransporter(2, Color.RED, 400, "CarTransporter", 0, 0);
        Saab95 saab95 = new Saab95(Color.RED,180);

        //Ramp not down this should not load a car
        ct.load(saab95);
        Assert.assertEquals( 0,ct.getLoadedCars().size() );

        //Actual loading of car because criterias are met here
        ct.lowerRamp();
        ct.load(saab95);

        //cannot load same car twice
        ct.load(saab95);
        ct.raiseRamp();
        Assert.assertEquals( 1,ct.getLoadedCars().size() );

        Volvo240 volvo240 = new Volvo240(Color.RED,180);
        //Cannot load a car while carloader is moving
        ct.startEngine();
        ct.gas(1);
        ct.move();
        ct.load(volvo240);
        Assert.assertEquals( 1,ct.getLoadedCars().size() );

        //Cannot load when too far away
        ct.stopEngine();
        ct.lowerRamp();
        ct.load(volvo240);
        Assert.assertEquals( 1,ct.getLoadedCars().size() );

        //Move volvo up to carloader
        volvo240.startEngine();
        volvo240.gas(1);
        volvo240.move();
        volvo240.stopEngine();
        //Move porsche up to carloader
        PorscheSpyder porscheSpyder = new PorscheSpyder(Color.BLUE,360);
        porscheSpyder.startEngine();
        //porsche is faster so gas less to move to the correct location
        porscheSpyder.gas(0.5);
        porscheSpyder.move();
        porscheSpyder.stopEngine();
        //Loading is now ok because the cars are adjacent to the carTransporter
        ct.load(volvo240);
        ct.load(porscheSpyder);
        Assert.assertEquals( 3,ct.getLoadedCars().size() );

    }

    /**
     * Testing that the Unload and unloadAll methods works as expected
     * (only Unload when not moving/Ramp needs to be lowered)
     */
    @Test
    public void testUnload(){
        CarTransporter ct = new CarTransporter(2, Color.RED, 400, "CarTransporter", 0, 0);
        Saab95 saab95 = new Saab95(Color.RED,180);
        Volvo240 volvo240 = new Volvo240(Color.GREEN, 180);
        PorscheSpyder porsche = new PorscheSpyder(Color.MAGENTA,360);

        ct.lowerRamp();
        ct.load(saab95);
        ct.load(volvo240);
        ct.load(porsche);

        //cannot unload with ramp raised...
        ct.raiseRamp();
        ct.unload();
        Assert.assertEquals(3,ct.getLoadedCars().size());

        //unload a car, this is OK!
        ct.lowerRamp();
        ct.unload();
        Assert.assertEquals(2,ct.getLoadedCars().size());

        //Start moving the cartransporter
        ct.raiseRamp();
        ct.startEngine();
        ct.gas(1);
        ct.move();
        //cannot unload while moving...
        ct.unload();
        Assert.assertEquals(2,ct.getLoadedCars().size());

        //Finally stop and unload all cars
        ct.stopEngine();
        ct.lowerRamp();
        ct.unloadAll();
        Assert.assertEquals(0,ct.getLoadedCars().size());

    }

    /**
     * Tests whether the isFull method behaves as expected
     */
    @Test
    public void testIsFull(){
        CarTransporter ct = new CarTransporter(2, Color.RED, 400, "CarTransporter", 0, 0);
        ct.lowerRamp();
        for(int i = 1; i <= 14; i++ ){
            ct.load(new Volvo240(Color.RED,180));
        }

        Assert.assertFalse(ct.isFull());
        ct.load(new Volvo240(Color.RED,180));
        Assert.assertTrue(ct.isFull());
        ct.load(new Volvo240(Color.RED,180));
        Assert.assertTrue(ct.isFull());
    }

}
