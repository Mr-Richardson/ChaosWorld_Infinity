package gameplay

import Key
import Vector
import menu.Settings
import p
import processing.core.PApplet

class Character(private val obstacles: ObstacleManager, private val key: Key, private val settings: Settings) {
    private val radius = settings.playerRadius
    private val maxJump = settings.playerMaxJump
    private val friction = settings.friction
    private val epsilon = settings.epsilon
    private val maxSpeed = settings.playerMaxSpeed
    val pos = settings.playerStart.copy()
    private val vel = Vector(0.0, 0.0)
    private var canJump = true
    private var right = true
    private val sprite = p.loadImage("textures/characterTexture.png")

    fun render() {
        p.pushMatrix()
        p.translate(pos.x.toFloat() * p.width, pos.y.toFloat() * p.height)
        p.scale(if (right) 1f else -1f, 1f)
        p.imageMode(PApplet.CENTER)
        p.image(sprite, 0f, 0f, (radius * 2).toFloat(), (radius * 2).toFloat())
        p.popMatrix()
    }

    fun update() {
        velUpdate()
        collisionCheck()
        posUpdate()
    }

    fun velUpdate() {
        if (key.isRight) {
            if (canJump) {
                vel.x += maxSpeed
            } else {
                vel.x += maxSpeed / settings.playerAirInertia
            }
            right = true
        }
        if (key.isLeft) {
            if (canJump) {
                vel.x -= maxSpeed
            } else {
                vel.x -= maxSpeed / settings.playerAirInertia
            }
            right = false
        }
        vel.x *= friction
        if (key.isJump && canJump) {
            vel.y -= maxJump
            canJump = false
        } else {
            vel.y += settings.gravity
        }
    }


    fun collisionCheck() {    //TODO: time-based movement processioning
        if (vel.x > epsilon) { //  forward
            for (o in obstacles.obstacle) {
                if (o.y1 <= pos.y + radius && pos.y - radius <= o.y2 && o.x1 - pos.x - radius < vel.x && o.x1 > pos.x + radius) {
                    vel.x = o.x1 - pos.x - radius - epsilon
                }
            }
        } else if (vel.x < -epsilon) { // backward
            for (o in obstacles.obstacle) {
                if (o.y1 <= pos.y + radius && pos.y - radius <= o.y2 && o.x2 - pos.x + radius > vel.x && o.x2 < pos.x - radius) {
                    vel.x = o.x2 - pos.x + radius + epsilon
                }
            }
        }
        //  hitbox check (y)
        if (vel.y > epsilon) { //  upward
            for (o in obstacles.obstacle) {
                if (o.x1 < pos.x + radius && pos.x - radius < o.x2 && o.y1 - pos.y - radius < vel.y && o.y1 > pos.y + radius) {
                    vel.y = o.y1 - pos.y - radius - epsilon
                }
            }
        } else { // downward
            if (vel.y < -epsilon) {
                for (o in obstacles.obstacle) {
                    if (o.x1 < pos.x + radius && pos.x - radius < o.x2 && o.y2 - pos.y + radius > vel.y && o.y2 < pos.y - radius) {
                        vel.y = o.y2 - pos.y + radius + epsilon
                    }
                }
            } else {
                canJump = true
            }
        }
    }

    fun posUpdate() {
        pos.x += vel.x
        pos.y += vel.y
    }
}
