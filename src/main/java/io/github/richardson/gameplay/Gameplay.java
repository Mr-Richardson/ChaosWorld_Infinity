package io.github.richardson.gameplay;

import io.github.richardson.Cursor;
import processing.core.PApplet;

public class Gameplay {
    private final PApplet p;
    private int seed, score;
    private Cursor cursor;
    private Background bg;
    private Character player;

    public Gameplay (PApplet p){
        this.p=p;
        cursor = new Cursor(p, 3000, true);
        bg = new Background(p, p.color(0, 50, 200), p.color(0));
        player = new Character(p, 35, 30, 1.2f, 5.0f, 150.0f, 500.0f);
    }

    public void main() {    //FIXME
        key_inputs();
        generateObstacles();
        player.movement();
        bg.render();
        p.fill(255);
        p.textAlign(PApplet.LEFT, PApplet.BOTTOM);
        p.textSize(13);
        p.text(seed, 1, p.height - 1); //  seed printing
        p.textAlign(PApplet.LEFT, PApplet.TOP);
        p.textSize(p.height * 0.03f);
        if (score < player.posX * 0.01f) {
            score = (int) (player.posX * 0.01f);
        }
        p.text(score, p.height * 0.01f, p.height * 0.01f); //  distance printing

        p.translate(posCameraX, height); //  camera alignment
        p.scale(1, -1);
        player.render();
        ();
        velocityCameraX = posCameraX + player.posX - width * 0.3f;
        posCameraX -= velocityCameraX * smoothnessCamera;
        if (player.posY < deathY || keyR) {
            reset();
        }
        cursor.hideCheck();
    }

    public void reset() {    //FIXME
        seed = (int) p.random(Integer.MAX_VALUE);
        java.lang.System.out.print(seed);
        score = 0;
        player.posX = 150f;
        player.posY = 500f;
        posCameraX = width * 0.5f;
        player.velocityX = 0f;
        player.velocityY = 0f;
        velocityCameraX = 0f;
        player.right = true;
        player.canJump = true;
        keyA = false;
        keyD = false;
        keySpace = false;
        keyR = false;
        obstacles.clear();
        obstacles.add(new Obstacle(, 50, 0, 500, 100));
        p.randomSeed(seed);
    }
}