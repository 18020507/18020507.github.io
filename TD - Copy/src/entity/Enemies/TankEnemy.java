package entity.Enemies;

import javafx.scene.image.Image;


public class TankEnemy extends Enemy {
    public TankEnemy(int[][] map, double xPos, double yPos) {
        super(map, xPos, yPos);
        image = new Image("Assets/TankEnemy.png", 32,32 ,false, true);
        speed = 0.5;
        hp = 30;
        money_get = 10;
    }
}
