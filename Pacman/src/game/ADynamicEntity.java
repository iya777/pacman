package game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public abstract class ADynamicEntity extends AEntity {
    protected int spd;
    protected int xSpd = 0;
    protected int ySpd = 0;
    protected BufferedImage sprite;
    protected float subimage = 0;
    protected int nbSubimagesPerCycle;
    protected int direction = 0;
    protected float imageSpd = 0.2f;

    public ADynamicEntity(int size, int xPos, int yPos, int spd, String spriteName, int nbSubimagesPerCycle, float imageSpd) {
        super(size, xPos, yPos);
        this.spd = spd;
        try {
            this.sprite = ImageIO.read(getClass().getClassLoader().getResource("resources/img/" + spriteName));
            this.nbSubimagesPerCycle = nbSubimagesPerCycle;
            this.imageSpd = imageSpd;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {
        updatePosition();
    }
    
    @Override
    public void restore(){
        super.restore();
        this.xSpd = 0;
        this.ySpd = 0;
        this.direction = 0;
    }
    
    public void updatePosition() {
        if (!(xSpd == 0 && ySpd == 0)) {
            xPos+=xSpd;
            yPos+=ySpd;

            if (xSpd > 0) {
                direction = 0;
            } else if (xSpd < 0) {
                direction = 1;
            } else if (ySpd < 0) {
                direction = 2;
            } else if (ySpd > 0) {
                direction = 3;
            }

            subimage += imageSpd;
            if (subimage >= nbSubimagesPerCycle) {
                subimage = 0;
            }
        }

        if (xPos > GameMechanic.width) {
            xPos = 0 - size + spd;
        }

        if (xPos < 0 - size + spd) {
            xPos = GameMechanic.width;
        }

        if (yPos > GameMechanic.height) {
            yPos = 0 - size + spd;
        }

        if (yPos < 0 - size + spd) {
            yPos = GameMechanic.height;
        }
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(sprite.getSubimage((int)subimage * size + direction * size * nbSubimagesPerCycle, 0, size, size), this.xPos, this.yPos,null);
    }

    public boolean onTheGrid() {
        return (xPos%8 == 0 && yPos%8 == 0);
    }

    public boolean onGameplayWindow() { return !(xPos<=0 || xPos>= GameMechanic.width || yPos<=0 || yPos>= GameMechanic.height); }

    public Rectangle getHitbox() {
        return new Rectangle(xPos, yPos, size, size);
    }

    public BufferedImage getSprite() {
        return sprite;
    }

    public void setSprite(BufferedImage sprite) {
        this.sprite = sprite;
    }

    public void setSprite(String spriteName) {
        try {
            this.sprite = ImageIO.read(getClass().getClassLoader().getResource("img/" + spriteName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public float getSubimage() {
        return subimage;
    }

    public void setSubimage(float subimage) {
        this.subimage = subimage;
    }

    public int getNbSubimagesPerCycle() {
        return nbSubimagesPerCycle;
    }

    public void setNbSubimagesPerCycle(int nbSubimagesPerCycle) {
        this.nbSubimagesPerCycle = nbSubimagesPerCycle;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getxSpd() {
        return xSpd;
    }

    public void setxSpd(int xSpd) {
        this.xSpd = xSpd;
    }

    public int getySpd() {
        return ySpd;
    }

    public void setySpd(int ySpd) {
        this.ySpd = ySpd;
    }

    public int getSpd() {
        return spd;
    }
}
