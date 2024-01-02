package game;

public class PinkySpawner extends AGhostSpawner {
    @Override
    public AGhost SummonGhost(int xPos, int yPos) {
        return new Pinky(xPos, yPos);
    }
}
