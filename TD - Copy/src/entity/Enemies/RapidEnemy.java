package entity.Enemies;

import javafx.scene.image.Image;


public class RapidEnemy extends Enemy {

    public RapidEnemy(int[][] map, double xPos, double yPos) {
        super(map, xPos, yPos);
        image = new Image("Assets/RapidEnemy.png", 32,32,false, true);
        speed = 2;
        hp = 10;
        money_get = 8;
    }
}
