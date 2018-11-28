import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

// This panel represent the animated part of the view with the car images.

public class DrawPanel extends JPanel{

    // Just a single image, TODO: Generalize
    private BufferedImage currentImage;

    // To keep track of a singel cars position
    Point imageCenterPoint = new Point();

    // TODO: Make this general for all cars
    void updateImagePosition(int x, int y){
        imageCenterPoint.x = x;
        imageCenterPoint.y = y;
    }
    void setCurrentImage(BufferedImage bf){
        currentImage = bf;
    }

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);


    }

    // This method is called each time the panel updates/refreshes/repaints itself
    // TODO: Change to suit your needs.
    @Override
    protected void paintComponent(Graphics g) {
        try {
            super.paintComponent(g);
            g.drawImage(currentImage, imageCenterPoint.x , imageCenterPoint.y, null);
        }catch (NullPointerException n){
            System.out.println(n.getMessage());
        }
    }
}
