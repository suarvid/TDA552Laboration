import static org.junit.Assert.*;

import org.junit.Test;

import java.awt.*;

public class Saab95Test {


    @Test
    public void createCar() {
        Car car = new Saab95(Color.ORANGE, 380);

    }

    @Test
    public void startCar() {
        Car car = new Saab95(Color.ORANGE, 380);
        car.startEngine();
        assertEquals(0.1, car.getCurrentSpeed(), 0.000001);
    }

    @Test
    public void stopCar() {
        Car car = new Saab95(Color.ORANGE, 380);
        car.startEngine();
        car.stopEngine();
        assertEquals(0, car.getCurrentSpeed(), 0.000001);
    }

    @Test
    public void turboOn() {
        Saab95 car = new Saab95(Color.ORANGE, 380);
        car.setTurboOn();
        assertEquals(true, car.isTurboOn());
    }

    @Test
    public void turboOff() {
        Saab95 car = new Saab95(Color.ORANGE, 380);
        car.setTurboOff();
        assertEquals(false, car.isTurboOn());
    }

/*
    @Test
    public void incSpeedWTurbo() {
        Saab95 car = new Saab95(Color.ORANGE, 380);
        car.setTurboOn();
        car.startEngine();
        car.incrementSpeed(10.0);
        assertEquals(49, car.getCurrentSpeed(), 1);
    }
    */

    @Test
    public void turnLeft() {
        Saab95 car = new Saab95(Color.BLACK, 100);
        car.turnLeft();
        assertEquals(Car.Directions.UP, car.getDirection());
        car.startEngine();
        car.turnLeft();
        assertEquals(Car.Directions.LEFT, car.getDirection());
        car.stopEngine();
        car.turnLeft();
        assertEquals(Car.Directions.LEFT, car.getDirection());
        car.startEngine();
        car.turnLeft();
        assertEquals(Car.Directions.DOWN, car.getDirection());
        car.turnLeft();
        assertEquals(Car.Directions.RIGHT, car.getDirection());
        car.turnLeft();
        assertEquals(Car.Directions.UP, car.getDirection());
    }

    @Test
    public void turnRight() {
        Saab95 car = new Saab95(Color.MAGENTA, 150);
        car.turnRight();
        assertEquals(Car.Directions.UP, car.getDirection());
        car.startEngine();
        car.turnRight();
        assertEquals(Car.Directions.RIGHT, car.getDirection());
        car.stopEngine();
        car.turnRight();
        assertEquals(Car.Directions.RIGHT, car.getDirection());
        car.startEngine();
        car.turnRight();
        assertEquals(Car.Directions.DOWN, car.getDirection());
        car.turnRight();
        assertEquals(Car.Directions.LEFT, car.getDirection());
        car.startEngine();
        car.turnRight();
        assertEquals(Car.Directions.UP, car.getDirection());

    }

    @Test
    public void move() {
        Saab95 car = new Saab95(Color.MAGENTA, 150);
        car.startEngine();
        car.move();
        assertEquals(true, car.getY() < 0);
    }
}
