import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class ScaniaTest {
    @Test
    public void createScania() {
        Scania scania = new Scania(2, Color.RED, 100, 0, 0);
    }

    @Test
    public void startEngine() {
        Scania scania = new Scania(2, Color.RED, 100, 0, 0);
        scania.startEngine();
        assertTrue(scania.isEngineOn());
    }

    @Test
    public void stopEngine() {
        Scania scania = new Scania(2, Color.RED, 100, 0, 0);
        scania.startEngine();
        scania.stopEngine();
        assertFalse(scania.isEngineOn());
    }

    @Test
    public void raiseFlatbed() {
        Scania scania = new Scania(2, Color.RED, 100, 0, 0);
        scania.raiseFlatbed();
        assertTrue(scania.getFlatbedAngle() > 0);
    }

    @Test
    public void lowerFlatbed() {
        Scania scania = new Scania(2, Color.RED, 100, 0, 0);
        scania.raiseFlatbed();
        scania.lowerFlatbed();
        assertEquals(0, scania.getFlatbedAngle(), 0);
    }

    @Test
    public void isFull() {
        Scania scania = new Scania(2, Color.RED, 100, 0, 0);
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
        Scania scania = new Scania(2, Color.RED, 100, 0, 0);
        assertEquals(0, scania.getFlatbedAngle(), 0);
        scania.raiseFlatbed();
        assertEquals(1, scania.getFlatbedAngle(), 0);
    }

    @Test
    public void load() {
        Scania scania = new Scania(2, Color.RED, 100, 0, 0);
        scania.load(1);
        assertFalse(scania.isFull());
        scania.load(9999);
        assertTrue(scania.isFull());
    }

    @Test
    public void unload() {
        Scania scania = new Scania(2, Color.RED, 100, 0, 0);
        assertEquals(0, scania.getCurrentLoad());
        scania.load(10);
        assertEquals(10, scania.getCurrentLoad());
        scania.unload();
        assertEquals(0, scania.getCurrentLoad());
    }

    @Test
    public void move() {
        Scania scania = new Scania(2, Color.RED, 100, 0, 0);
        scania.move();
        assertFalse(scania.isMoving());
        scania.startEngine();
        scania.gas(1);
        scania.move();
        assertTrue(scania.isMoving());
        scania.stopEngine();
        assertFalse(scania.isMoving());
    }

    @Test
    public void turnLeft() {
        Scania scania = new Scania(2, Color.RED, 100, 0, 0);
        assertEquals(Vehicle.Directions.UP, scania.getDirection());
        scania.turnLeft();
        assertEquals(Vehicle.Directions.UP, scania.getDirection());
        scania.startEngine();
        scania.turnLeft();
        assertEquals(Vehicle.Directions.LEFT, scania.getDirection());
        scania.turnLeft();
        assertEquals(Vehicle.Directions.DOWN, scania.getDirection());
        scania.turnLeft();
        assertEquals(Vehicle.Directions.RIGHT, scania.getDirection());
        scania.turnLeft();
        assertEquals(Vehicle.Directions.UP, scania.getDirection());
        scania.stopEngine();
        assertEquals(Vehicle.Directions.UP, scania.getDirection());

    }

    @Test
    public void turnRight() {
        Scania scania = new Scania(2, Color.RED, 100, 0, 0);
        assertEquals(Vehicle.Directions.UP, scania.getDirection());
        scania.turnRight();
        assertEquals(Vehicle.Directions.UP, scania.getDirection());
        scania.startEngine();
        scania.turnRight();
        assertEquals(Vehicle.Directions.RIGHT, scania.getDirection());
        scania.turnRight();
        assertEquals(Vehicle.Directions.DOWN, scania.getDirection());
        scania.turnRight();
        assertEquals(Vehicle.Directions.LEFT, scania.getDirection());
        scania.turnRight();
        assertEquals(Vehicle.Directions.UP, scania.getDirection());
        scania.stopEngine();
        scania.turnRight();
        assertEquals(Vehicle.Directions.UP, scania.getDirection());

    }

    @Test
    public void moveWithFlatbed() {
        Scania scania = new Scania(2, Color.RED, 100, 0, 0);
        scania.startEngine();
        scania.raiseFlatbed();
        scania.move();
        assertEquals(0, scania.getX(), 0);
        assertEquals(0, scania.getY(), 0);
        scania.lowerFlatbed();
        scania.gas(1);
        scania.move();
        assertFalse(scania.getY() == 0);
    }

    @Test
    public void lowerFlatbedMoving() {
        Scania scania = new Scania(2, Color.RED, 100, 0, 0);
        scania.startEngine();
        scania.gas(1);
        scania.move();
        assertTrue(scania.isMoving());
        scania.raiseFlatbed();
        assertEquals(0, scania.getFlatbedAngle(),0);
    }


}
