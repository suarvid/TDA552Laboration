import java.awt.*;
import java.util.Queue;

public class CarTransporter extends Car {


    private final CarLoader carLoader = new CarLoader(15, 10000, 50000);


    private boolean rampRaised;

    public CarTransporter(int nrDoors, Color color, double enginePower, String modelName, int totalWeight, double x, double y) {
        super(nrDoors, color, enginePower, modelName, totalWeight, x, y);
        rampRaised = true;
    }


    public void raiseRamp() {
        rampRaised = true;
    }

    public Queue<Car> getLoadedCars() {
        return carLoader.getLoadedCars();
    }

    public void lowerRamp() {
        if (isMoving()) {
            System.out.println("Stop moving before lowering the Ramp!");
        } else {
            rampRaised = false;
        }
    }

    public void load(Car carToLoad) {
        carLoader.load(carToLoad, this);
    }

    public void unload() {
        carLoader.unload(carLoader.getLoadedCars().getLast());
    }

    public void move() {
        if (rampRaised) {
            super.move();
            for (Car loadedcar : carLoader.getLoadedCars()) {
                loadedcar.setPosition(getX(), getY());
            }
        } else {
            System.out.println("Raise the ramp before driving!");
        }
    }


    public boolean isFull() {
        return carLoader.isFull();
    }

    @Override
    public double speedFactor() {
        return (getEnginePower() / getTotalWeight()) * 50;
    }

}
