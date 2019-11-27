package entity.Enemies;

import javafx.scene.image.Image;

public class NormalEnemy extends Enemy {

    public NormalEnemy(int[][] map, double xPos, double yPos) {
        super(map, xPos, yPos);
        image = new Image("Assets/nnEnemy.png", 32,32,false,true);
        speed = 1;
        hp=14;
        money_get = 5;
    }
}
