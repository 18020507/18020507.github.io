package entity.bullet;

import entity.tower.Tower;
import graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class BulletNormalGun extends Bullet {
    public BulletNormalGun(Tower tower) {
        super(tower);
        image = new Image("Assets/Bullet_Normal.png");
        dame = 2;
    }

    @Override
    public void draw(GraphicsContext graphicsContext) {
        if(!onDestroyed()) {
            graphicsContext.drawImage(Sprite.rotate(image, angle), xPos, yPos);
        }
    }
}
