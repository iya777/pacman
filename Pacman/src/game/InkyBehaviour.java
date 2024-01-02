package game;

public class InkyBehaviour implements IGhostBehaviour{
    private AGhost otherGhost;
    public InkyBehaviour(AGhost ghost) {
        this.otherGhost = ghost;
    }

    @Override
    public int[] getChaseTargetPosition() {
        if (this.otherGhost == null){
            return getScatterTargetPosition();
        }
        int[] position = new int[2];
        int[] pacmanFacingPosition = Utils.getPointDistanceDirection(GameMechanic.getPacman().getxPos(), GameMechanic.getPacman().getyPos(), 32d, Utils.directionConverter(GameMechanic.getPacman().getDirection()));
        double distanceOtherGhost = Utils.getDistance(pacmanFacingPosition[0], pacmanFacingPosition[1], otherGhost.getxPos(), otherGhost.getyPos());
        double directionOtherGhost = Utils.getDirection(otherGhost.getxPos(), otherGhost.getyPos(), pacmanFacingPosition[0], pacmanFacingPosition[1]);
        int[] blinkyVectorPosition = Utils.getPointDistanceDirection(pacmanFacingPosition[0], pacmanFacingPosition[1], distanceOtherGhost, directionOtherGhost);
        position[0] = blinkyVectorPosition[0];
        position[1] = blinkyVectorPosition[1];
        return position;
    }

    @Override
    public int[] getScatterTargetPosition() {
        int[] position = new int[2];
        position[0] = GameMechanic.width;
        position[1] = GameMechanic.height;
        return position;
    }
}
