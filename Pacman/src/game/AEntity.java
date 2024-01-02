package game;

import java.awt.*;

public abstract class AEntity {
    /* Restart Mechanic */
    protected int restore_size;
    protected int restore_xPos;
    protected int restore_yPos;
  
    protected int size;
    protected int xPos;
    protected int yPos;

    protected boolean destroyed = false;

    public AEntity(int size, int xPos, int yPos) {
        this.size = size;
        this.xPos = xPos;
        this.yPos = yPos;
        this.restore_xPos = xPos;
        this.restore_yPos = yPos;
        this.restore_size = size;
    }

    public void update() {}

    public void render(Graphics2D g) {}

    public void destroy() {
        this.xPos = -32;
        this.yPos = -32;
        destroyed = true;
    }
    public void restore(){
        this.xPos = restore_xPos;
        this.yPos = restore_yPos;
        this.destroyed = false;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public int getSize() {
        return size;
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public abstract Rectangle getHitbox();
}
