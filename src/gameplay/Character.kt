package gameplay

import Key
import Vector
import menu.Settings
import p
import processing.core.PApplet
import processing.core.PImage
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

class Character(private val texture: PImage, private val obstacles: ObstacleManager, private val key: Key, private val settings: Settings) {
    val pos = settings.playerStart.copy()
    private val vel = Vector(0.0, 0.0)
    private var onGround = false
    private var right = true

    fun render() {

        val x = (pos.x * p.width).toFloat()
        val y = (pos.y * p.width).toFloat()
        val dia = (settings.playerRadius * 2 * p.width).toFloat()
        p.translate(x, y)
        p.scale(if (right) 1f else -1f, -1f)
        p.imageMode(PApplet.CENTER)
        p.image(texture, 0f, 0f, dia, dia) // TODO: no blurred texture
    }

    fun update() {
        velUpdate()
        collisionAndPos()
    }

    fun velUpdate() {
        if (key.isRight) {
            if (onGround) {
                vel.x += settings.playerSpeed * if (key.isSprint) 1.3 else 1.0
            } else {
                vel.x = max(
                    vel.x + abs(vel.x / settings.playerAirResistance - vel.x),
                    (vel.x + settings.playerSpeed) * settings.friction * settings.playerAirInertia
                )
            }
            right = true
        }
        if (key.isLeft) {
            if (onGround) {
                vel.x -= settings.playerSpeed * if (key.isSprint) 1.3 else 1.0
            } else {
                vel.x = min(
                    vel.x - abs(vel.x / settings.playerAirResistance - vel.x),
                    (vel.x - settings.playerSpeed) * settings.friction * settings.playerAirInertia
                )
            }
            right = false
        }
        if (onGround) {
            vel.x *= settings.friction
        } else {
            vel.x *= settings.playerAirResistance
        }
        if (key.isJump && onGround) {
            vel.y += settings.playerJump
            onGround = false
        } else {
            vel.y -= settings.gravity
        }
    }


    fun collisionAndPos() {    //TODO: time-based movement processioning
        if (vel.x > settings.epsilon) { //  forward
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
        pos.x += vel.x
        //  hitbox check (y)
        if (vel.y > 0) { //  upward
            for (o in obstacles.obstacle) {
                if (o.x1 < pos.x + settings.playerRadius && pos.x - settings.playerRadius < o.x2 && o.y1 - pos.y - settings.playerRadius < vel.y && o.y1 > pos.y + settings.playerRadius) {
                    vel.y = o.y1 - pos.y - settings.playerRadius - settings.epsilon
                }
            }
            pos.y += vel.y
        } else { // downward
            var tempY = vel.y
            for (o in obstacles.obstacle) {
                if (o.x1 < pos.x + settings.playerRadius && pos.x - settings.playerRadius < o.x2 && o.y2 - pos.y + settings.playerRadius > vel.y && o.y2 < pos.y - settings.playerRadius) {
                    tempY = o.y2 - pos.y + settings.playerRadius + settings.epsilon
                }
            }
            pos.y += tempY
            if (tempY == vel.y) {
                onGround = false
            } else {
                vel.y = 0.0
                onGround = true
            }
        }
    }
}
