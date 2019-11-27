package entity.tower;

import entity.Enemies.Enemy;
import entity.bullet.BulletMachineGun;
import entity.bullet.BulletNormalGun;
import graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class NormalTower extends Tower {
    public NormalTower(double xPos, double yPos, ArrayList<Enemy> enemyArrayList) {
        super(xPos, yPos, enemyArrayList);
        image = new Image("Assets/gun.png");
        image_base = new Image("Assets/base.png");
        price = 25;
        refund = 13;
    }

    @Override
    public void setRange() {
        this.range = 200;
    }

    @Override
    public void shoot() {
        if (tickDown <= 0 && (bullet == null || bullet.onDestroyed()) && target != null) {
            tickDown = 10;
            bullet = new BulletNormalGun(this);
        } else tickDown--;
    }

    @Override
    public void setTickDown(int tickDown) {
        this.tickDown = 10;
    }

    @Override
    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.drawImage(image_base, xPos, yPos);
        graphicsContext.drawImage(Sprite.rotate(image,angle), xPos, yPos);
        super.draw(graphicsContext);
    }
}
