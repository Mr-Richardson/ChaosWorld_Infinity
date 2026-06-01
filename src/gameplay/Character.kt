package gameplay

import Key
import menu.Settings
import processing.core.PApplet
import processing.core.PImage
import processing.core.PVector

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

    fun movement() { //TODO: time-based movement processing
        val max = obstacles.maxUntilCollide(pos, vel, radius.toFloat()).copy()
        if (max.mag() < vel.mag()) {
            vel.setMag(max.mag())
        }
        pos.add(vel)
    }

    fun render() {
        p.pushMatrix()
        p.translate(pos.x, pos.y)
        p.scale(if (right) 1f else -1f, 1f)
        p.imageMode(PApplet.CENTER)
        p.image(sprite, 0f, 0f, (radius * 2).toFloat(), (radius * 2).toFloat())
        p.popMatrix()
    }

    fun velocityUpdate() {
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
}
