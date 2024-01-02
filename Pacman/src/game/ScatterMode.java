package game;

public class ScatterMode extends AGhostState{
    public ScatterMode(AGhost ghost) {
        super(ghost);
    }

    @Override
    public void superPacGumEaten() {
        ghost.switchFrightenedMode();
    }

    @Override
    public void timerModeOver() {
        ghost.switchChaseMode();
    }

    @Override
    public int[] getTargetPosition() {
        return ghost.getStrategy().getScatterTargetPosition();
    }
}
