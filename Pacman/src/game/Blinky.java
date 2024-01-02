package game;
public class Blinky extends AGhost {
    public Blinky(int xPos, int yPos) {
        super(xPos, yPos, "blinky.png");
        setStrategy(new BlinkyBehaviour());
    }
}
