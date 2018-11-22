import java.awt.*;
import java.util.Queue;

public class CarTransporter extends Car implements Loadable<Car> {

    private final CarLoader carLoader = new CarLoader(15, 3000, 50000);

    private boolean rampRaised;

    public CarTransporter(int nrDoors, Color color, double enginePower, String modelName, double x, double y) {
        super(nrDoors, color, enginePower, modelName, 7000, x, y);
        rampRaised = true;
    }

    public Queue<Car> getLoadedCars() {
        return carLoader.getLoadedCars();
    }


    public void raiseRamp() {
        rampRaised = true;
    }

    public void lowerRamp(){
        if (isMoving()) {
            System.out.println("Stop moving before lowering the Ramp!");
        } else {
            rampRaised = false;
        }
    }

    public void load(Car carToLoad) {
        if (!rampRaised && carToLoad != this) {
            try {
                carLoader.load(carToLoad, this);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    public void unloadAll() {
        carLoader.unloadAll();
    }


    public void unload() {
        if (!rampRaised) {
            Car carToUnload = carLoader.getLoadedCars().getLast();
            carLoader.unload(carToUnload, this);
            switch (getDirection()) {
                case UP:
                    carToUnload.setPosition(getX(), getY() + 0.5);
                    break;
                case DOWN:
                    carToUnload.setPosition(getX(), getY() - 0.5);
                    break;
                case RIGHT:
                    carToUnload.setPosition(getX() - 0.5, getY());
                    break;
                case LEFT:
                    carToUnload.setPosition(getX() + 0.5, getY());
                    break;
            }
        }else{
            System.out.println("Lower the ramp before unloading...");
        }
    }

    public void move(){
        if (rampRaised) {
            super.move();
            for (Car loadedCar : carLoader.getLoadedCars()) {
                loadedCar.setPosition(getX(), getY());
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

    public boolean isRampRaised() {
        return rampRaised;
    }
}
