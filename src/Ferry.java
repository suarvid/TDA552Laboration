import java.awt.*;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class Ferry implements Movable, CarLoadable {

    private final Queue<Car> loadedCars = new LinkedBlockingQueue<>();
    private final Car methCar = new Car(0, Color.BLACK, 100000, "Ferry", 30000);

    private int currentLoadWeight = 0;
    private final int maxNrCars = 30;
    private final int maxCarWeight = 20000;
    private final int maxLoadWeight = 90000;

    public Ferry() {

    }


    public void move() {
        methCar.move();
        for (Car car : loadedCars) {
            car.setPosition(this.methCar.getX(), this.methCar.getY());
        }
    }

    public void turnLeft() {
        methCar.turnLeft();
    }

    public void turnRight() {
        methCar.turnRight();
    }

    public void load(Car car) {
        if (loadedCars.size() >= maxNrCars) {
            System.out.println("Maximum number of loaded cars reached!");
        } else if (currentLoadWeight + car.getTotalWeight() > maxLoadWeight) {
            System.out.println("Maximum weight reached, cannot load car!");
        } else if (car.getTotalWeight() > maxCarWeight) {
            System.out.println("This car is too heavy!");
        } else {
            loadedCars.add(car);
        }
    }

    public void unload() {

    }

    public void unloadAll() {

    }

    public boolean isFull() {

        return false; //Placeholder
    }

    public void printPosition() {
        System.out.println("Ferry is at: " + methCar.getX() + ", " + methCar.getY());
    }

    public void startEngine() {
        methCar.startEngine();
    }

    public void gas(double amount) {
        methCar.gas(amount);
    }
}
