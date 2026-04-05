package io.github.richardson.gameplay;

import io.github.richardson.ObjectManager;
import processing.core.PApplet;

public class Gameplay {
    private final PApplet p;
    private int seed, score;
    private Background bg;
    private Character player;
    private ObjectManager objectManager;
    private ObstacleManager obstacles;
    private Camera camera;

    public Gameplay(PApplet p, ObjectManager objectManager) {
        this.p = p;
        this.objectManager = objectManager;
        reset();
    }

    public void main() {
        player.velocityUpdate();
        obstacles.generate();
        player.movement();
        bg.render();
        p.fill(255);
        p.textAlign(PApplet.LEFT, PApplet.BOTTOM);
        p.textSize(13);
        p.text(seed, 1, p.height - 1); //  seed printing
        p.textAlign(PApplet.LEFT, PApplet.TOP);
        p.textSize(p.height * 0.03f);
        if (score < player.getPos().x * 0.01f) {
            score = (int) (player.getPos().x * 0.01f);
        }
        p.text(score, p.height * 0.01f, p.height * 0.01f); //  distance printing
        camera.move(player.getPos().x);
        p.scale(1, -1);
        player.render();
        if (player.getPos().x < objectManager.settings.getDeathY() || p.key == objectManager.settings.getKeyReset()) {
            reset();
        }
        objectManager.cursor.hideCheck();
    }

    public void reset() {
        seed = (int) p.random(Integer.MAX_VALUE);
        p.randomSeed(seed); //FIXME: i switched to Javas random
        java.lang.System.out.print(seed);

        score = 0;

        bg = null;
        player = null;
        obstacles = null;
        camera = null;

        bg = new Background(p, p.color(0, 50, 200), p.color(0));
        player = new Character(p, obstacles, objectManager.key, objectManager.settings);
        obstacles = new ObstacleManager(p, this.player, objectManager.settings);
        camera = new Camera(p, objectManager.settings, 0f);
    }
}