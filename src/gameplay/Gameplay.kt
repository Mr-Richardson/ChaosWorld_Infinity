package gameplay

import ObjectManager
import p
import kotlin.properties.Delegates
import kotlin.random.Random

class Gameplay(private val objectManager: ObjectManager) {
    private var seed by Delegates.notNull<Long>()
    private val bg: Background = Background(p.color(0, 50, 200), p.color(0))
    private val ui: Ui = Ui(objectManager)
    private lateinit var difficulty: Difficulty
    private lateinit var obstacles: ObstacleManager
    private lateinit var player: Character
    private lateinit var camera: Camera

    init {
        this.reset()
    }

    fun main() {
        physic() // TODO: separate physic thread
        render()
    }

    private fun physic() {
        obstacles.generate(player.pos.x)
        //player.update() TODO: debug
        obstacles.generate(camera.pos)
        difficulty.updateScore(player.pos.x)
        keyInput()
    }

    private fun render() {
        bg.render()
        camera.move(player.pos.x)
        obstacles.renderAll(p.color(200, 100, 0))
        player.render()
        ui.render(seed, difficulty.score)
        objectManager.cursor.hideCheck()
    }

    fun reset() {
        seed = Random.nextLong()

        difficulty = Difficulty(0)
        obstacles = ObstacleManager(objectManager.settings, seed)
        player = Character(obstacles, objectManager.key, objectManager.settings)
        camera = Camera(objectManager.settings, 0.0)
    }

    private fun keyInput() {
        if (objectManager.key.isZoomIn) {
            objectManager.settings.zoom *= 1.01f // TODO: faster zooming with "CONTROL"
        }
        if (objectManager.key.isZoomOut) {
            objectManager.settings.zoom *= 0.99f
        }
        if (player.pos.y <= objectManager.settings.deathY || objectManager.key.isReset) {
            reset()
        }
    }
}
