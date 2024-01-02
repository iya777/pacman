package game;

public class BlinkyBehaviour implements IGhostBehaviour{
    @Override
    public int[] getChaseTargetPosition() {
        int[] position = new int[2];
        position[0] = GameMechanic.getPacman().getxPos();
        position[1] = GameMechanic.getPacman().getyPos();
        return position;
    }

    @Override
    public int[] getScatterTargetPosition() {
        int[] position = new int[2];
        position[0] = GameMechanic.width;
        position[1] = 0;
        return position;
    }
}
