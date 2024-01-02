package game;

public class PinkyBehaviour implements IGhostBehaviour {
    @Override
    public int[] getChaseTargetPosition() {
        int[] position = new int[2];
        int[] pacmanFacingPosition = Utils.getPointDistanceDirection(GameMechanic.getPacman().getxPos(), GameMechanic.getPacman().getyPos(), 64, Utils.directionConverter(GameMechanic.getPacman().getDirection()));
        position[0] = pacmanFacingPosition[0];
        position[1] = pacmanFacingPosition[1];
        return position;
    }

    @Override
    public int[] getScatterTargetPosition() {
        int[] position = new int[2];
        position[0] = 0;
        position[1] = 0;
        return position;
    }
}
