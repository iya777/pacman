package game;

public interface IEventSystem {
    void registerObserver(IObserver observer);
    void removeObserver(IObserver observer);
    void notifyObserverPacGumEaten(PacGum pg);
    void notifyObserverSuperPacGumEaten(SuperPacGum spg);
    void notifyObserverGhostCollision(AGhost gh);
}
