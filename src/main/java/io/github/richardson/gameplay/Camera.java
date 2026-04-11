package io.github.richardson.gameplay;

import io.github.richardson.menu.Settings;
import processing.core.PApplet;

public class Camera {
    private final PApplet p;
    private Settings settings;
    private float pos, velocity;

    public Camera(PApplet p, Settings settings, float startPos) {
        this.p = p;
        this.settings = settings;
        this.pos = startPos;
    }

    public void move(float location) {
        velocity = pos + location - p.width * settings.getCameraFocus();
        pos -= velocity * settings.getCameraSmoothness();
        p.translate(pos, p.height);
        p.scale(1, -1);
    }
}