package io.github.richardson.gameplay;

public class Camera {
    private float pos, velocity;
    private float smoothness = 0.02f;

    public float getPos() {
        return this.pos;
    }

    public float getVelocity() {
        return this.velocity;
    }

    public float getSmoothness() {
        return this.smoothness;
    }
}