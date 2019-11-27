package entity.bullet;

import com.sun.javafx.geom.Vec2d;
import entity.Enemies.Enemy;
import entity.GameEntity;
import entity.tower.Tower;

public abstract class Bullet extends GameEntity {
    private Enemy target;
    private double speed;
    protected double dame;
    private boolean destroyed = false;
    protected double angle;


    public void setDame(double dame) {
        this.dame = dame;
    }

    public double getDame() {
        return dame;
    }

    public Bullet(Tower tower) {
        super(tower.getxPos(), tower.getyPos());
        this.dame = dame;
        this.target = tower.getTarget();
        this.angle = tower.getAngle();
        this.speed = 10;
    }
    @Override
    public void update() {
        if(destroyed) return;

        double x1 = xPos;
        double y1 = yPos;

        if(target != null) {
            double x2 = target.getxPos();
            double y2 = target.getyPos();
            double d = Vec2d.distance(x1, y1, x2, y2);
            double dx = x2 - x1;
            double dy = y2 - y1;
            double vy;
            double vx;
            if (d >= speed) {
                vx = dx / d * speed;
                vy = dy / d * speed;

            } else {
                vx = dx;
                vy = dy;
            }
            xPos += vx;
            yPos += vy;
            //damage
            if(d <= speed) {
                target.damage(getDame());
                destroyed = true;
            }
        }
    }
    public boolean onDestroyed() {
        return destroyed;
    }
}
