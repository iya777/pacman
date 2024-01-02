package game;

public class InkySpawner extends AGhostSpawner {
    @Override
    public AGhost SummonGhost(int xPos, int yPos) {
        return new Inky(xPos, yPos);
    }
}
