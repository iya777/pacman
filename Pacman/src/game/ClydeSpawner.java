package game;

public class ClydeSpawner extends AGhostSpawner {
    @Override
    public AGhost SummonGhost(int xPos, int yPos) {
        return new Clyde(xPos, yPos);
    }
}
