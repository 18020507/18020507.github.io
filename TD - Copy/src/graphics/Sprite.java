package graphics;

import javafx.geometry.Rectangle2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class Sprite {



    public static Image rotate(Image image, double angle) {
        ImageView imageView = new ImageView(image);
        imageView.setRotate(angle);

        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setViewport(new Rectangle2D(0, 0, image.getWidth(), image.getHeight()));
        parameters.setFill(Color.TRANSPARENT);
        return imageView.snapshot(parameters, null);

    }
}
