package entity.tower;

import com.sun.javafx.geom.Vec2d;
import entity.Enemies.Enemy;
import entity.GameEntity;
import entity.bullet.Bullet;
import entity.bullet.BulletMachineGun;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.Collections;

public abstract class Tower extends GameEntity {
    private ArrayList<Enemy> enemyArrayList;
    Bullet bullet;
    protected Enemy target = null;
    protected double angle;
    protected double price;
    protected double refund;

    public Enemy getTarget() {
        return target;
    }

    public double getAngle() {
        return angle;
    }

    public double getPrice() {return price; }

    public double getRefund() {return refund; }

    protected double range;

    protected double tickDown;

    public Tower(double xPos, double yPos, ArrayList<Enemy> enemyArrayList) {
        super(xPos, yPos);
        this.enemyArrayList = enemyArrayList;
    }
    public abstract void setRange();

    public double getRange() {
        return range;
    }

    @Override
    public void update() {
        aim();
        if(enemyArrayList.size() > 0)
        shoot();
        if (bullet != null) bullet.update();
    }

    @Override
    public void draw(GraphicsContext graphicsContext) {
        if (bullet != null) bullet.draw(graphicsContext);
    }
    
    public abstract void shoot();

    public void setTickDown(int tickDown) {
        this.tickDown = tickDown;
    }

    private void aim() {
        //select
        if (!enemyArrayList.isEmpty()) {
//            Collections.sort(enemyArrayList, (Enemy a, Enemy b) -> {
//                if (a.getDangerous() > b.getDangerous()) return -1;
//                if (a.getDangerous() < b.getDangerous()) return 1;
//                return 0;
//            });
            if(enemyArrayList.size() > 0)
            for (Enemy enemy : enemyArrayList) {
                if ((Math.abs(enemy.getxPos() - xPos) < getRange()/2 && Math.abs(enemy.getyPos() - yPos)  < getRange())) {
                    target = enemy;
                    break;
                } else {
                    target = null;
                }
            }
        }
        //spin
        double x1 = xPos;
        double y1 = yPos;

        if(target != null) {
            double x2 = target.getxPos();
            double y2 = target.getyPos();
            double d = Vec2d.distance(x1, y1, x2, y2);

            double cosA = (x2 - x1) / d;
            double sinA = (y2 - y1) / d;

            angle = (Math.acos(cosA) * 180 / Math.PI);
            if (sinA > 0 && angle > 180) angle = -angle;
            if (sinA < 0 && angle < 180) angle = -angle;
            angle += 90;
        }
    }
}
