package game;
public class Clyde extends AGhost {
    public Clyde(int xPos, int yPos) {
        super(xPos, yPos, "clyde.png");
        setStrategy(new ClydeBehaviour(this));
    }
}
