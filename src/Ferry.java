import java.awt.*;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class Ferry implements Movable, Loadable {

    private final Queue<Car> loadedCars = new LinkedBlockingQueue<>();
    private final Car methCar = new Car(0, Color.BLACK, 100000, "Ferry", 30000);

    private int currentLoadWeight = 0;
    private final int maxNrCars = 30;
    private final int maxCarWeight = 20000;
    private final int maxLoadWeight = 90000;


    public void move() {
        methCar.move();
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
        }else
    }

    public void unload() {

    }

    public void unloadAll() {

    }

    public boolean isFull() {

        return false; //Placeholder
    }
}
