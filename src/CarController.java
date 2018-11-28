import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
    //ArrayList<Car> cars = new ArrayList<>();
    private Map<Vehicle,Image> imageVehicleMap = new HashMap<>();
    private String imagesPath = "src\\pics\\";
    private String volvoImage = "Volvo240.jpg";
    private String saabImage = "Saab95.jpg";
    private String scaniaImage = "Scania.jpg";



    //methods:

    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();

        cc.cars.add(new Volvo240());

        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc);

        // Start the timer
        cc.timer.start();
    }

    private void createVehicle(Vehicle vehicle, String filename){
        try {
            BufferedImage image = ImageIO.read(new File(imagesPath + filename));
            imageVehicleMap.put(vehicle,image);

        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }



    /* Each step the TimerListener moves all the cars in the list and tells the
     * view to update its images. Change this method to your needs.
     * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            for (Map.Entry<Vehicle, Image> tuple : imageVehicleMap.entrySet()) {
                Vehicle vehicle = tuple.getKey();
                vehicle.move();
                int x = (int) Math.round(vehicle.getX());
                int y = (int) Math.round(vehicle.getY());

                frame.drawPanel.updateImagePosition(x, y);
                frame.drawPanel.setCurrentImage(tuple.getValue());
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
