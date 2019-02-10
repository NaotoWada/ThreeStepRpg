package resource.img.utils;

import javafx.animation.Animation;
import resource.img.ImageAnimationView;

public class ScaleUtil {

    public static void resetScales(ImageAnimationView img) {
        // 画像を等倍に戻す
        img.setScaleX(1.00);
        img.setScaleY(1.00);

        // 無限ループアニメーションの開始
        img.setCycleCount(Animation.INDEFINITE);
        // アニメーション実行が解除されているので再設定
        img.setAnimationRange(1, img.getMaxIndex());
    }

}
