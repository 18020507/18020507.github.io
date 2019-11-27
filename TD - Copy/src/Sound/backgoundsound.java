package Sound;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class backgoundsound {
    public static MediaPlayer backGroundSound = initBackSound();

    private static MediaPlayer initBackSound() {
        MediaPlayer mediaPlayer = new MediaPlayer(new Media(new File("src/Sound/audioclip-1574819103-144506.mp3").toURI().toString()));
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        return mediaPlayer;
    }
}
