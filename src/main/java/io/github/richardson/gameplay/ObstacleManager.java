package io.github.richardson.gameplay;

import io.github.richardson.menu.Settings;
import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

import static java.lang.Math.random;

public class ObstacleManager {
    private final PApplet p;
    ArrayList<Obstacle> obstacle = new ArrayList<>();
    private Character player;
    private Settings settings;

    public ObstacleManager(PApplet p, Character player, Settings settings) {
        this.p = p;
        this.player = player;
        this.settings = settings;
    }

    public void renderAll() {
        for (Obstacle o : obstacle) {
            o.render();
        }
    }

    public void generate() {
        while (obstacle.getLast().getX2() - player.getPos().x <= p.width) {
            float x1Temp = (float) (obstacle.getLast().getX2() + settings.getObstacleDistanceMin()
                    + random() * (settings.getObstacleDistanceMax() - settings.getObstacleDistanceMin()));
            float y1Temp = (float) (settings.getObstacleHeightMin()
                    + random() * (settings.getObstacleHeightMax() - settings.getObstacleHeightMin()));
            float x2Temp = (float) (x1Temp
                    + random() * (settings.getObstacleWidthMax() - settings.getObstacleWidthMin()));
            float y2Temp = (float) (y1Temp
                    + random() * (settings.getObstacleThicknessMax() - settings.getObstacleThicknessMin()));
            obstacle.add(new Obstacle(p, x1Temp, y1Temp, x2Temp, y2Temp));
        }
        // removal
        while (obstacle.getFirst().getX2() - player.getPos().x < p.width) {
            obstacle.removeFirst();
        }
    }

    public PVector maxUntilCollide(PVector pos, PVector vel, float radius) {
        for (Obstacle o : obstacle) {
            float a, b, am, bm;
            if (vel.y > 0) {
                am = (o.getY1() - radius - pos.y) / vel.y;
            } else {
                am = (o.getY2() + radius - pos.y) / vel.y;
            }
            a = vel.x * am + pos.x;
            if (vel.x > 0) {
                bm = (o.getX1() - radius - pos.x) / vel.x;
            } else {
                bm = (o.getX2() + radius - pos.x) / vel.x;
            }
            b = vel.y * bm + pos.y;
            //X
            if (o.getX2() + radius > a && o.getX1() - radius < a) {
                vel.mult(am);
            } else if (o.getY2() + radius > b && o.getY1() - radius < b) {
                vel.mult(bm);
            }
        }
        return vel;
    }
}

