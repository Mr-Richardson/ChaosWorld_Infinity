package io.github.richardson.menu;

import processing.core.PApplet;

import java.io.File;

public class Settings { //FIXME: this is a total disaster!
    public static final float epsilon = 0.001f;
    private final PApplet p;
    File config = new File(path);
    private int deathY = -1000;
    private int obstacleDistanceMin = -100;
    private int obstacleDistanceMax = 600;
    private int obstacleHeightMin = 100;
    private int obstacleHeightMax = 300;
    private int obstacleWidthMin = 100;
    private int obstacleWidthMax = 600;
    private int obstacleThicknessMin = 10;
    private int obstacleThicknessMax = 400;
    private float friction = 0.9f;

    Settings(PApplet p) {
        this.p = p;
    }
    //TODO: add getters
}