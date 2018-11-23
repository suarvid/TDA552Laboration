import java.util.LinkedList;

/**
 * Class defining the general attributes and methods for an object used to store Cars in.
 * Delegated to Ferry and CarTransporter.
 *
 * @author Lantmannen, Matty, Alleboi
 * @version 1.0
 * @since 2018-11-22
 */

public class CarLoader {

    private int currentLoadWeight;
    private final int maxNrCars;
    private final int maxCarWeight;
    private final int maxLoadWeight;
    private final LinkedList<Car> loadedCars = new LinkedList<>();

    /**
     *
     * @param maxNrCars The maximum number of Cars that can be stored in the object.
     * @param maxCarWeight The maximum allowed weight of an individual Car stored in the object.
     * @param maxLoadWeight The maximum total weight of all Cars stored in the object combined.
     */

    CarLoader(int maxNrCars, int maxCarWeight, int maxLoadWeight) {
        this.maxNrCars = maxNrCars;
        this.maxCarWeight = maxCarWeight;
        this.maxLoadWeight = maxLoadWeight;
    }

    /**
     *
     * @return A list of all Cars currently loaded in the form of a LinkedList.
     */
    public LinkedList<Car> getLoadedCars() {
        return loadedCars;
    }

    /**
     *
     * @param carToLoad Target Car to load.
     * @param currentLoader Vehicle delegating a CarLoader
     */

    public void load(Car carToLoad, Vehicle currentLoader) {
        if (isFull()) {
            System.out.println("Cannot load! Car is either too heavy or at max Car capacity!");
        } else if (!closeEnoughToLoad(carToLoad, currentLoader.getX(), currentLoader.getY())) {
            System.out.println("Car is too far away to load!");
        } else if (!carToLoad.isOnTransport()) {
            loadedCars.add(carToLoad);
            carToLoad.setOnTransport();
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
            carToUnload.takeOffTransport();
            currentLoadWeight -= carToUnload.getTotalWeight();
            currentLoader.setTotalWeight(currentLoader.getTotalWeight() - carToUnload.getTotalWeight());
        } catch (NullPointerException eNullPoint) {
            System.out.println("Loader is already empty, cannot unload!");
        }
    }

    public void unloadAll(Loadable currentLoader) {
        for (int i = 0; i < loadedCars.size(); i++) {
            //Sets bool in each car to false, allowing them to move once again

            currentLoader.unload();
        }
    }

    public boolean isFull() {
        return loadedCars.size() == maxNrCars || currentLoadWeight == maxLoadWeight;
    }
}
