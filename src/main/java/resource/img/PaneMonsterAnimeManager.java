package resource.img;

import dto.chara.enums.EnemyEnums.ENEMY;
import javafx.scene.image.ImageView;

public class PaneMonsterAnimeManager {

    private static PaneMonsterAnimeManager instance = new PaneMonsterAnimeManager();

    public static PaneMonsterAnimeManager getInstance() {
        return instance;
    }

    public ImageView newImage(ENEMY enemy) {
        return new ImageView(ClassLoader.getSystemResource(enemy.getPath()).toExternalForm());
    }
}
