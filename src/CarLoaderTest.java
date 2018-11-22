import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class CarLoaderTest {

    /**
     * Test to create a new carLoader with valid parameters
     */
    @Test
    public void createCarLoader() {
        CarLoader carLoader = new CarLoader(4, 2000, 6000);
    }
    @Test
    public void load(){
        CarLoader carLoader = new CarLoader(4, 2000, 6000);
        CarTransporter transporter = new CarTransporter(4,Color.BLUE,200,"DHL",5000,0,0);
        carLoader.load(new Saab95(Color.RED,180),transporter);
        carLoader.load(new Volvo240(Color.CYAN,200),transporter);
        assertTrue(carLoader.getLoadedCars().size() > 1);
    }

    /**
     * Testing unload method in carLoader (from a car transporter),
     * but does not follow rules for unloading in carTransporter.
     */
    @Test
    public void unload(){
        CarLoader carLoader = new CarLoader(4, 2000, 6000);
        CarTransporter transporter = new CarTransporter(4,Color.BLUE,200,"DHL",5000,0,0);
        Saab95 saab95 = new Saab95(Color.RED,180);
        carLoader.load(saab95,transporter);
        carLoader.load(new Volvo240(Color.CYAN,200),transporter);
        carLoader.unload(saab95);

    }

}
