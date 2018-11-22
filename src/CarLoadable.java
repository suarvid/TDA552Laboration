public interface CarLoadable {

    <T extends Car> void load(T t);

    boolean isFull();

    void unload();

    void unloadAll();

}
