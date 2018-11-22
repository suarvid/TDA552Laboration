import java.awt.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;

public class DriveCar {
    public static void main(String[] args) {
        Volvo240 redVolvo = new Volvo240(Color.RED, 100);
        Saab95 greenVolvo = new Saab95(Color.GREEN, 100);

        Saab95 redSaab = new Saab95(Color.RED, 100);
        Saab95 greenSaab = new Saab95(Color.GREEN, 100);


        ArrayList<Vehicle> vehicles = new ArrayList<>();
        ArrayList<Car> cars = new ArrayList<>();


        vehicles.add(redVolvo);
        vehicles.add(greenVolvo);
        vehicles.add(redSaab);
        vehicles.add(greenSaab);

        cars.add(redVolvo);
        cars.add(greenVolvo);
        cars.add(redSaab);
        cars.add(greenSaab);



    }
}
