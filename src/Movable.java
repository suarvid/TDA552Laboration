/**
 * Interface with methods for moving and turning.
 * Should be implemented by all movable objects.
 * @author Matte B, Arre S, Alle V
 * @version 1.0
 * @since 2018-11-07
 */


public interface Movable {

    /**
     * Changes position of object.
     */
    void move();

    /**
     * Changes direction counter-clockwise, in which object moves.
     */
    void turnLeft();


    /**
     * Changes direction clockwise, in which object moves.
     */
    void turnRight();


}
