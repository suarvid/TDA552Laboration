public interface Loadable <T> {

     void load(T t);

    boolean isFull();

    void unload();


}
