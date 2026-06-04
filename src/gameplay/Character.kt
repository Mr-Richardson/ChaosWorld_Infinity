package gameplay

import Key
import Vector
import menu.Settings
import p
import processing.core.PApplet
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

class Character(private val obstacles: ObstacleManager, private val key: Key, private val settings: Settings) {
    val pos = settings.playerStart.copy()
    private val vel = Vector(0.0, 0.0)
    private var canJump = false
    private var right = true
    private val sprite = p.loadImage("textures/characterTexture.png")

    fun render() {
        val x = (pos.x * p.width).toFloat()
        val y = (pos.y * p.width).toFloat()
        val dia = (settings.playerRadius * 2 * p.width).toFloat()
        p.translate(x, y)
        p.scale(if (right) 1f else -1f, -1f)
        p.imageMode(PApplet.CENTER)
        p.image(sprite, 0f, 0f, dia, dia) // TODO: no blurred texture
    }

    fun update() {
        velUpdate()
        collisionAndPos()
    }

    fun velUpdate() {
        if (key.isRight) { // TODO: use onGround instead of canJump
            if (canJump) {
                vel.x += settings.playerSpeed * if (key.isCtrl) 1.3 else 1.0
            } else {
                vel.x = max(
                    vel.x + abs(vel.x / settings.playerAirResistance - vel.x),
                    (vel.x + settings.playerSpeed) * settings.friction * settings.playerAirInertia
                )
            }
            right = true
        }
        if (key.isLeft) {
            if (canJump) {
                vel.x -= settings.playerSpeed * if (key.isCtrl) 1.3 else 1.0
            } else {
                vel.x = min(
                    vel.x - abs(vel.x / settings.playerAirResistance - vel.x),
                    (vel.x - settings.playerSpeed) * settings.friction * settings.playerAirInertia
                )
            }
            right = false
        }
        if (canJump) {
            vel.x *= settings.friction
        } else {
            vel.x *= settings.playerAirResistance
        }
        if (key.isJump && canJump) {
            vel.y += settings.playerJump
            canJump = false
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
                        canJump = true // FIXME: hold down jump and you'll see
                    }
                }
            }
        }
        pos.y += vel.y
    }
}
