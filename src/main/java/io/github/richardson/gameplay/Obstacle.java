package io.github.richardson.gameplay;

import processing.core.PApplet;

public class Obstacle {
    private final PApplet p;
    State type = State.NORMAL;
    private float x1, y1, x2, y2;

    public Obstacle(PApplet p, float x1, float y1, float x2, float y2) {
        this.p = p;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public void render() {
        p.rectMode(PApplet.CORNERS);
        p.rect(x1, y1, x2, y2);
    }

    public float getX1() {
        return x1;
    }

    public float getY1() {
        return y1;
    }

    public float getX2() {
        return x2;
    }

    public float getY2() {
        return y2;
    }

    enum State {    //TODO: add different types of obstacles
        NORMAL,
        SLIPPERY,
        BOUNCY
    }
}
