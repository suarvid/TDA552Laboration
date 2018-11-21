import java.awt.*;
import java.util.ArrayList;

public class CarTransporter implements Loadable, Movable {

    private static final int loadCapacity = 10;

    private Car car;
    private boolean rampRaised;
    private final ArrayList<Car> loadedCars = new ArrayList<>();

    public CarTransporter(int nrDoors, Color color, double enginePower) {
        car = new Car(nrDoors, color, enginePower, "Transporter of Cars");
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
        } else if (!carToLoad.equals(car)) {
            loadedCars.add(carToLoad);
            carToLoad.setPosition(car.getX(), car.getY());
        }

    }

    public void unload(int cars) {

    }

    /*private boolean isMoving() {
        return car.getCurrentSpeed() > 0;
    }*/


    public boolean isFull() {
        //TODO Implement
        return true;
    }

    public double speedFactor() {
        //TODO Implement
        return 0.0;
    }

}
