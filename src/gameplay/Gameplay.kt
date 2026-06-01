package gameplay

import ObjectManager
import processing.core.PApplet
import kotlin.math.max

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
        p.textSize(13f * objectManager.settings.uiScale)
        objectManager.font.apply()
        p.text(seed, 1f * objectManager.settings.uiScale, (p.height - 1).toFloat() * objectManager.settings.uiScale) //  seed printing
        p.textAlign(PApplet.LEFT, PApplet.TOP)
        p.textSize(0.03f * objectManager.settings.uiScale)
        score = max(score, (player.pos.x * 0.01f).toInt())
        p.text(score, 10 * objectManager.settings.uiScale, p.height * objectManager.settings.uiScale) //  distance printing
        camera.move(player.pos.x)

        obstacles.renderAll(p.color(200, 100, 0))
        player.render()
        if (player.pos.y > objectManager.settings.deathY + p.height || (p.key == objectManager.settings.keyReset && p.keyPressed)) {
            reset()
        }
        objectManager.cursor.hideCheck()
    }

    fun render() { // TODO
        bg.render()
        camera.move(player.pos.x)
        player.render()
        obstacles.renderAll(p.color(200, 100, 0))
    }

    fun physic() { // TODO
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
