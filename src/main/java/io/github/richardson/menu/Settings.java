package io.github.richardson.menu;

import lombok.Getter;
import processing.core.PApplet;

import java.io.File;

@Getter
public class Settings { //FIXME
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
    private float cameraSmoothness = 0.02f;
    private float cameraFocus = 0.3f;
    private char keyRight = 'd';
    private char keyLeft = 'a';
    private char keyJump = ' ';
    private int playerRadius = 35;
    private int playerMaxJump = 30;
    private float playerMaxSpeed = 1.2f;
    private float playerAirInertia = 5.0f;
    private float playerStartX = 150f;
    private float playerStartY = 500.0f;

    Settings(PApplet p) {
        this.p = p;
    }
}