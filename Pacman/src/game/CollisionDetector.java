package game;

public class CollisionDetector {
    private GameMechanic game;

    public CollisionDetector(GameMechanic game) {
        this.game = game;
    }

    public AEntity checkCollision(AEntity obj, Class<? extends AEntity> collisionCheck) {
        for (AEntity e : game.getEntities()) {
            if (!e.isDestroyed() && collisionCheck.isInstance(e) && e.getHitbox().contains(obj.getxPos() + obj.getSize() / 2, obj.getyPos() + obj.getSize() / 2)) return e;
        }
        return null;
    }

    public AEntity checkCollisionRect(AEntity obj, Class<? extends AEntity> collisionCheck) {
        for (AEntity e : game.getEntities()) {
            if (!e.isDestroyed() && collisionCheck.isInstance(e) && e.getHitbox().intersects(obj.getHitbox())) return e;
        }
        return null;
    }
}
