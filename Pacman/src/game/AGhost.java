package game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public abstract class AGhost extends ADynamicEntity {
    protected AGhostState state;

    protected final AGhostState chaseMode;
    protected final AGhostState scatterMode;
    protected final AGhostState frightenedMode;
    protected final AGhostState eatenMode;
    protected final AGhostState houseMode;

    protected int modeTimer = 0;
    protected int frightenedTimer = 0;
    protected boolean isChasing = false;

    protected static BufferedImage frightenedSprite1;
    protected static BufferedImage frightenedSprite2;
    protected static BufferedImage eatenSprite;

    protected IGhostBehaviour strategy;

    public AGhost(int xPos, int yPos, String spriteName) {
        super(32, xPos, yPos, 2, spriteName, 2, 0.1f);

        chaseMode = new ChaseMode(this);
        scatterMode = new ScatterMode(this);
        frightenedMode = new FrightenedMode(this);
        eatenMode = new EatenMode(this);
        houseMode = new HouseMode(this);

        state = houseMode;

        try {
            frightenedSprite1 = ImageIO.read(getClass().getClassLoader().getResource("resources/img/ghost_frightened.png"));
            frightenedSprite2 = ImageIO.read(getClass().getClassLoader().getResource("resources/img/ghost_frightened_2.png"));
            eatenSprite = ImageIO.read(getClass().getClassLoader().getResource("resources/img/ghost_eaten.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void restore(){
        super.restore();
        this.modeTimer = 0;
        this.frightenedTimer = 0;
        this.isChasing = false;
        this.state = houseMode;
    }
    public void switchChaseMode() {
        state = chaseMode;
    }
    public void switchScatterMode() {
        state = scatterMode;
    }

    public void switchFrightenedMode() {
        frightenedTimer = 0;
        state = frightenedMode;
    }

    public void switchEatenMode() {
        state = eatenMode;
    }

    public void switchHouseMode() {
        state = houseMode;
    }

    public void switchChaseModeOrScatterMode() {
        if (isChasing) {
            switchChaseMode();
        }else{
            switchScatterMode();
        }
    }

    public IGhostBehaviour getStrategy() {
        return this.strategy;
    }

    public void setStrategy(IGhostBehaviour strategy) {
        this.strategy = strategy;
    }

    public AGhostState getState() {
        return state;
    }

    @Override
    public void update() {
        if (!GameMechanic.getFirstInput()) return; 
        if (state == frightenedMode) {
            frightenedTimer++;

            if (frightenedTimer >= (60 * 7)) {
                state.timerFrightenedModeOver();
            }
        }

        if (state == chaseMode || state == scatterMode) {
            modeTimer++;

            if ((isChasing && modeTimer >= (60 * 20)) || (!isChasing && modeTimer >= (60 * 5))) {
                state.timerModeOver();
                isChasing = !isChasing;
            }
        }

        if (xPos == 208 && yPos == 168) {
            state.outsideHouse();
        }

        if (xPos == 208 && yPos == 200) {
            state.insideHouse();
        }

        state.computeNextDir();
        updatePosition();
    }

    @Override
    public void render(Graphics2D g) {
        if (state == frightenedMode) {
            if (frightenedTimer <= (60 * 5) || frightenedTimer%20 > 10) {
                g.drawImage(frightenedSprite1.getSubimage((int)subimage * size, 0, size, size), this.xPos, this.yPos,null);
            }else{
                g.drawImage(frightenedSprite2.getSubimage((int)subimage * size, 0, size, size), this.xPos, this.yPos,null);
            }
        }else if (state == eatenMode) {
            g.drawImage(eatenSprite.getSubimage(direction * size, 0, size, size), this.xPos, this.yPos,null);
        }else{
            g.drawImage(sprite.getSubimage((int)subimage * size + direction * size * nbSubimagesPerCycle, 0, size, size), this.xPos, this.yPos,null);
        }

    }
}
