import java.awt.*;
import java.util.Scanner;

public class DriveCar {
    public static void main(String[] args) {
        /*Volvo240 volvo = new Volvo240(Color.RED,180);
        volvo.startEngine();
//        volvo.printPosition();
        volvo.gas(1);
        volvo.move();
//        volvo.gas(0.8);
//        volvo.gas(0.8);
        volvo.printCurrentSpeed();
        volvo.printPosition();

        Saab95 saab = new Saab95(Color.GRAY, 200);
        saab.startEngine();
        saab.setTurboOn();
        saab.gas(1);
        saab.move();
        saab.printCurrentSpeed();
        saab.printPosition();

        Scania scania = new Scania(2,Color.BLACK,300);
        scania.startEngine();
        scania.gas(1);
        scania.move();
        scania.printCurrentSpeed();
        scania.printPosition();*/
        /*scania.printPosition();
        scania.startEngine();
        scania.gas(1);
        scania.move();
        scania.printPosition();
        scania.turnLeft();
        scania.move();
        scania.printPosition();
        scania.lowerFlatbed();
        scania.brake(1);
        scania.brake(1);
        scania.printCurrentSpeed();
        scania.lowerFlatbed();
        scania.brake(1);
        scania.brake(1);
        scania.printCurrentSpeed();
        scania.raiseFlatbed();
        scania.raiseFlatbed();
        scania.raiseFlatbed();
        scania.gas(1);
        scania.move();*/

        /*Volvo240 volvo240 = new Volvo240(Color.RED, 300);
        Scania brummis = new Scania(2,Color.MAGENTA,400);
        PorscheSpyder porscheSpyder = new PorscheSpyder(Color.BLACK,2);
        CarTransporter transporter = new CarTransporter(2, Color.BLUE, 400);
        transporter.lowerRamp();
        transporter.load(volvo240);
        transporter.load(brummis);
        transporter.load(porscheSpyder);
        transporter.raiseRamp();
        transporter.printPosition();
        transporter.startEngine();
        transporter.gas(1);
        transporter.gas(1);
        transporter.move();
        transporter.printPosition();
        volvo240.printPosition();
        System.out.println(transporter.getLoadedCars());*/

        CarTransporter transporter = new CarTransporter(2,Color.BLUE,300);
        Volvo240 volvo240 = new Volvo240(Color.ORANGE,180);

        transporter.printPosition();
        volvo240.printPosition();

        transporter.startEngine();
        transporter.gas(1);
        transporter.move();
        transporter.move();
        transporter.brake(1); 
        transporter.brake(1);
        transporter.lowerRamp();
        transporter.printPosition();
        volvo240.printPosition();
        transporter.load(volvo240);


    }
}
