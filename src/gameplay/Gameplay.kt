package gameplay

import ObjectManager
import processing.core.PApplet
import kotlin.properties.Delegates
import kotlin.random.Random

class Gameplay(private val p: PApplet, private val objectManager: ObjectManager) {
    private var seed by Delegates.notNull<Long>()
    private val bg: Background = Background(p, p.color(0, 50, 200), p.color(0))
    private val ui: Ui = Ui(p, objectManager)
    private lateinit var difficulty: Difficulty
    private lateinit var obstacles: ObstacleManager
    private lateinit var player: Character
    private lateinit var camera: Camera

    init {
        this.reset()
    }

    fun main() {
        physic()
        render()
    }

    fun physic() {
        obstacles.generate(player.pos.x)
        player.velocityUpdate()
        player.movement()
        difficulty.updateScore(player.pos.x)
        camera.move(player.pos.x)
        if (player.pos.y > objectManager.settings.deathY + p.height || (p.key == objectManager.settings.keyReset && p.keyPressed)) {
            reset()
        }
    }

    fun render() {
        bg.render()
        obstacles.renderAll(p.color(200, 100, 0))
        player.render()
        ui.render(seed, difficulty.score)
        objectManager.cursor.hideCheck()
    }

    fun reset() {
        seed = Random.nextLong()

        difficulty = Difficulty(0)
        obstacles = ObstacleManager(p, objectManager.settings, seed)
        player = Character(p, obstacles, objectManager.key, objectManager.settings)
        camera = Camera(p, objectManager.settings, 0f)
    }
}
