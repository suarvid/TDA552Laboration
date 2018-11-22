import java.awt.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class CarTransporter implements Loadable, Movable {

    private static final int loadCapacity = 10;
    private static final int maxCarWeight = 2500;

    private final Car car;
    private boolean rampRaised;
    private final Deque<Car> loadedCars = new ArrayDeque<>();

    public CarTransporter(int nrDoors, Color color, double enginePower) {
        car = new Car(nrDoors, color, enginePower, "Transporter of Cars", 10000);
        rampRaised = true;
    }

    public void startEngine() {
        car.startEngine();
    }

    public void stopEngine() {
        car.stopEngine();
    }

    public void printPosition() {
        car.printPosition();
    }

    //Kan bli lite knäppt med gas och brake då man anropar den via Car, då speedFactorn från car mest troligt används i increment- och decrementSpeed.
    public void brake(double amount) {
        car.brake(amount);
    }

    public void gas(double amount) {
        car.gas(amount);
    }

    public void raiseRamp() {
        rampRaised = true;
    }

    public void lowerRamp() {
        if (car.isMoving()) {
            System.out.println("Stop moving before lowering the Ramp!");
        } else {
            rampRaised = false;
        }
    }

    public void turnRight() {
        car.turnRight();
    }

    public void turnLeft() {
        car.turnLeft();
    }

    public void move() {
        if (rampRaised) {
            car.move();
            for (Car loadedcar : loadedCars) {
                loadedcar.setPosition(car.getX(), car.getY());
            }
        } else {
            System.out.println("Raise the ramp before driving!");
        }
    }

    private boolean closeEnoughToLoad(Car carToLoad) {
        return !((Math.abs(carToLoad.getX() - car.getX()) > 1) || Math.abs(carToLoad.getY() - car.getY()) > 1);
    }

    public void load(Car carToLoad) {
        if (rampRaised) {
            System.out.println("Lower ramp before loading cars!");
        } else if (!closeEnoughToLoad(carToLoad)) {
            System.out.println("Car is to far away to be loaded on transporter!");

        } else if (loadedCars.size() == loadCapacity) {
            System.out.println("Car Transporter is loaded to the max! Cannot load more Cars!");
        } else if (!carToLoad.equals(car) && carToLoad.getTotalWeight() <= maxCarWeight) {
            loadedCars.add(carToLoad);
            car.setTotalWeight(car.getTotalWeight() + carToLoad.getTotalWeight());
            carToLoad.setPosition(car.getX(), car.getY());
        }

    }

    public Deque<Car> getLoadedCars() {
        return loadedCars;
    }

    public void unload() {
        loadedCars.pop();

    }

    public void unloadAll() {
        while (!loadedCars.isEmpty()) {
            loadedCars.pop();
        }
    }

    private void isMoving() {
        car.isMoving();
    }


    public boolean isFull() {
        return loadedCars.size() == loadCapacity;
    }

    public double speedFactor() {
        return (car.getEnginePower()/car.getTotalWeight()) * 50;
    }

}
