package game;

public class ClydeBehaviour implements IGhostBehaviour{
    private AGhost ghost;
    public ClydeBehaviour(AGhost ghost) {
        this.ghost = ghost;
    }

    @Override
    public int[] getChaseTargetPosition() {
        if (Utils.getDistance(ghost.getxPos(), ghost.getyPos(), GameMechanic.getPacman().getxPos(), GameMechanic.getPacman().getyPos()) >= 256) {
            int[] position = new int[2];
            position[0] = GameMechanic.getPacman().getxPos();
            position[1] = GameMechanic.getPacman().getyPos();
            return position;
        }else{
            return getScatterTargetPosition();
        }
    }

    @Override
    public int[] getScatterTargetPosition() {
        int[] position = new int[2];
        position[0] = 0;
        position[1] = GameMechanic.height;
        return position;
    }
}
