import java.awt.*;
import java.util.Queue;

public class Ferry extends Vehicle {

    private final CarLoader carLoader = new CarLoader(30, 20000, 90000);

    private boolean engineOn = false;
    private double enginePower = 9001;

    public Ferry(String modelname, Color color, int totalWeight, double x, double y) {
        super(modelname, color, totalWeight, x, y);
    }

    public Queue<Car> getLoadedCars() {
        return carLoader.getLoadedCars();

    }

    public void move() {
        move();
        for (Car car : carLoader.getLoadedCars()) {
            car.setPosition(getX(), getY());
        }
    }

    public void load(Car carToLoad) {
        carLoader.load(carToLoad, this);
    }

    @Override
    public void decrementSpeed(double amount) {
        setCurrentSpeed(getCurrentSpeed() - amount * (enginePower/10000));
    }
    @Override
    public void incrementSpeed(double amount) {
        setCurrentSpeed(getCurrentSpeed() + amount * (enginePower/10000));
    }

    public void unload() {
        carLoader.unload(carLoader.getLoadedCars().getFirst());
    }

    public void unloadAll() {
        carLoader.unloadAll();
    }

    public boolean isFull() {
        return carLoader.isFull();
    }


    public void startEngine() {
        engineOn = true;
    }

    public void stopEngine() {
        engineOn = false;
    }

    public void gas(double amount) {
        if (amount > 1.0) {
            amount = 1.0;
        }
        if (0 < amount) {
            incrementSpeed(amount);
        }
    }
}
