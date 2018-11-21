import java.awt.*;
//Ska Scania vara med i Loadable? Döpa om Loadable till VehicleLoader eller liknande?
public class Scania implements Movable {
    private static int loadCapacity = 10000;
    private Car car;
    private double flatbedAngle;
    private double currentLoad;

    public Scania(int nrDoors, Color color, double enginePower) {
        car = new Car(nrDoors, color, enginePower, "Scania");
        flatbedAngle = 0;
        currentLoad = 0;
    }

    public void printCurrentSpeed() {
        System.out.println(car.getCurrentSpeed());
    }

    public void startEngine() {
        car.startEngine();
    }

    public void stopEngine() {
        car.stopEngine();
    }

    //Kan bli lite knäppt med gas och brake då man anropar den via Car, då speedFactorn från car mest troligt används i increment- och decrementSpeed.
    public void brake(double amount) {
        car.brake(amount);
    }

    public void gas(double amount) {
        car.gas(amount);
    }

    public void printPosition() {
        car.printPosition();
    }

    public void turnLeft() {
        car.turnLeft();
    }

    public void turnRight() {
        car.turnRight();
    }

    public void load(int weight) {
        if (currentLoad + weight <= loadCapacity) {
            currentLoad += weight;
        } else {
            System.out.println("Can't load the truck with that much weight!");
        }
    }

    public void unload(int weight) {
        if (currentLoad - weight >= 0) {
            currentLoad -= weight;
        } else {
            System.out.println("Truck is not loaded with that much weight! Can't unload more than: " + currentLoad + " kg");
        }
    }

    public boolean isFull() {
        if (currentLoad == loadCapacity) {
            return true;
        }
        return false;
    }

    /*private boolean isMoving() {
        if (car.getCurrentSpeed() > 0) {
            return true;
        }
        return false;
    }*/


    public void move() {
        if (flatbedAngle == 0) {
            car.move();
        } else {
            System.out.println("Lower flatbed before driving!");
        }
    }

    public void raiseFlatbed() {
        if (car.isMoving()) {
            System.out.println("Flatbed can only be raised when truck is not moving!");
        } else {
            if (flatbedAngle < 70) {
                flatbedAngle += 1.0;
            } else {
                flatbedAngle = 70.0;
                System.out.println("Flatbed is raised to the max!");
            }
        }
    }

    public void lowerFlatbed() {
        if (flatbedAngle > 0) {
            flatbedAngle -= 1.0;
        } else {
            flatbedAngle = 0.0;
            System.out.println("Flatbed is at lowest position!");
        }
    }

    public double speedFactor() {

        return 0.0; //Placeholder
    }
}
