import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;

// This panel represent the animated part of the view with the car images.

public class DrawPanel extends JPanel{
    private String imagesPath = "src//pics//";
    private String volvoImage = "Volvo240.jpg";
    private String saabImage = "Saab95.jpg";
    private String scaniaImage = "Scania.jpg";


    // Just a single image, TODO: Generalize
    private HashMap<Car,BufferedImage> pointAndImage = new HashMap<>();

    // To keep track of a single cars position
    Point imageCenterPoint = new Point();

    // TODO: Make this general for all cars
    void updateImagePosition(int x, int y){
        imageCenterPoint.x = x;
        imageCenterPoint.y = y;
    }
    void updatePanelObjects(Car c,BufferedImage image){
        pointAndImage.put(c,image);
    }

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
        // Print an error message in case file is not found with a try/catch block

    }

    // This method is called each time the panel updates/refreshes/repaints itself
    // TODO: Change to suit your needs.
    @Override
    protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            try {
                for(Map.Entry<Car, BufferedImage> tuple : pointAndImage.entrySet()) {
                    Car car = tuple.getKey();
                    int y = (int)car.getY();
                    int x = (int)car.getX();
                    BufferedImage i = tuple.getValue();
                    g.drawImage(i, x, y, null);
                }

            }catch (Exception e){

            }

    }
}
