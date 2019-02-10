package resource.img.enums;

import java.io.File;
import java.util.EnumMap;
import java.util.Map;
import javafx.util.Duration;
import lombok.Getter;
import resource.img.ImageAnimationView;

public enum NUMBER {

    ZERO("0.png", 0), //
    ONE("1.png", 1), //
    TWO("2.png", 2), //
    THREE("3.png", 3), //
    FOUR("4.png", 4), //
    FIVE("5.png", 5), //
    SIX("6.png", 6), //
    SEVEN("7.png", 7), //
    EIGHT("8.png", 8), //
    NINE("9.png", 9);//

    @Getter
    private final String path;
    private final int val;
    @Getter
    private final static Map<NUMBER, ImageAnimationView> imgMap;

    private NUMBER(String path, int val) {
        this.path = path;
        this.val = val;
    }

    // とりあえずの初期化
    static {
        imgMap = new EnumMap<>(NUMBER.class);
        for (NUMBER se : NUMBER.values()) {
            imgMap.put(se, null);
        }
    }

    public static ImageAnimationView getImg(int num) {
        NUMBER numType = convert(num);
        ImageAnimationView imgVal = imgMap.get(numType);
        if (imgVal == null) {
            String externalForm = ClassLoader.getSystemResource(numType.getPath()).toExternalForm();
            File file = new File(externalForm);
            imgVal = new ImageAnimationView(Duration.millis(600), file, 14, 40);
            imgVal.setAnimationRange(1, imgVal.getMaxIndex());
            imgMap.put(numType, imgVal);
        }
        return imgVal;
    }

    private static NUMBER convert(int num) {
        for (NUMBER numE : NUMBER.values()) {
            if (numE.val == num) {
                return numE;
            }
        }
        throw new IllegalArgumentException("入力値不正");
    }

}
