package entity.tower;

import entity.Enemies.Enemy;
import entity.bullet.BulletMachineGun;
import entity.bullet.BulletSniperGun;
import graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class MachineGun extends Tower {
    public MachineGun(double xPos, double yPos, ArrayList<Enemy> enemyArrayList) {
        super(xPos, yPos, enemyArrayList);
        image = new Image("Assets/gun1.png");
        image_base = new Image("Assets/base.png");
        price = 50;
        refund = price/2;
    }

    @Override
    public void setRange() {
        this.range = 150;
    }

    @Override
    public void shoot() {
        if (tickDown <= 0 && (bullet == null || bullet.onDestroyed()) && target != null) {
            tickDown = 1;
            bullet = new BulletMachineGun(this);
        } else tickDown--;
    }

    @Override
    public void setTickDown(int tickDown) {
        this.tickDown = 1;
    }

    @Override
    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.drawImage(image_base, xPos, yPos);
        graphicsContext.drawImage(Sprite.rotate(image,angle), xPos, yPos);
        super.draw(graphicsContext);
    }
}
