import java.awt.*;

public abstract class Vehicle implements Movable {

    public enum Directions {LEFT, RIGHT, UP, DOWN}

    private String modelname;
    private Color color;
    private int totalWeight;
    private double currentSpeed;
    private Directions direction = Directions.UP;
    private double x;
    private double y;

    public Vehicle(String modelname, Color color, int totalWeight, double x, double y) {
        this.modelname = modelname;
        this.color = color;
        this.totalWeight = totalWeight;
        this.currentSpeed = 0;
        this.x = x;
        this.y = y;
    }

    public void move() {
        switch (direction) {
            case UP:
                y = y - currentSpeed;
                break;
            case LEFT:
                x = x - currentSpeed;
                break;
            case DOWN:
                y = y + currentSpeed;
                break;
            case RIGHT:
                x = x + currentSpeed;
                break;
        }
    }

//    public abstract void brake(double amount);




    public void turnLeft() {
        switch (direction) {
            case UP:
                direction = Directions.LEFT;
                break;
            case LEFT:
                direction = Directions.DOWN;
                break;
            case DOWN:
                direction = Directions.RIGHT;
                break;
            case RIGHT:
                direction = Directions.UP;
                break;
        }
    }

    public void turnRight() {
        switch (direction) {
            case UP:
                direction = Directions.RIGHT;
                break;
            case LEFT:
                direction = Directions.UP;
                break;
            case DOWN:
                direction = Directions.LEFT;
                break;
            case RIGHT:
                direction = Directions.DOWN;
                break;
        }
    }

    /**
     * @return gets the current speed of the car
     */
    double getCurrentSpeed() {
        return currentSpeed;
    }

    void setCurrentSpeed(double speed){
        currentSpeed = speed;
    }

    boolean isMoving() {
        return currentSpeed > 0;
    }

    public void printCurrentSpeed() {
        System.out.println(currentSpeed);
    }

    public void printPosition() {
        System.out.println(modelname + " is at position: " + "x: " + x + ", y: " + y);
    }

    void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return gets the x position of the car
     */
    double getX() {
        return x;
    }

    /**
     * @return gets the y position of the car
     */
    double getY() {
        return y;
    }



    /**
     * @return gets the color of the car
     */
    public Color getColor() {
        return color;
    }

    /**
     * Sets the color of the car to specified color
     *
     * @param color specified color
     */
    void setColor(Color color) {
        this.color = color;
    }



    int getTotalWeight() {
        return totalWeight;
    }

    void setTotalWeight(int weight) {
        this.totalWeight = weight;
    }


    /**
     * @return gets the current direction of the car
     */
    Directions getDirection() {
        return direction;
    }

    abstract void incrementSpeed(double amount);

    abstract void decrementSpeed(double amount);


}
