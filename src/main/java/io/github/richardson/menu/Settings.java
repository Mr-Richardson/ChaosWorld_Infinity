package io.github.richardson.menu;

import lombok.Getter;
import processing.core.PVector;

@Getter
public class Settings {
    public float epsilon = 0.001f;
    //File config = new File(path);    TODO: save settings in file
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
    private char keyReset = 'r';
    private int playerRadius = 35;
    private int playerMaxJump = 30;
    private float playerMaxSpeed = 1.2f;
    private float playerAirInertia = 5.0f;
    private PVector playerStart = new PVector(150.0f, 500);

    public Settings() {
    }
}