package entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class GameEntity{
    protected double xPos;
    protected double yPos;

    protected Image image;
    protected Image image_base;

    public double getxPos() {
        return xPos;
    }

    public void setxPos(double xPos) {
        this.xPos = xPos;
    }

    public double getyPos() {
        return yPos;
    }

    public void setyPos(double yPos) {
        this.yPos = yPos;
    }

    public GameEntity(double xPos, double yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public abstract void update();
    public abstract void draw(GraphicsContext graphicsContext);
}
