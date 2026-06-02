package gameplay

import Key
import menu.Settings
import processing.core.PApplet
import processing.core.PImage
import processing.core.PVector
import kotlin.Float.Companion.NEGATIVE_INFINITY
import kotlin.Float.Companion.POSITIVE_INFINITY
import kotlin.compareTo
import kotlin.math.max
import kotlin.math.min

class Character(
    private val p: PApplet,
    private val obstacles: ObstacleManager,
    private val key: Key,
    private val settings: Settings
) {
    private val radius: Int = settings.playerRadius
    private val maxJump: Float = settings.playerMaxJump
    val pos: PVector = settings.playerStart
    private val maxSpeed: Float = settings.playerMaxSpeed
    private val vel = PVector(0f, 0f)
    private val airInertia: Float = settings.playerAirInertia
    private var canJump = true
    private var right = true
    private val sprite: PImage = p.loadImage("textures/characterTexture.png")

    fun render() {
        p.pushMatrix()
        p.translate(pos.x, pos.y)
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
                vel.x += maxSpeed / airInertia
            }
            right = true
        }
        if (key.isLeft) {
            if (canJump) {
                vel.x -= maxSpeed
            } else {
                vel.x -= maxSpeed / airInertia
            }
            right = false
        }
        if (key.isJump && canJump) {
            vel.y -= maxJump
            //canJump = false
        } else {
            vel.y += settings.gravity
        }
    }


    fun collisionCheck() {    //TODO: time-based movement processioning
        if (vel.x > settings.epsilon) { //  forward
            for (o in obstacles.obstacle) {
                if (o.y1 <= pos.y + radius && pos.y - radius <= o.y2 && o.x1 - pos.x - radius < vel.x && o.x1 > pos.x + radius) {
                    vel.x = o.x1 - pos.x - radius - settings.epsilon
                }
            }
        } else if (vel.x < -settings.epsilon) { // backward
            for (o in obstacles.obstacle) {
                if (o.y1 <= pos.y + radius && pos.y - radius <= o.y2 && o.x2 - pos.x + radius > vel.x && o.x2 < pos.x - radius) {
                    vel.x = o.x2 - pos.x + radius + settings.epsilon
                }
            }
        }
        //  hitbox check (y)
        if (vel.y > settings.epsilon) { //  upward
            for (o in obstacles.obstacle) {
                if (o.x1 < pos.x + radius && pos.x - radius < o.x2 && o.y1 - pos.y - radius < vel.y && o.y1 > pos.y + radius) {
                    vel.y = o.y1 - pos.y - radius - settings.epsilon
                }
            }
        } else { // downward
            if (vel.y < -settings.epsilon) {
                for (o in obstacles.obstacle) {
                    if (o.x1 < pos.x + radius && pos.x - radius < o.x2 && o.y2 - pos.y + radius > vel.y && o.y2 < pos.y - radius) {
                        vel.y = o.y2 - pos.y + radius + settings.epsilon
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
