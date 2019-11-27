package entity.Enemies;

import javafx.scene.image.Image;

public class Plane extends Enemy {

    public Plane (int[][] map, double xPos, double yPos) {
        super(map, xPos, yPos);
        image = new Image("Assets/Plane.png", 40,40,false,true);
        speed = 0.7;
        angle = -30;
        hp = 20;
        money_get = 15;
    }

    @Override
    public void update() {
        xPos += speed;
        yPos += (-speed + 0.52);

        int x = (int) xPos/32;
        int y = (int) yPos/32;
        if(x+1 >= 32) {
            check_destroy = true;
            check_outOfMap = true;
        }
    }
}