package io.github.richardson.gameplay;

import processing.core.PApplet;
import processing.core.PImage;

import static io.github.richardson.menu.Settings.epsilon;

public class Character {
    private final PApplet p;
    private int radius, maxJump;
    private float posX, posY, maxSpeed, velocityX, velocityY, airInertia;
    private boolean canJump, right;
    private PImage sprite;

    public Character(PApplet p, int radius, int maxJump, float maxSpeed, float airInertia, float x, float y) {
        this.p = p;
        this.radius = radius;
        this.maxJump = maxJump;
        this.maxSpeed = maxSpeed;
        this.airInertia = airInertia;
        this.posX = x;
        this.posY = y;
        this.sprite = p.loadImage("textures/characterTexture.png");
    }

    public void movement() {    //TODO: time-based movement processioning
        //  variables
        float distanceX;
        float distanceY;
        //  hitbox check (x)
        if (velocityX > epsilon) { //  forward
            distanceX = Float.POSITIVE_INFINITY;
            for (int i = 0; i < obstacles.size(); i++) {
                Obstacle o = obstacles.get(i);
                if (o.y1 <= posY + radius
                        && posY - radius <= o.y2
                        && o.x1 - posX - radius < distanceX
                        && o.x1 > posX + radius) {
                    distanceX = o.x1 - posX - radius;
                }
            }
            //  position & momentum update (x)
            float moveX = Math.min(velocityX, distanceX);
            posX += moveX - epsilon;
            if (moveX == distanceX || velocityX < epsilon) {
                velocityX = 0;
            } else {
                if (canJump) {
                    velocityX *= friction;
                } else {
                    velocityX *= (friction + airInertia - 1) / airInertia;
                }
            }
        } else { // backward
            distanceX = Float.NEGATIVE_INFINITY;
            if (velocityX < -epsilon) {
                for (int i = 0; i < obstacles.size(); i++) {
                    Obstacle o = obstacles.get(i);
                    if (o.y1 <= posY + radius
                            && posY - radius <= o.y2
                            && o.x2 - posX + radius > distanceX
                            && o.x2 < posX - radius) {
                        distanceX = o.x2 - posX + radius;
                    }
                }
                //  position & momentum update (x)
                float moveX = Math.max(velocityX, distanceX);
                posX += moveX + epsilon;
                if (moveX == distanceX || velocityX > -epsilon) {
                    velocityX = 0;
                } else {
                    if (canJump) {
                        velocityX *= friction;
                    } else {
                        velocityX *= (friction + airInertia - 1) / airInertia;
                    }
                }
            }
        }
        //  hitbox check (y)
        if (velocityY > epsilon) { //  upward
            distanceY = Float.POSITIVE_INFINITY;
            for (int i = 0; i < obstacles.size(); i++) {
                Obstacle o = obstacles.get(i);
                if (o.x1 < posX + radius
                        && posX - radius < o.x2
                        && o.y1 - posY - radius < distanceY
                        && o.y1 > posY + radius) {
                    distanceY = o.y1 - posY - radius;
                }
            }
            float moveY = Math.min(velocityY, distanceY);
            posY += moveY - epsilon;
            if (moveY == distanceY) {
                velocityY = 0;
            } else {
                velocityY -= 1;
            }
        } else { // downward
            distanceY = Float.NEGATIVE_INFINITY;
            if (velocityY < -epsilon) {
                for (int i = 0; i < obstacles.size(); i++) {
                    Obstacle o = obstacles.get(i);
                    if (o.x1 < posX + radius
                            && posX - radius < o.x2
                            && o.y2 - posY + radius > distanceY
                            && o.y2 < posY - radius) {
                        distanceY = o.y2 - posY + radius;
                    }
                }
            }
            //  position & momentum update (y)
            float moveY = Math.max(velocityY, distanceY);
            posY += moveY + epsilon; // Add negative velocity to move down
            if (moveY == distanceY) {
                velocityY = 0; // Hit floor
                canJump = true; // Allow jumping again
            } else {
                velocityY -= 1.5;
            }
        }
    }   //FIXME

    public void render() {
        p.pushMatrix();
        p.translate(posX, posY);
        if (!right) {
            p.scale(-1, -1);
        } else {
            p.scale(1, -1);
        }
        p.imageMode(PApplet.CENTER);
        p.image(sprite, -radius, radius, radius, -radius);
        p.popMatrix();
    }

    public float getPosX() {
        return posX;
    }
    //maybe more getter/setter
}
