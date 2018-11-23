import org.junit.Test;

import java.awt.*;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class CarLoaderTest {

    /**
     * Test to create a new carLoader with valid parameters
     */
    @Test
    public void createCarLoader() {
        CarLoader carLoader = new CarLoader(4, 2000, 6000);
    }

    /**
     * Test that getLoadedCars() returns a list with the loaded car.
     */
    @Test
    public void getLoadedCars(){
        CarLoader carLoader = new CarLoader(4, 2000, 6000);
        CarTransporter transporter = new CarTransporter(4,Color.BLUE,200,"DHL",0,0);
        Saab95 saab95 = new Saab95(Color.RED,180);
        carLoader.load(saab95,transporter);
        assertEquals(saab95, carLoader.getLoadedCars().getFirst());
    }

    /**
     * Test to load two cars and checks that car loader holds more than one car.
     */
    @Test
    public void load(){
        CarLoader carLoader = new CarLoader(4, 2000, 6000);
        CarTransporter transporter = new CarTransporter(4,Color.BLUE,200,"DHL",0,0);
        carLoader.load(new Saab95(Color.RED,180),transporter);
        carLoader.load(new Volvo240(Color.CYAN,200),transporter);
        assertTrue(carLoader.getLoadedCars().size() > 1);
    }

    /**
     * Testing unload method in carLoader (from a car transporter),
     * but does not follow rules for unloading in carTransporter.
     */
    @Test
    public void unloadWithCarTransporter() {
        CarLoader carLoader = new CarLoader(4, 2000, 6000);
        CarTransporter transporter = new CarTransporter(4, Color.BLUE, 200, "DHL", 0, 0);
        Saab95 saab95 = new Saab95(Color.RED, 180);
        Volvo240 volvo240 = new Volvo240(Color.CYAN, 200);
        carLoader.load(saab95, transporter);
        carLoader.load(volvo240, transporter);
        carLoader.unload(saab95, transporter);
        assertEquals(volvo240, carLoader.getLoadedCars().getFirst());
    }

    /**
     * test unload method with ferry
     * does not follow ferrys unloading rules, unload by parameter.
     */
    @Test
    public void unloadWithFerry(){
        CarLoader carLoader = new CarLoader(4, 2000, 6000);
        Ferry ferry = new Ferry("Ferry", 2000,Color.BLUE, 0, 0);
        Saab95 saab95 = new Saab95(Color.RED, 180);
        Volvo240 volvo1 = new Volvo240(Color.CYAN, 200);
        Volvo240 volvo2 = new Volvo240(Color.BLUE, 300);
        carLoader.load(saab95, ferry);
        carLoader.load(volvo1, ferry);
        carLoader.load(volvo2, ferry);
        carLoader.unload(volvo1, ferry);
        assertEquals(saab95, carLoader.getLoadedCars().getFirst());
        assertEquals(volvo2, carLoader.getLoadedCars().getLast());
    }

    /**
     * Test to load a car that is to far away.
     * no car should be loaded.
     */
    @Test
    public void closeEnoughToLoad(){
        CarLoader carLoader = new CarLoader(4, 2000, 6000);
        Ferry ferry = new Ferry("Ferry", 2000,Color.BLUE, 10, 10);
        Saab95 saab95 = new Saab95(Color.RED, 180);
        carLoader.load(saab95, ferry);
        assertTrue(ferry.getLoadedCars().size() == 0);
    }

    /**
     * Test to unload all cars on a ferry.
     */
    @Test
    public void unloadAll(){
        CarLoader carLoader = new CarLoader(4, 2000, 6000);
        Ferry ferry = new Ferry("Ferry", 2000,Color.BLUE, 0, 0);
        Saab95 saab95 = new Saab95(Color.RED, 180);
        Volvo240 volvo1 = new Volvo240(Color.CYAN, 200);
        Volvo240 volvo2 = new Volvo240(Color.BLUE, 300);
        carLoader.load(saab95, ferry);
        carLoader.load(volvo1, ferry);
        carLoader.load(volvo2, ferry);
        carLoader.unloadAll(ferry);
        assertTrue(carLoader.getLoadedCars().size() == 0);
    }
    /**
     * Test that only maximum number of cars can be loaded.
     */
    @Test
    public void loadToMax(){
        CarLoader carLoader = new CarLoader(4, 2000, 6000);
        Ferry ferry = new Ferry("Ferry",2000, Color.BLUE, 0, 0);
        for(int i = 0; i < 5; i++){
            carLoader.load(new Volvo240(Color.CYAN, 200),ferry);
        }
        assertTrue(carLoader.getLoadedCars().size() == 4);
    }
    @Test
    public void isFull(){
        CarLoader carLoader = new CarLoader(4, 2000, 6000);
        Ferry ferry = new Ferry("Ferry",2000, Color.BLUE, 0, 0);
        for(int i = 0; i < 5; i++){
            carLoader.load(new Volvo240(Color.CYAN, 200),ferry);
        }
        assertTrue(carLoader.isFull());
    }



}
