import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.swing.*;

// This panel represent the animated part of the view with the car images.

public class DrawPanel extends JPanel{

    private HashMap<Car,BufferedImage> pointAndImage = new HashMap<>();


    void updatePanelObjects(Car c,BufferedImage image){
        pointAndImage.put(c,image);
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

                for(Map.Entry<Car, BufferedImage> tuple : pointAndImage.entrySet()) {
                    Car car = tuple.getKey();
                    y = (int)car.getY();
                    x = (int)car.getX();
                    BufferedImage i = tuple.getValue();
                    g.drawImage(i, x, y, null);
                }

            }catch (Exception e){

            }

    }
}
