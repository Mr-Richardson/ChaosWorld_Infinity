package io.github.richardson.gameplay;

import io.github.richardson.menu.Settings;
import processing.core.PApplet;

import java.util.ArrayList;

import static java.lang.Math.random;

public class ObstacleManager {
    private final PApplet p;
    ArrayList<Obstacle> obstacles = new ArrayList<>();
    private Character player;
    private Settings settings;  //fixme

    public ObstacleManager(PApplet p, Character player, Settings settings) {
        this.p = p;
        this.player = player;
    }

    public void renderAll() {
        for (Obstacle o : obstacles) {
            o.render();
        }
    }

    public void generate() {    //FIXME
        while (obstacles.getLast().getX2() - player.getPosX() <= width) {
            float x1Temp =
                    obstacles.getLast().x2 + random(obstacleDistanceMin, obstacleDistanceMax);
            float y1Temp = random(obstacleHeightMin, obstacleHeightMax);
            float x2Temp = x1Temp + random(obstacleWidthMin, obstacleWidthMax);
            float y2Temp = y1Temp + random(obstacleThicknessMin, obstacleThicknessMax);
            obstacles.add(new Obstacle(x1Temp, y1Temp, x2Temp, y2Temp));
        }
        // removal
        while (obstacles.getFirst().getX2() - player.getPosX() < p.width) {
            obstacles.removeFirst();
        }
    }
}

