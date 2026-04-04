package io.github.richardson.gameplay;

import io.github.richardson.menu.Settings;
import processing.core.PApplet;

import java.util.ArrayList;

import static java.lang.Math.random;

public class ObstacleManager {
    private final PApplet p;
    ArrayList<Obstacle> obstacles = new ArrayList<>();
    private Character player;
    private Settings settings;  //FIXME

    public ObstacleManager(PApplet p, Character player, Settings settings) {
        this.p = p;
        this.player = player;
    }

    public void renderAll() {
        for (Obstacle o : obstacles) {
            o.render();
        }
    }

    public void generate() {
        while (obstacles.getLast().getX2() - player.getPosX() <= p.width) {
            float x1Temp = (float) (obstacles.getLast().getX2()  + settings.getObstacleDistanceMin()
                                + random() * (settings.getObstacleDistanceMax()-settings.getObstacleDistanceMin()));
            float y1Temp = (float) (settings.getObstacleHeightMin()
                                + random() * (settings.getObstacleHeightMax() - settings.getObstacleHeightMin()));
            float x2Temp = (float) (x1Temp
                                + random() * (settings.getObstacleWidthMax() - settings.getObstacleWidthMin()));
            float y2Temp = (float) (y1Temp
                                + random() * (settings.getObstacleThicknessMax() - settings.getObstacleThicknessMin()));
            obstacles.add(new Obstacle(p, x1Temp, y1Temp, x2Temp, y2Temp));
        }
        // removal
        while (obstacles.getFirst().getX2() - player.getPosX() < p.width) {
            obstacles.removeFirst();
        }
    }
}

