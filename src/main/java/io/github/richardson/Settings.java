package io.github.richardson;

public class Settings {
    private int deathY = -1000;
    private int obstacleDistanceMin = -100;
    private int obstacleDistanceMax = 600;
    private int obstacleHeightMin = 100;
    private int obstacleHeightMax = 300;
    private int obstacleWidthMin = 100;
    private int obstacleWidthMax = 600;
    private int obstacleThicknessMin = 10;
    private int obstacleThicknessMax = 400;
    public static final float epsilon = 0.001f;
    private float friction = 0.9f;
    p.PFont normal;
    p.PFont italic;
    p.JSONObject keySettings;
}