/**
 * Interface with methods for loading and unloading objects as well as checking if the <i>Loadable</i> object is full.
 * Should be implemented by all objects that load and unload other objects.
 * @param <T> arbitrary type of object to be loaded.
 * @author Matte B, Arre S, Alle V
 * @version 1.0
 * @since 2018-11-07
 */
public interface Loadable<T>  {

    /**
     * Loads the loadable with an object of arbitrary type.
     * @param t object to be loaded.
     */
    void load(T t);

    /**
     * Checks whether the loadable is full or not.
     * @return true if loadable is full.
     */
    boolean isFull();

    /**
     * Unloads an object of arbitrary type from the loadable.
     */
    void unload();


}
