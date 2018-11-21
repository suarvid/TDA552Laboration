public interface Loadable {

    public void load(Car car);

    //Blir fett konstigt om Scania ska vara med i loadable, då CT och Ferry loadar Cars, men den loadar typ en viss vikt av något.
    /*public void load(int amount);*/

    public boolean isFull();

    public void unload(int amount);



}
