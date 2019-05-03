package resource.img;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.value.WritableValue;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 * アニメーション処理を実行できるImageViewクラス
 *
 */
public class ImageAnimationView extends ImageView {

    private Rectangle2D[] viewports;
    private Index index = new Index();
    private Timeline timeline;
    private Duration dur;
    private Image img;
    private int width;
    private int height;

    /**
     * 指定した画像を横幅、縦幅で分割し、指定期間で1アニメーションを実行するImageAnimationViewオブジェクトを構築します。
     *
     * @param dur
     * @param img
     * @param width
     * @param height
     */
    public ImageAnimationView(Duration dur, Image img, int width, int height) {

        // ディープコピー用に複写しておく
        this.dur = dur;
        this.img = img;
        this.width = width;
        this.height = height;

        // 画像が横に何枚あるか、縦に何枚あるかを算出します。
        int x = (int) img.getWidth() / width;
        int y = (int) img.getHeight() / height;

        // viewportの作成
        Rectangle2D[] viewports = new Rectangle2D[x * y];
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                viewports[i * x + j] = new Rectangle2D(j * width, i * height, width, height);
            }
        }
        this.dur = dur;
        this.viewports = viewports;
        setViewport(viewports[0]);
        setImage(img);
        timeline = new Timeline();
    }

    /**
     * 画像番号の範囲をアニメーションするように設定します。
     *
     * @param startIndex
     * @param endIndex
     */
    public void setAnimationRange(int startIndex, int endIndex) {

        timeline.stop();
        index.setValue(startIndex);

        timeline.getKeyFrames().clear();

        // 開始番号から終了番号までアニメーション
        @SuppressWarnings("unchecked")
        KeyFrame kf = new KeyFrame(dur, new KeyValue(index, endIndex, Interpolator.EASE_BOTH));
        timeline.getKeyFrames().add(kf);
    }

    /**
     * 全ての画像をアニメーションの対象とします。
     */
    public void setDefaultAnimationRange() {
        setAnimationRange(0, getMaxIndex());
    }

    /**
     * アニメーションを開始します。
     */
    public void play() {
        timeline.play();
    }

    /**
     * アニメーションのループ回数を指定します。
     *
     * @param value
     */
    public void setCycleCount(int value) {
        timeline.setCycleCount(value);
    }

    /**
     * 画像番号の最大値を取得します。
     *
     * @return
     */
    public int getMaxIndex() {
        return viewports.length - 1;
    }

    @SuppressWarnings("rawtypes")
    private class Index implements WritableValue {
        private int index = 0;

        @Override
        public Integer getValue() {
            return index;
        }

        @Override
        public void setValue(Object value) {
            this.index = (Integer) value;
            setViewport(viewports[index]);
        }
    }

    public ImageAnimationView deepCopy() {
        return new ImageAnimationView(dur, img, width, height);
    }
}
