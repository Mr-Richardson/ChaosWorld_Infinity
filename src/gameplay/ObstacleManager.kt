package gameplay

import menu.Settings
import processing.core.PApplet
import kotlin.random.Random

class ObstacleManager(private val p: PApplet, private val settings: Settings, seed: Long) {
    var obstacle: ArrayList<Obstacle> = ArrayList()
    val seededRandom = Random(seed)

    fun renderAll(color: Int) {
        for (o in obstacle) {
            o.render(color)
        }
    }

    fun generate(location: Double) {
        if (obstacle.isEmpty()) {
            obstacle.add(
                Obstacle(
                    p,
                    settings.playerStart.x - 0.1,
                    settings.playerStart.y - 0.2,
                    settings.playerStart.x + 0.1,
                    settings.playerStart.y - 0.1,
                    Obstacle.State.NORMAL
                )
            )
        }
        val zoom1 = 1f / settings.zoom
        while (obstacle.last().x2 - location <= zoom1) {
            val x1Temp = obstacle.last().x2 + Random.nextDouble(settings.obstacleDistanceMin, settings.obstacleDistanceMax)
            val y1Temp = Random.nextDouble(settings.obstacleHeightMin, settings.obstacleHeightMax)
            val x2Temp = x1Temp + Random.nextDouble(settings.obstacleWidthMin, settings.obstacleWidthMax)
            val y2Temp = y1Temp + Random.nextDouble(settings.obstacleThicknessMin, settings.obstacleThicknessMax)
            obstacle.add(Obstacle(p, x1Temp, y1Temp, x2Temp, y2Temp, Obstacle.State.entries.random()))
        }
        // Remove obstacles only when they are significantly behind the camera
        obstacle.removeAll { it.x2 < location - zoom1 }
    }
}
