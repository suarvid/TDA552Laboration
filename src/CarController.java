import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * This class represents the Controller part in the MVC pattern.
 * It's responsibilities is to listen to the View and responds in a appropriate manner by
 * modifying the model state and the updating the view.
 *
 *  @author Matte B, Arre S, Alle V, Alex G
 *  @version 1.0
 *  @since 2018-11-29
 */

public class CarController {
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with an listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    private CarView frame;

    private Map<Car, BufferedImage> imageCarMap = new HashMap<>();
    private String imagesPath = "src//pics//";
    private String volvoImage = "Volvo240.jpg";
    private String saabImage = "Saab95.jpg";
    private String scaniaImage = "Scania.jpg";


    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();

        //maps cars with respective image
        cc.createVehicle(new Volvo240(0,0),cc.volvoImage);
        cc.createVehicle(new Saab95(0,100),cc.saabImage);
        cc.createVehicle(new Scania(0,200),cc.scaniaImage);

        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc);
        cc.frame.drawPanel.setHashMap(cc.imageCarMap);

        // Start the timer
        cc.timer.start();
    }

    /**
     * Creates a image from filename if possible and maps car with image.
     * @param car the car to map
     * @param filename the filname of the image file to map
     */
    private void createVehicle(Car car, String filename){
        try {
            BufferedImage image = ImageIO.read(new File(imagesPath + filename));
            imageCarMap.put(car,image);
            System.out.println(image);

        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }



    /**
     * Moves all the cars in the list and tells the
     * view to update its images.
     * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            for (Map.Entry<Car, BufferedImage> tuple : imageCarMap.entrySet()) {
                Car car = tuple.getKey();
                car.move();
                frame.drawPanel.repaint();
                // repaint() calls the paintComponent method of the panel
                if (isOutOfBounds(car)) {
                    turnAround(car);
                }
            }
        }
    }

    /**
     * Calculate the x-coordinate for the image's most right corner.
     * @param car the car that maps with the image.
     * @return the x-coordinate for image's most right corner.
     */
    private double getMaxX(Car car) {
        return car.getX() + imageCarMap.get(car).getWidth();
    }

    /**
     * Returns the x-coordinate for car's image's most left corner.
     * @param car the car that maps with the image.
     * @return the x-coordinate for car's image's most left corner.
     */
    private double getMinX(Car car) {
        return car.getX();
    }

    /**
     * Controls whether a car is out of bounds (outside the view of the frame/panel)
     * @param car the car to check.
     * @return returns true if the car is either outside the bounds to the left or to the right.
     */
    private boolean isOutOfBounds(Car car) {
        if (getMinX(car) < 0) {
            return true;
        } else if (getMaxX(car) > frame.getX()) {
            return true;
        }
        return false;
    }

    /**
     * Turns a car 180 degrees.
     * @param car the car to turn around.
     */
    private void turnAround(Car car) {
        car.stopEngine();
        car.turnRight();
        car.turnRight();
        car.startEngine();

    }

    /**
     * Turns on the turbo for each of the compatible instantiated cars.
     */
    // Calls the gas method for each car once
    void turboOn() {
        for (Car car : imageCarMap.keySet()) {
            try {
                Saab95 saab95 = (Saab95) car;
                saab95.setTurboOn();
            } catch (ClassCastException cce) {

            }
        }
    }

    /**
     * Gases each of the instantiated cars.
     * @param amount how much to gas the cars.
     */
    void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (Vehicle vehicle : imageCarMap.keySet()) {
            Car car = (Car) vehicle;
            car.gas(gas);
        }
    }

    /**
     * Breaks all of the instantiated cars
     *
     * @param amount how much to break the cars.
     */
    void brake(int amount) {
        double brake = ((double) amount / 100);
        for (Car car : imageCarMap.keySet()) {
            car.brake(brake);
        }
    }

    /**
     * Stops each of the instantiated cars.
     */
    void stopEngine() {
        for (Car car : imageCarMap.keySet()) {
            car.stopEngine();
        }
    }

    /**
     * Starts each of the instantiated cars.
     */
    void startEngine() {
        for (Car car : imageCarMap.keySet()) {
            car.startEngine();
        }
    }

    /**
     * Lowers the flatbead of each Scania of the instantiated cars.
     */
    void raiseFlatBed() {
        for (Car car : imageCarMap.keySet()) {
            try {
                Scania scania = (Scania) car;
                scania.raiseFlatbed();
            } catch (ClassCastException cce) {

            }
        }
    }

    /**
     * Lowers the flatbead of each Scania of the instantiated cars.
     */
    void lowerFlatbed() {
        for (Car car : imageCarMap.keySet()) {
            try {
                Scania scania = (Scania) car;
                scania.lowerFlatbed();
            } catch (ClassCastException cce) {

            }
        }
    }

}
