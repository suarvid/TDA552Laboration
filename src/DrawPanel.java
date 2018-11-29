import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.swing.*;

/** This panel class represent the animated part of the view with the car images.
 *
 * @author Matte B, Arre S, Alle V, Alex G
 * @version 1.0
 * @since 2018-11-29
 */

public class DrawPanel extends JPanel{

    private Map<Car,BufferedImage> carsAndImage;

    /**
     * Sets map in DrawPanel to a HashMap<Car,Image> from car controller
     * @param map a map with cars and their respective image to display
     */
    void setHashMap(Map<Car,BufferedImage> map){
        carsAndImage = map;
    }

    /**
     * Initializes the panel and set background color
     * @param panelWidth width of panel
     * @param panelHeight height of panel
     */
    DrawPanel(int panelWidth, int panelHeight) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(panelWidth, panelHeight));
        this.setBackground(Color.orange);
    }

    /**
     * This method is called each time the panel updates and repaints itself
     * @param g graphics from JPanel
     */

    @Override
    protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            try {
                int y;
                int x;

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
