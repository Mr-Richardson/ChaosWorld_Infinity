package io.github.richardson.gameplay;

import io.github.richardson.Cursor;
import io.github.richardson.menu.Settings;
import processing.core.PApplet;

public class Gameplay {
    private final PApplet p;
    private int seed, score;
    private Cursor cursor;
    private Background bg;
    private Character player;
    private ObstacleManager obstacles;
    private Camera camera;
    private Settings settings;  //FIXME

    public Gameplay(PApplet p, ObstacleManager obstacleManager) {
        this.p = p;
        reset();
    }

    public void main() {    //FIXME
        key_inputs();
        obstacles.generate();
        player.movement();
        bg.render();
        p.fill(255);
        p.textAlign(PApplet.LEFT, PApplet.BOTTOM);
        p.textSize(13);
        p.text(seed, 1, p.height - 1); //  seed printing
        p.textAlign(PApplet.LEFT, PApplet.TOP);
        p.textSize(p.height * 0.03f);
        if (score < player.getPosX() * 0.01f) {
            score = (int) (player.getPosX() * 0.01f);
        }
        p.text(score, p.height * 0.01f, p.height * 0.01f); //  distance printing
        camera.move(player.getPosX());
        p.scale(1, -1);
        player.render();
        if (player.getPosX() < settings.getDeathY() || keyR) {
            reset();
        }
        cursor.hideCheck();
    }

    public void reset() {    //FIXME
        seed = (int) p.random(Integer.MAX_VALUE);
        p.randomSeed(seed); //FIXME: i switched to Javas random
        java.lang.System.out.print(seed);

        score = 0;

        cursor = null;
        bg = null;
        player = null;
        obstacles = null;
        camera = null;

        cursor = new Cursor(p, 3000, true);
        bg = new Background(p, p.color(0, 50, 200), p.color(0));
        player = new Character(p, settings.getPlayerRadius(), settings.getPlayerMaxJump(), settings.getPlayerMaxSpeed(), settings.getPlayerAirInertia(), settings.getPlayerStartX(), settings.getPlayerStartY());
        obstacles = new ObstacleManager(p, this.player, settings);
        camera = new Camera(p, settings, 0f);
    }
}