import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/*
 * This class represents the Controller part in the MVC pattern.
 * It's responsibilities is to listen to the View and responds in a appropriate manner by
 * modifying the model state and the updating the view.
 */

public class CarController {
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with an listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // A list of cars, modify if needed
    ArrayList<Car> cars = new ArrayList<>();

    //methods:

    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();

        cc.cars.add(new Volvo240());
        cc.cars.add(new Saab95());
        cc.cars.add(new Scania());
        HashMap<Car, Image> carImageHashMap = new HashMap<>();
        try {
            carImageHashMap.put(new Volvo240(), ImageIO.read(new File("src//pics//Volvo240.jpg")));
            carImageHashMap.put(new Saab95(), ImageIO.read(new File("src//pics//Saab95.jpg")));
            carImageHashMap.put(new Scania(), ImageIO.read(new File("src//pics//Scania.jpg")));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc);

        // Start the timer
        cc.timer.start();
    }

    /*public void iterateUsingLambda(Map<Car, Image> map) {
        Car current;
        map.forEach((k, v) -> System.out.println("K: " + k + ", V: " + v));
    }*/


    /* Each step the TimerListener moves all the cars in the list and tells the
     * view to update its images. Change this method to your needs.
     * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (Car car : cars) {
                car.move();
                int x = (int) Math.round(car.getX());
                int y = (int) Math.round(car.getY());
                frame.drawPanel.moveit(x, y);
                // repaint() calls the paintComponent method of the panel
                frame.drawPanel.repaint();
                if (isOutOfBounds(car)) {
                    turnAround(car);
                }
            }
        }
    }

    private boolean isOutOfBounds(Car car) {
        if (car.getX() < 0) {
            return true;
        } else if (car.getX() > frame.getX()) {
            return true;
        }
        return false;
    }

    private void turnAround(Car car) {
        if (car.getX() > 0) {
            car.turnRight();
            car.turnRight();
            //Checks out of bounds to the right
        } else if (car.getX() < 0) {
            car.turnRight();
            car.turnRight();
        }
        //Checks out of bounds to the left
    }

    // Calls the gas method for each car once
    void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (Car car : cars) {
            car.gas(gas);
        }
    }

    void brake(int amount) {
        double brake = ((double) amount / 100);
        for (Car car : cars) {
            car.brake(brake);
        }
    }

    void stopEngine() {
        for (Car car : cars) {
            car.stopEngine();
        }
    }

    void startEngine() {
        for (Car car : cars) {
            car.startEngine();
        }
    }
}
