package io.github.richardson.gameplay;

import processing.core.PApplet;

import java.util.ArrayList;

import static java.lang.Math.random;

public class Obstacle {
    public final PApplet p;
    public float x1, y1, x2, y2;
    ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();

    public Obstacle(PApplet p, float x1, float y1, float x2, float y2) {
        this.p = p;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public void render() {
        if (x1 <= -posCameraX + width && x2 >= -posCameraX) {
            p.rect(x1, y1, x2, y2);
        }
    }


    public void generateObstacles() {
        // generation
        while (obstacles.get(obstacles.size() - 1).x2 - player.posX <= width) {
            float x1Temp =
                    obstacles.get(obstacles.size() - 1).x2 + random(obstacleDistanceMin, obstacleDistanceMax);
            float y1Temp = random(obstacleHeightMin, obstacleHeightMax);
            float x2Temp = x1Temp + random(obstacleWidthMin, obstacleWidthMax);
            float y2Temp = y1Temp + random(obstacleThicknessMin, obstacleThicknessMax);
            obstacles.add(new Obstacle(, x1Temp, y1Temp, x2Temp, y2Temp));
        }
        // removal
        while (obstacles.get(0).x2 - player.posX < p.width) {
            obstacles.remove(0);
        }
    }

    public void renderObstacles() {
        for (int i = 0; i < obstacles.size(); i++) {
            obstacles.get(i).render();
        }
    }
}
