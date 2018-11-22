
import java.util.LinkedList;
import java.util.Queue;

public class CarLoader {

    private int currentLoadWeight;
    private final int maxNrCars;
    private final int maxCarWeight;
    private final int maxLoadWeight;
    private final LinkedList<Car> loadedCars = new LinkedList<>();

    CarLoader(int maxNrCars, int maxCarWeight, int maxLoadWeight) {
        this.maxNrCars = maxNrCars;
        this.maxCarWeight = maxCarWeight;
        this.maxLoadWeight = maxLoadWeight;
    }

    public LinkedList<Car> getLoadedCars() {
        return loadedCars;
    }


    public void load(Car carToLoad, Vehicle currentLoader) {
        if (isFull()) {
            System.out.println("Cannot load! Car is either too heavy or at max Car capacity!");
        } else if (!closeEnoughToLoad(carToLoad, currentLoader.getX(), currentLoader.getY())) {
            System.out.println("Car is too far away to load!");
        } else {
            loadedCars.add(carToLoad);
            currentLoadWeight += carToLoad.getTotalWeight();
            currentLoader.setTotalWeight(currentLoader.getTotalWeight() + carToLoad.getTotalWeight());
            carToLoad.setPosition(currentLoader.getX(), currentLoader.getY());
        }
    }

    private boolean closeEnoughToLoad(Car carToLoad, double loaderX, double loaderY) {
        return !((Math.abs(carToLoad.getX() - loaderX) > 1) || Math.abs(carToLoad.getY() - loaderY) > 1);
    }

    public void unload(Car carToUnload, Vehicle currentLoader) {
        try {
            loadedCars.remove(carToUnload);
            currentLoadWeight -= carToUnload.getTotalWeight();
            currentLoader.setTotalWeight(currentLoader.getTotalWeight() - carToUnload.getTotalWeight());
        } catch (NullPointerException eNullPoint) {
            System.out.println("Loader is already empty, cannot unload!");
        }
    }

    public void unloadAll() {
        while (!loadedCars.isEmpty()) {
            loadedCars.remove();
        }
    }

    public boolean isFull() {
        return loadedCars.size() == maxNrCars || currentLoadWeight == maxLoadWeight;
    }
}
