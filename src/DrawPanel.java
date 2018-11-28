import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.swing.*;

// This panel represent the animated part of the view with the car images.

public class DrawPanel extends JPanel{

    private Map<Car,BufferedImage> carsAndImage;


    void setHashMap(Map<Car,BufferedImage> map){
        carsAndImage = map;
    }

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.orange);
        // Print an error message in case file is not found with a try/catch block

    }

    // This method is called each time the panel updates/refreshes/repaints itself
    @Override
    protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            try {
                int y = 0;
                int x = 0;

                for(Map.Entry<Car, BufferedImage> pair : carsAndImage.entrySet()) {
                    Car car = pair.getKey();
                    y = (int)car.getY();
                    x = (int)car.getX();
                    BufferedImage image = pair.getValue();
                    g.drawImage(image, x, y, null);
                }

            }catch (Exception e){

            }

    }
}
