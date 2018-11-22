import java.awt.*;

public class DriveCar {
    public static void main(String[] args) {
        CarTransporter transporter = new CarTransporter(2,Color.ORANGE,300);
        Ferry ferry = new Ferry();

        ferry.load(transporter.referToCar());
        ferry.startEngine();
        ferry.gas(1);
        ferry.gas(1);
        ferry.move();
        ferry.move();
        ferry.printPosition();
        transporter.printPosition();


    }
}
