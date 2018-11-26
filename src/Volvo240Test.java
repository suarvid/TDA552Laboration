import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Volvo240Test {
    @Test
    public void createCar() {
        Volvo240 car = new Volvo240( Color.ORANGE, 380);

    }

    @Test
    public void startCar() {
        Volvo240 car = new Volvo240( Color.ORANGE, 380);
        car.startEngine();
        assertEquals(0, car.getCurrentSpeed(), 0);
    }

    @Test
    public void stopCar() {
        Volvo240 car = new Volvo240( Color.ORANGE, 380);
        car.startEngine();
        car.stopEngine();
        assertEquals(0, car.getCurrentSpeed(), 0.000001);
    }

 /*   @Test
    public void incSpeed() {
        Volvo240 car = new Volvo240( Color.ORANGE, 380);
        car.startEngine();
        car.incrementSpeed(10.0);
        assertEquals(47, car.getCurrentSpeed(), 1);
    }
*/
    @Test
    public void turnLeft() {
        Volvo240 car = new Volvo240( Color.ORANGE, 380);
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
        Volvo240 car = new Volvo240( Color.ORANGE, 380);
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
        Volvo240 car = new Volvo240( Color.ORANGE, 380);
        car.startEngine();
        car.move();
        assertFalse(car.getY() < 0);
        car.gas(1);
        car.move();
        assertTrue(car.getY() < 0);
    }

    @Test
    public void gas() {
        Volvo240 car = new Volvo240(Color.RED, 10);
        car.startEngine();
        car.move();
        car.gas(10);
        car.printPosition();
        car.move();
        car.printPosition();
        car.gas(10);
        car.gas(10);
        car.gas(10);
        car.printPosition();
        car.move();
        car.printPosition();
    }

}
