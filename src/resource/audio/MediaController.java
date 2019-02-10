package resource.audio;

import java.util.EnumMap;
import java.util.Map;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import lombok.Getter;

public class MediaController {

    public enum MEDIA {

        TOP_MENU("menu.mp3"), //
        CREATE("menu.mp3"), //
        BATTLE("battle.mp3"),; //

        @Getter
        private final String path;
        @Getter
        private final static Map<MEDIA, MediaPlayer> mediaMap;

        private MEDIA(String path) {
            this.path = path;
        }

        // とりあえずの初期化
        static {
            mediaMap = new EnumMap<>(MEDIA.class);
            for (MEDIA medi : MEDIA.values()) {
                mediaMap.put(medi, null);
            }
        }

        public void play() {
            MediaPlayer mp = mediaMap.get(this);
            if (mp == null) {
                mp = loadMediaPlayer();
                mediaMap.put(this, mp);
            }
            mp.setCycleCount(MediaPlayer.INDEFINITE);
            mp.muteProperty().set(false);
            mp.play();
        }

        public void stop() {
            MediaPlayer mp = mediaMap.get(this);
            if (mp == null) {
                mp = loadMediaPlayer();
                mediaMap.put(this, mp);
            }
            mp.stop();
        }

        public void mute() {
            MediaPlayer mp = mediaMap.get(this);
            mp.muteProperty().set(true);
        }


        public void restart() {
            MediaPlayer mp = mediaMap.get(this);
            mp.muteProperty().set(false);
        }

        private MediaPlayer loadMediaPlayer() {
            String externalForm = ClassLoader.getSystemResource(this.getPath()).toExternalForm();
            Media media = new Media(externalForm);
            return new MediaPlayer(media);
        }
    }
}
