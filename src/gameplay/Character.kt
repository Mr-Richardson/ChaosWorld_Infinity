package gameplay

import Key
import Vector
import menu.Settings
import p
import processing.core.PApplet

class Character(private val obstacles: ObstacleManager, private val key: Key, private val settings: Settings) {
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
                if (o.y1 < pos.y + settings.playerRadius && pos.y - settings.playerRadius < o.y2 && o.x1 - pos.x - settings.playerRadius < vel.x && o.x1 > pos.x + settings.playerRadius) {
                    vel.x = o.x1 - pos.x - settings.playerRadius - settings.epsilon
                }
            }
        } else if (vel.x < -settings.epsilon) { // backward
            for (o in obstacles.obstacle) {
                if (o.y1 < pos.y + settings.playerRadius && pos.y - settings.playerRadius < o.y2 && o.x2 - pos.x + settings.playerRadius > vel.x && o.x2 < pos.x - settings.playerRadius) {
                    vel.x = o.x2 - pos.x + settings.playerRadius + settings.epsilon
                }
            }
        }
        //  hitbox check (y)
        if (vel.y > settings.epsilon) { //  upward
            for (o in obstacles.obstacle) {
                if (o.x1 < pos.x + settings.playerRadius && pos.x - settings.playerRadius < o.x2 && o.y1 - pos.y - settings.playerRadius < vel.y && o.y1 > pos.y + settings.playerRadius) {
                    vel.y = o.y1 - pos.y - settings.playerRadius - settings.epsilon
                }
            }
        } else { // downward
            if (vel.y < -settings.epsilon) {
                for (o in obstacles.obstacle) {
                    if (o.x1 < pos.x + settings.playerRadius && pos.x - settings.playerRadius < o.x2 && o.y2 - pos.y + settings.playerRadius > vel.y && o.y2 < pos.y - settings.playerRadius) {
                        vel.y = o.y2 - pos.y + settings.playerRadius + settings.epsilon
                        canJump = true
                    }
                }
            }
        }
    }

    fun posUpdate() {
        pos.x += vel.x
        pos.y += vel.y
    }
}
