package game;
public class Pinky extends AGhost {
    public Pinky(int xPos, int yPos) {
        super(xPos, yPos, "pinky.png");
        setStrategy(new PinkyBehaviour());
    }
}
