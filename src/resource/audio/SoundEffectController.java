package resource.audio;

import java.util.EnumMap;
import java.util.Map;
import application.thread.ThreadUtil;
import javafx.scene.media.AudioClip;
import lombok.Getter;

public class SoundEffectController {

    public enum SE {

        DECISION("decision.mp3"), //
        CANCEL("cancel.mp3"), //
        CURSOR("cursor.mp3"), //
        ITEM_SET("itemSet.mp3"), //
        CALL("call.mp3"), //
        OUTSIDE("outside.mp3"),; //

        @Getter
        private final String path;
        @Getter
        private final static Map<SE, AudioClip> seMap;

        private SE(String path) {
            this.path = path;
        }

        // とりあえずの初期化
        static {
            seMap = new EnumMap<>(SE.class);
            for (SE se : SE.values()) {
                seMap.put(se, null);
            }
        }

        public void play() {
            AudioClip se = seMap.get(this);
            if (se == null) {
                se = loadSE();
                seMap.put(this, se);
            }
            se.play();
        }

        public void playWhile(long waitTime) {
            AudioClip se = seMap.get(this);
            if (se == null) {
                se = loadSE();
                seMap.put(this, se);
            }
            se.play();
            ThreadUtil.sleep(waitTime);
            se.stop();
        }

        public void stop() {
            AudioClip se = seMap.get(this);
            if (se == null) {
                se = loadSE();
                seMap.put(this, se);
            }
            se.stop();
        }

        private AudioClip loadSE() {
            String externalForm = ClassLoader.getSystemResource(this.getPath()).toExternalForm();
            return new AudioClip(externalForm);
        }
    }
}
