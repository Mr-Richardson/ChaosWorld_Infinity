package gameplay

import ObjectManager
import processing.core.PApplet

class Gameplay(private val p: PApplet, private val objectManager: ObjectManager) {
    private var seed = 0
    private var score = 0
    private var bg: Background = Background(p, p.color(0, 50, 200), p.color(0))
    private lateinit var obstacles: ObstacleManager
    private lateinit var player: Character
    private lateinit var camera: Camera

    init {
        this.reset()
    }

    fun main() {
        obstacles.generate(player.pos.x)
        player.velocityUpdate()
        player.movement()
        bg.render()
        p.fill(255)
        p.textAlign(PApplet.LEFT, PApplet.BOTTOM)
        p.textSize(13f)
        objectManager.font.apply()
        p.text(seed, 1f, (p.height - 1).toFloat()) //  seed printing
        p.textAlign(PApplet.LEFT, PApplet.TOP)
        p.textSize(p.height * 0.03f)
        if (score < player.pos.x * 0.01f) {
            score = (player.pos.x * 0.01f).toInt()
        }
        p.text(score, p.height * 0.01f, p.height * 0.01f) //  distance printing
        camera.move(player.pos.x)

        obstacles.renderAll(p.color(200, 100, 0))
        player.render()
        if (player.pos.y > objectManager.settings.deathY + p.height || (p.key == objectManager.settings.keyReset && p.keyPressed)) {
            reset()
        }
        objectManager.cursor.hideCheck()
    }

    fun render() {
        bg.render()
        camera.move(player.pos.x)
        player.render()
        obstacles.renderAll(p.color(200, 100, 0))
    }

    fun physic() {
        obstacles.generate(player.pos.x)
        player.velocityUpdate()
        player.movement()
    }

    fun reset() {
        seed = p.random(Int.MAX_VALUE.toFloat()).toInt()
        p.randomSeed(seed.toLong()) //FIXME: i switched to Javas random

        score = 0

        obstacles = ObstacleManager(p, objectManager.settings)
        player = Character(p, obstacles, objectManager.key, objectManager.settings)
        camera = Camera(p, objectManager.settings, 0f)
    }
}
