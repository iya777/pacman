package game;

import java.awt.*;

public class WallCollisionDetector {

    public static boolean checkWallCollision(AEntity obj, int dx, int dy) {
        Rectangle r = new Rectangle(obj.getxPos() + dx, obj.getyPos() + dy, obj.getSize(), obj.getSize());
        for (Wall w : GameMechanic.getWalls()) {
            if (w.getHitbox().intersects(r)) return true;
        }
        return false;
    }

    public static boolean checkWallCollision(AEntity obj, int dx, int dy, boolean ignoreGhostHouses) {
        Rectangle r = new Rectangle(obj.getxPos() + dx, obj.getyPos() + dy, obj.getSize(), obj.getSize());
        for (Wall w : GameMechanic.getWalls()) {
            if (!(ignoreGhostHouses && w instanceof GhostHouse) && w.getHitbox().intersects(r)) return true;
        }
        return false;
    }
}
