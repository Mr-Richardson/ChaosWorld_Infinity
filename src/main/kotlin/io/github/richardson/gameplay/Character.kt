package io.github.richardson.gameplay

import io.github.richardson.Key
import io.github.richardson.menu.Settings
import processing.core.PApplet
import processing.core.PImage
import processing.core.PVector

class Character(
    private val p: PApplet,
    private val obstacles: ObstacleManager,
    private val key: Key,
    private val settings: Settings
) {
    private val radius: Int
    private val maxJump: Int
    val pos: PVector
    private val maxSpeed: Float
    private val vel = PVector(0f, 0f)
    private val airInertia: Float
    private var canJump = true
    private val right = false
    private val sprite: PImage?

    init {
        this.radius = settings.playerRadius
        this.maxJump = settings.playerMaxJump
        this.maxSpeed = settings.playerMaxSpeed
        this.airInertia = settings.playerAirInertia
        this.pos = settings.playerStart
        this.sprite = p.loadImage("textures/characterTexture.png")
    }

    fun movement() {    //TODO: time-based movement processing
        val max = obstacles.maxUntilCollide(pos, vel, radius.toFloat()).copy()
        if (max.mag() < vel.mag()) {
            if (vel.mag() > 0) {
                vel.setMag(max.mag() - settings.epsilon)
            } else {
                vel.setMag(max.mag() + settings.epsilon)
            }
        }
        pos.add(vel)
    }

    fun render() {
        p.pushMatrix()
        p.translate(pos.x, pos.y)
        if (right) {
            p.scale(1f, -1f)
        } else {
            p.scale(-1f, -1f)
        }
        //p.imageMode(PApplet.CENTER);
        p.image(sprite, pos.x, pos.y, (radius * 2).toFloat(), (radius * 2).toFloat())
        p.popMatrix()
    }

    fun velocityUpdate() {
        if (key.isRight) {
            if (canJump) {
                vel.x += maxSpeed
            } else {
                vel.x += maxSpeed / airInertia
            }
        }
        if (key.isLeft) {
            if (canJump) {
                vel.x -= maxSpeed
            } else {
                vel.x -= maxSpeed / airInertia
            }
        }
        if (key.isJump && canJump) {
            vel.y += maxJump.toFloat()
            canJump = false
        }
    }
}
