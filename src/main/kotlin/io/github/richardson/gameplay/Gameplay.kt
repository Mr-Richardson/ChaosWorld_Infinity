package io.github.richardson.gameplay

import io.github.richardson.ObjectManager
import processing.core.PApplet

class Gameplay(private val p: PApplet, private val objectManager: ObjectManager) {
    private var seed = 0
    private var score = 0
    private var bg: Background? = null
    private var player: Character? = null
    private var obstacles: ObstacleManager? = null
    private var camera: Camera? = null

    init {
        this.reset()
    }

    fun main() {
        obstacles!!.generate(player!!.getPos().x)
        player!!.velocityUpdate()
        player!!.movement()
        bg!!.render()
        p.fill(255)
        p.textAlign(PApplet.LEFT, PApplet.BOTTOM)
        p.textSize(13f)
        objectManager.font.apply()
        p.text(seed, 1f, (p.height - 1).toFloat()) //  seed printing
        p.textAlign(PApplet.LEFT, PApplet.TOP)
        p.textSize(p.height * 0.03f)
        if (score < player!!.getPos().x * 0.01f) {
            score = (player!!.getPos().x * 0.01f).toInt()
        }
        p.text(score, p.height * 0.01f, p.height * 0.01f) //  distance printing
        camera!!.move(player!!.getPos().x)

        obstacles!!.renderAll(p.color(200, 100, 0))
        player!!.render()
        if (player!!.getPos().x < objectManager.settings.deathY || p.key == objectManager.settings.keyReset) {
            reset()
        }
        objectManager.cursor.hideCheck()
    }

    fun render() {
        bg!!.render()
        camera!!.move(player!!.getPos().x)
        //player.render();
        obstacles!!.renderAll(p.color(200, 100, 0))
    }

    fun physic() {
        obstacles!!.generate(player!!.getPos().x)
        player!!.velocityUpdate()
        player!!.movement()
    }

    fun reset() {
        seed = p.random(Int.MAX_VALUE.toFloat()).toInt()
        p.randomSeed(seed.toLong()) //FIXME: i switched to Javas random
        println("Seed: " + seed)

        score = 0

        bg = null
        player = null
        obstacles = null
        camera = null

        bg = Background(p, p.color(0, 50, 200), p.color(0))
        obstacles = ObstacleManager(p, objectManager.settings)
        player = Character(p, obstacles, objectManager.key, objectManager.settings)
        camera = Camera(p, objectManager.settings, 0f)
    }
}