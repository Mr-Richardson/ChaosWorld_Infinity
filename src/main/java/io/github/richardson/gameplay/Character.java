package io.github.richardson.gameplay;

import io.github.richardson.Key;
import io.github.richardson.menu.Settings;
import lombok.Getter;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;


public class Character {
    private final PApplet p;
    private ObstacleManager obstacles;
    private Key key;
    private Settings settings;
    private int radius, maxJump;
    @Getter
    private PVector pos;
    private float maxSpeed;
    private PVector vel = new PVector(0, 0);
    private float airInertia;
    private boolean canJump, right;
    private PImage sprite;

    public Character(PApplet p, ObstacleManager obstacleManager, Key key, Settings settings) {
        this.p = p;
        this.obstacles = obstacleManager;
        this.key = key;
        this.settings = settings;
        this.radius = settings.getPlayerRadius();
        this.maxJump = settings.getPlayerMaxJump();
        this.maxSpeed = settings.getPlayerMaxSpeed();
        this.airInertia = settings.getPlayerAirInertia();
        this.pos = settings.getPlayerStart();
        this.sprite = p.loadImage("textures/characterTexture.png");
    }

    public void movement() {    //TODO: time-based movement processing
        PVector max = obstacles.maxUntilCollide(pos, vel, radius).copy();
        if (max.mag() < vel.mag()) {
            if (vel.mag() > 0) {
                vel.setMag(max.mag() - settings.getEpsilon());
            } else {
                vel.setMag(max.mag() + settings.getEpsilon());
            }
        }
        pos.add(vel);
    }

    public void render() {
        p.pushMatrix();
        p.translate(pos.x, pos.y);
        if (!right) {
            p.scale(-1, -1);
        } else {
            p.scale(1, -1);
        }
        p.imageMode(PApplet.CENTER);
        p.image(sprite, -radius, radius, radius, -radius);
        p.popMatrix();
    }

    public void velocityUpdate() {
        if (key.isRight()) {
            if (canJump) {
                vel.x += maxSpeed;
            } else {
                vel.x += maxSpeed / airInertia;
            }
        }
        if (key.isLeft()) {
            if (canJump) {
                vel.x -= maxSpeed;
            } else {
                vel.x -= maxSpeed / airInertia;
            }
        }
        if (key.isJump() && canJump) {
            vel.y += maxJump;
            canJump = false;
        }
    }
}
