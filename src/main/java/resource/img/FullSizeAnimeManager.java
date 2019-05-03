package resource.img;

import java.net.URL;
import dto.chara.enums.GENDER;
import dto.chara.enums.JOBManage;
import dto.chara.enums.JOBManage.JOB;
import javafx.scene.image.Image;
import javafx.util.Duration;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import resource.img.enums.NAVI;
import resource.img.utils.ImgFactory;
import resource.img.utils.PlayUtil;

public class FullSizeAnimeManager {

    private static FullSizeAnimeManager instance = new FullSizeAnimeManager();

    private FullSizeAnimeManager() {}

    public static FullSizeAnimeManager getInstance() {
        return instance;
    }

    private static CharaCreationInfo[] _FullSize;
    @Getter
    @Setter
    private static boolean _FirstTime = false;
    @Getter
    @Setter
    private static int _NowViewing = 0;
    private static final int _InitView = 0;

    static {
        if (_FullSize == null) {
            _FullSize = new CharaCreationInfo[JOBManage._JobTypeCapacity];
        }
    }

    public void initFullSize() {

        resetNowViewing();
        // 軽くないので1回作ったら終わりにしたい
        if (is_FirstTime()) {
            return;
        }

        // 初期化と設定処理
        int cnt = 0;
        for (JOB job : JOB.values()) {
            cnt += makeFullSizeArr(job, cnt);
        }
        set_FirstTime(true);
    }

    private static void resetNowViewing() {
        set_NowViewing(_InitView);
    }

    private int makeFullSizeArr(JOB job, int cnt) {

        // 画像ノードをルートに追加
        URL uri = ClassLoader.getSystemResource(job.getFullSizePath());
        if (uri == null) {
            // TODO : この分岐はENUM定義に存在する全ての画像を用意したら消す
            return 0;
        }

        Image img = ImgFactory.getInstance().create(job.getFullSizePath());
        ImageAnimationView imgAnmVw = new ImageAnimationView(Duration.millis(800), img, 240, 240);
        PlayUtil.playInfinityLoop(imgAnmVw);

        CharaCreationInfo info = new CharaCreationInfo();
        info.setImg(imgAnmVw);
        info.setJob(job);
        _FullSize[cnt] = info;
        return 1;
    }

    public static ImageAnimationView moveView(NAVI direct) {
        switch (direct) {
            case LEFT:
                // ポインタを左に1個ずらす
                return _FullSize[calcWhere(declement())].getImg();
            case RIGHT:
                // ポインタを右に1個ずらす
                return _FullSize[calcWhere(inclement())].getImg();
            default:
                throw new IllegalArgumentException("getImage 入力値がおかしい:" + direct);
        }
    }

    private static int declement() {
        int now = get_NowViewing();
        now--;
        set_NowViewing(now);
        return get_NowViewing();
    }

    private static int inclement() {
        int now = get_NowViewing();
        now++;
        set_NowViewing(now);
        return get_NowViewing();
    }

    /**
     * 現在どの配列要素を見ているか判定し、ポインタを変更する.
     * <p>
     * {@code _NowViewing}を変更するので、リファクタする際に注意する
     *
     * @param nowPoint
     * @return
     */
    private static int calcWhere(int nowPoint) {

        if (nowPoint < 0) {
            // マイナス値(一番左の要素のさらに左）になったら配列の一番右へポインタを動かす
            set_NowViewing(JOBManage._JobTypeCapacity - 1);
            return get_NowViewing();
        }

        if (nowPoint >= JOBManage._JobTypeCapacity) {
            // 配列最大値(一番右の要素のさらに右)を超えたら一番左へポインタを動かす
            resetNowViewing();
            return nowPoint % JOBManage._JobTypeCapacity;

        } else {
            // マイナス～最大値までの間
            return nowPoint % JOBManage._JobTypeCapacity;
        }
    }

    public static ImageAnimationView getNowFullSizeImage() {
        return _FullSize[get_NowViewing()].getImg();
    }

    public static CharaCreationInfo getCreationInfo() {
        return _FullSize[get_NowViewing()];
    }

    @Data
    public class CharaCreationInfo {
        private ImageAnimationView img;
        private JOB job;
        private GENDER gender;
    }
}
