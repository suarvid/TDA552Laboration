import static org.junit.Assert.*;
import org.junit.Test;

import java.awt.*;

/**
 * Test dedicated to the Ferry methods.
 *
 * @author Matte B, Arre S, Alle V
 * @version 1.0
 * @since 2018-11-23
 */
public class FerryTest {


    /**
     * test to create a ferry with valid parameters
     */
    @Test
    public void createFerry() {
        Ferry ferry = new Ferry("Ferris", 5000, Color.RED, 0, 0);
    }


    /**
     * Testing that getting the loadedcars list executes without error
     */
    @Test
    public void testGetLoadedCarList() {
        Ferry ferry = new Ferry("Ferris", 5000, Color.RED, 0, 0);
        ferry.getLoadedCars();
    }

    /**
     * Testing that the load method does not load one car twice.
     */
    @Test
    public void testLoadSameTwice() {
        Ferry ferry = new Ferry("Ferris", 5000, Color.RED, 0, 0);
        Saab95 saab95 = new Saab95(Color.RED, 180);

        ferry.load(saab95);
        assertEquals(1, ferry.getLoadedCars().size());

        ferry.load(saab95);
        assertEquals(1, ferry.getLoadedCars().size());
    }

    /**
     * Test to load several cars
     */
    @Test
    public void testLoadSeveral(){
        Ferry ferry = new Ferry("Ferris", 5000, Color.RED, 10, 10);
        Saab95 saab95 = new Saab95(Color.RED, 180);
        Volvo240 volvo240 = new Volvo240(Color.GREEN, 180);
        PorscheSpyder porsche = new PorscheSpyder(Color.MAGENTA, 360);

        ferry.load(saab95);
        ferry.load(volvo240);
        ferry.load(porsche);
        assertEquals(3,ferry.getLoadedCars().size());
    }
    /**
     * Test that load method does not load a car when loader is moving.
     */
    @Test
    public void testLoadWhileMoving() {
        Ferry ferry = new Ferry("Ferris", 5000, Color.RED, 0, 0);
        Volvo240 volvo240 = new Volvo240(Color.RED, 180);
        ferry.startEngine();
        ferry.gas(1);
        ferry.move();
        ferry.load(volvo240);
        assertEquals(0, ferry.getLoadedCars().size());
    }

    /**
     * Test that load method does not load a car when loader is too far away.
     */
    @Test
    public void testLoadToFarAway() {
        Ferry ferry = new Ferry("Ferris", 5000, Color.RED, 10, 10);
        Volvo240 volvo240 = new Volvo240(Color.RED, 180);
        ferry.load(volvo240);
        assertEquals(0, ferry.getLoadedCars().size());
    }

    /**
     * Test that load method does not load a car when loader is moving.
     */
    @Test
    public void testLoadToHeavy() {
        Ferry ferry = new Ferry("Ferris", 5000, Color.RED, 10, 10);
        CarTransporter ct = new CarTransporter(2,Color.CYAN,900,"CarT",0,0);
        for(int i = 0; i < 15; i++) {
            ct.load(new Saab95(Color.BLACK, 300));
        }
        ferry.load(ct);
        assertTrue(ferry.getLoadedCars().size()== 0);
    }

    /**
     * Test that unload works as expected (FIFO)
     */
    @Test
    public void testUnload(){
        Ferry ferry = new Ferry("Ferris", 5000, Color.RED, 10, 10);
        Saab95 saab95 = new Saab95(Color.RED, 180);
        Volvo240 volvo240 = new Volvo240(Color.GREEN, 180);
        ferry.unload();
        assertEquals(volvo240,ferry.getLoadedCars().getFirst());
    }

    /**
     * Testing that the unloadAll method unload all cars
     */
    @Test
    public void testUnloadAll() {
        Ferry ferry = new Ferry("Ferris", 5000, Color.RED, 10, 10);
        Saab95 saab95 = new Saab95(Color.RED, 180);
        Volvo240 volvo240 = new Volvo240(Color.GREEN, 180);
        PorscheSpyder porsche = new PorscheSpyder(Color.MAGENTA, 360);

        ferry.load(saab95);
        ferry.load(volvo240);
        ferry.load(porsche);

        ferry.unloadAll();
        assertEquals(0, ferry.getLoadedCars().size());
    }

    /**
     * Tests whether the isFull method behaves as expected
     */
    @Test
    public void testIsFull() {
        CarTransporter ct = new CarTransporter(2, Color.RED, 400, "CarTransporter", 0, 0);
        ct.lowerRamp();
        for (int i = 1; i <= 14; i++) {
            ct.load(new Volvo240(Color.RED, 180));
        }

        assertFalse(ct.isFull());
        ct.load(new Volvo240(Color.RED, 180));
        assertTrue(ct.isFull());
        ct.load(new Volvo240(Color.RED, 180));
        assertTrue(ct.isFull());
    }

}
