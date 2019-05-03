package resource.img.enums;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.EnumMap;
import java.util.Map;
import javafx.util.Duration;
import lombok.Getter;
import resource.img.ImageAnimationView;

public enum NUMBER {

    ZERO("pict/num/0.png", 0), //
    ONE("pict/num/1.png", 1), //
    TWO("pict/num/2.png", 2), //
    THREE("pict/num/3.png", 3), //
    FOUR("pict/num/4.png", 4), //
    FIVE("pict/num/5.png", 5), //
    SIX("pict/num/6.png", 6), //
    SEVEN("pict/num/7.png", 7), //
    EIGHT("pict/num/8.png", 8), //
    NINE("pict/num/9.png", 9);//

    @Getter
    private final String path;
    private final int val;

    @Getter
    private final static Map<DIGIT, Map<NUMBER, ImageAnimationView>> imgMap;

    private NUMBER(String path, int val) {
        this.path = path;
        this.val = val;
    }

    // とりあえずの初期化
    static {
        imgMap = new EnumMap<>(DIGIT.class);
        for (DIGIT dig : DIGIT.values()) {
            imgMap.put(dig, new EnumMap<>(NUMBER.class));
        }
    }

    public static ImageAnimationView getImg(int dig, int num) {
        DIGIT digType = convertDIG(dig);
        NUMBER numType = convertNUM(num);
        Map<NUMBER, ImageAnimationView> digMap = imgMap.get(digType);
        ImageAnimationView imgVal = digMap.get(numType);
        if (imgVal == null) {
            URI externalForm = null;
            try {
                externalForm = ClassLoader.getSystemResource(numType.getPath()).toURI();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            File file = new File(externalForm);
            imgVal = new ImageAnimationView(Duration.millis(600), file, 14, 40);

            // 桁MAPに格納後、元MAPに明示的に詰める。
            digMap.put(numType, imgVal);
            imgMap.put(digType, digMap);
            System.out.println("☆桁数[" + dig + "] 数字[" + num + "]");
        }
        imgVal.setAnimationRange(1, imgVal.getMaxIndex());
        return imgVal;
    }

    private static NUMBER convertNUM(int num) {
        for (NUMBER numE : NUMBER.values()) {
            if (numE.val == num) {
                return numE;
            }
        }
        throw new IllegalArgumentException("入力値不正");
    }

    private static DIGIT convertDIG(int dig) {
        for (DIGIT digE : DIGIT.values()) {
            if (digE.val == dig) {
                return digE;
            }
        }
        throw new IllegalArgumentException("入力値不正");
    }

    enum DIGIT {
        ONE(1), //
        TWO(2), //
        THREE(3), //
        FOUR(4), //
        FIVE(5), //
        SIX(6), //
        SEVEN(7), //
        EIGHT(8), //
        NINE(9);//

        private final int val;

        private DIGIT(int val) {
            this.val = val;
        }
    }

}
