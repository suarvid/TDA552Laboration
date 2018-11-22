import java.awt.*;

//Ska Scania vara med i Loadable? Döpa om Loadable till VehicleLoader eller liknande?
public class Scania extends Car implements Loadable<Integer> {
    private static int loadCapacity = 10000;
    //    private Car car;
    private double flatbedAngle;
    private double currentLoad;

    public Scania(int nrDoors, Color color, double enginePower, double x, double y) {
        super(nrDoors, color, enginePower, "Scania", 8000, x, y);
        flatbedAngle = 0;
        currentLoad = 0;
    }

    public double getFlatbedAngle() {
        return flatbedAngle;
    }

    public void load(Integer loadWeight) {
        if (currentLoad + loadWeight <= loadCapacity) {
            currentLoad += loadWeight;
            setTotalWeight(getTotalWeight() + loadWeight);
        } else {
            System.out.println("Can't load the truck with that much weight!");
        }
    }

    public void unload(Integer weight) {
        if (currentLoad - weight >= 0) {
            currentLoad -= weight;
            setTotalWeight(getTotalWeight() - weight);
        } else {
            System.out.println("Truck is not loaded with that much weight! Can't unload more than: " + currentLoad + " kg");
        }
    }

    public void unload() {
        currentLoad = 0;
    }

    public boolean isFull() {
        if (currentLoad == loadCapacity) {
            return true;
        }
        return false;
    }


    @Override
    public void move() {
        if (flatbedAngle == 0) {
            super.move();
        } else {
            System.out.println("Lower flatbed before driving!");
        }
    }

    public void raiseFlatbed() {
        if (isMoving()) {
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
        if (!(isMoving())) {
            if (flatbedAngle > 0) {
                flatbedAngle -= 1.0;
            } else {
                flatbedAngle = 0.0;
                System.out.println("Flatbed is at lowest position!");
            }
        } else {
            System.out.println("Stop vehicle before lowering flatbed!");
        }
    }

    @Override
    public double speedFactor() {
        return (getEnginePower() / getTotalWeight()) * 50;
    }
}
