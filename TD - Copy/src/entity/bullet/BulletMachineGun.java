package entity.bullet;

import entity.Enemies.Enemy;
import entity.tower.Tower;
import graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class BulletMachineGun extends Bullet {

    public BulletMachineGun(Tower tower) {
        super(tower);
        image = new Image("Assets/Bullet_MachineGun.png");
        dame = 1;
    }

    @Override
    public void draw(GraphicsContext graphicsContext) {
        if(!onDestroyed()) {
            graphicsContext.drawImage(Sprite.rotate(image, angle), xPos, yPos);
        }
    }
}
