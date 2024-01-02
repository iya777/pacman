package game;

public class Inky extends AGhost {
    public Inky(int xPos, int yPos) {
        super(xPos, yPos, "inky.png");
        setStrategy(new InkyBehaviour(GameMechanic.getBlinky()));
    }
}
