import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

import java.awt.*;

public class ScaniaTest {
    @Test
    public void createScania() {
        Scania scania = new Scania(2, Color.RED,100,0,0);
    }

    @Test
    public void startEngine() {
        Scania scania = new Scania(2, Color.RED,100,0,0);
        scania.startEngine();
        assertTrue(scania.isEngineOn());
    }

    @Test
    public void stopEngine() {
        Scania scania = new Scania(2, Color.RED,100,0,0);
        scania.startEngine();
        scania.stopEngine();
        assertFalse(scania.isEngineOn());
    }

    @Test
    public void raiseFlatbed() {
        Scania scania = new Scania(2, Color.RED,100,0,0);
        scania.raiseFlatbed();
        assertTrue(scania.getFlatbedAngle() > 0);
    }

    @Test
    public void lowerFlatbed() {
        Scania scania = new Scania(2, Color.RED,100,0,0);
        scania.raiseFlatbed();
        scania.lowerFlatbed();
        assertEquals(0,scania.getFlatbedAngle(),0);
    }

    @Test
    public void isFull() {
        Scania scania = new Scania(2, Color.RED,100,0,0);
        assertFalse(scania.isFull());
        scania.load(2);
        assertFalse(scania.isFull());
        scania.load(1000000);
        assertFalse(scania.isFull());
        scania.load(9998);
        assertTrue(scania.isFull());

    }

    @Test
    public void getFlatbedAngle() {
        Scania scania = new Scania(2, Color.RED,100,0,0);
        
    }

    @Test
    public void load() {
        Scania scania = new Scania(2, Color.RED,100,0,0);
        scania.load(1);
        assertFalse(scania.isFull());
        scania.load(9999);
        assertTrue(scania.isFull());
    }



}
