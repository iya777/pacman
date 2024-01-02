package game;

public class BlinkySpawner extends AGhostSpawner {
    @Override
    public AGhost SummonGhost(int xPos, int yPos) {
        return new Blinky(xPos, yPos);
    }
}
