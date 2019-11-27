package entity.Enemies;

import entity.GameEntity;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Rotate;

public abstract class Enemy extends GameEntity {

    protected double dangerous;
    protected double hp;
    Direction direction = Direction.RIGHT;
    protected double speed;
    protected double money_get;
    protected double angle = 0;

    public double getDangerous() {
        return dangerous;
    }


    int[][] map;

    public Enemy(int[][] map, double xPos, double yPos) {
        super(xPos, yPos);
        this.map = map;
    }

    public double getMoney_get() {
        return money_get;
    }

    @Override
    public void update() {
        double vx;
        double vy;
        direction = getDirect();
        switch (direction) {
            case LEFT:
                vx = -speed;
                vy = 0;
                break;
            case RIGHT:
                vx = speed;
                vy = 0;
                break;
            case UP:
                vx = 0;
                vy = -speed;
                break;
            default /*down*/:
                vx = 0;
                vy = speed;
        }
        xPos += vx;
        yPos += vy;
    }

    boolean check_destroy = false;
    boolean check_outOfMap = false;

    public Direction getDirect() {
        if (xPos % 32 != 0 || yPos % 32 != 0) return direction;
        int x = (int) xPos / 32;
        int y = (int) yPos / 32;

        switch (direction) {
            case RIGHT:
                angle = 0;
                if (x + 1 >= 32 || map[y][x + 1] == 0)
                    if (y - 1 >= 0 && map[y - 1][x] == 1) return Direction.UP;
                    else if (y + 1 < 16 && map[y + 1][x] == 1) return Direction.DOWN;
                    else {
                        check_destroy = true;
                        check_outOfMap = true;
                    }
                break;
            case LEFT:
                angle = 180;
                if (x - 1 < 0 || map[y][x - 1] == 0)
                    if (y + 1 < 16 && map[y + 1][x] == 1) return Direction.DOWN;
                    else if (y - 1 >= 0 && map[y - 1][x] == 1) return Direction.UP;
                    else {
                        check_destroy = true;
                        check_outOfMap = true;
                    }

                break;
            case UP:
                angle = 270;
                if (y - 1 < 0 || map[y - 1][x] == 0)
                    if (x + 1 < 32 && map[y][x + 1] == 1) return Direction.RIGHT;
                    else if (x - 1 >= 0 && map[y][x - 1] == 1) return Direction.LEFT;
                    else {
                        check_destroy = true;
                        check_outOfMap = true;
                    }
                break;
            case DOWN:
                angle = 90;
                if (y + 1 >= 16 || map[y + 1][x] == 0)
                    if (x - 1 >= 0 && map[y][x - 1] == 1) return Direction.LEFT;
                    else if (x + 1 < 32 && map[y][x + 1] == 1) return Direction.RIGHT;
                    else  {
                        check_destroy = true;
                        check_outOfMap = true;
                    }
                break;
        }
        return direction;
    }

    public void setHp(double hp) {
        this.hp = hp;
    }

    public void damage(double dame) {
        hp -= dame;
    }

    public boolean onDestroy() {
        if(check_destroy == true) return true;
        if(hp < 0) {
            return true;
        }
        return false;
    }

    public boolean outOfMap() {
        if(check_outOfMap == true) {
            return true;
        }
        return false;
    }

    public void draw(GraphicsContext graphicsContext) {
        drawRotatedImage(graphicsContext, this.image, angle, xPos, yPos);
        //System.out.println(hp);
    }

    private void rotate(GraphicsContext gc, double angle, double px, double py) {
        Rotate r = new Rotate(angle, px, py);
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
    }

    private void drawRotatedImage(GraphicsContext gc, Image image, double angle, double tlpx, double tlpy) {
        gc.save(); // saves the current state on stack, including the current transform
        rotate(gc, angle, tlpx + image.getWidth() / 2, tlpy + image.getHeight() / 2);
        gc.drawImage(image, tlpx, tlpy);
        gc.restore(); // back to original state (before rotation)
    }
}
