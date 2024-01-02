package game;

public class FrightenedMode extends AGhostState{
    public FrightenedMode(AGhost ghost) {
        super(ghost);
    }

    @Override
    public void eaten() {
        ghost.switchEatenMode();
    }

    @Override
    public void timerFrightenedModeOver() {
        ghost.switchChaseModeOrScatterMode();
    }

    @Override
    public int[] getTargetPosition(){
        int[] position = new int[2];

        boolean randomAxis = Utils.randomBool();
        position[0] = ghost.getxPos() + (randomAxis ? Utils.randomInt(-1,1) * 32 : 0);
        position[1] = ghost.getyPos() + (!randomAxis ? Utils.randomInt(-1,1) * 32 : 0);
        return position;
    }
}
