package resource.img;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * 透過ファイルを作るクラス.
 * <p>
 *
 * @author Naoto Wada
 *
 */
public class Trans {
    private static String in = "C:/workspace/3StepRPG/monsterPicture/slime.png";
    private static String out = addSuffix2();

    private static String addSuffix2() {
        return in.split(".png")[0] + "2" + ".png";
    }

    public static void main(String[] args) {

        Trans gif = new Trans();
        gif.convertImage(in, out, Color.WHITE);
    }

    public boolean convertImage(String filePath, String afterFilePath, Color color) {
        File file = new File(filePath);
        if (!file.exists()) {
            return false;
        }
        BufferedImage bi = null;
        try {
            bi = ImageIO.read(file);
            changeTransparent(bi, color);

            ImageIO.write(bi, "png", new File(afterFilePath));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            bi = null;
        }
        return true;
    }

    private static void changeTransparent(BufferedImage img, Color c) {
        int w = img.getWidth(); // BufferedImageの幅
        int h = img.getHeight(); // BufferedImageの高さ
        int t = c.getRGB(); // 透明色に変換する色のRGB値

        // RGB値を0(透明色)に置換
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                if (img.getRGB(x, y) == t) {
                    img.setRGB(x, y, 0);
                }
            }
        }
    }
}
