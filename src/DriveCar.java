import java.awt.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;

public class DriveCar {
    public static void main(String[] args) {

        CarTransporter carTransporter = new CarTransporter(2,Color.MAGENTA,100,"Trans",0,0);
        Ferry ferry = new Ferry("Ferriboi", Color.ORANGE,0,0);

        Volvo240 volvo240 = new Volvo240(Color.BLACK,180);
        Saab95 saab95 = new Saab95(Color.GRAY, 200);
        PorscheSpyder porscheSpyder = new PorscheSpyder(Color.RED,100);

        Scania scania = new Scania(2,Color.BLUE,300,0,0);

        ArrayList<Loadable> pasta = new ArrayList<>();
        pasta.add(scania);
        pasta.add(ferry);
        pasta.add(carTransporter);


    }
}
