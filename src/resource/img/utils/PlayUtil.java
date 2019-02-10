package resource.img.utils;

import javafx.animation.Animation;
import resource.img.ImageAnimationView;

public class PlayUtil {

    public static void playInfinityLoop(ImageAnimationView img) {
        // 歩いているグラフィックの範囲を表示
        img.setAnimationRange(1, img.getMaxIndex());

        // 無限ループアニメーションの開始
        img.setCycleCount(Animation.INDEFINITE);
        img.play();
    }
}
