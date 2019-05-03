package resource.img.utils;

import javafx.scene.image.Image;
import lombok.Getter;

/**
 * イメージを生成するファクトリー.
 * <p>
 * パスが異なるだけで、処理は同一なのでクラス化した。<br>
 * コンテナに格納しておいた方がメモリ効率は良いはずなので、検討する
 *
 * @author Naoto Wada
 *
 */
public class ImgFactory {

    @Getter
    private static ImgFactory instance = new ImgFactory();

    /**
     * Imageを作成して返却する.
     * <p>
     * 規定パスの先頭に{@code /}を付与する事で、実行可能jarとなってもリソースが読み込める。<br>
     * 付与しないと、パスの関係上読み込みができずエラーとなるのでそれを防ぐために作成<br>
     *
     * 将来的には設定パスそのものを変更して対応する
     *
     * @param path 相対パス名
     * @return イメージインスタンス
     */
    public Image create(String path) {
        return new Image(getClass().getResourceAsStream("/" + path));
    }

}
