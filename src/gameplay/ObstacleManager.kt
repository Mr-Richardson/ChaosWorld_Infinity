package gameplay

import Vector
import menu.Settings
import kotlin.random.Random

class ObstacleManager(private val settings: Settings, seed: Long) {
    var obstacle: ArrayList<Obstacle> = ArrayList()
    val seededRandom = Random(seed)

    fun renderAll(color: Int) {
        for (o in obstacle) {
            o.render(color)
        }
    }

    fun generate(posCamera: Double, posCharacter: Vector) {
        if (obstacle.isEmpty()) {
            obstacle.add(
                Obstacle(
                    posCharacter.x - 0.1,
                    posCharacter.y - 0.2,
                    posCharacter.x + 0.1,
                    posCharacter.y - 0.1,
                    Obstacle.State.NORMAL
                )
            )
        }
        val zoom1 = 1f / settings.zoom
        while (obstacle.last().x2 - posCamera <= zoom1 || obstacle.last().x2 - posCharacter.x <= 0.0) { // TODO: make it relative to last obstacle
            val x1Temp = obstacle.last().x2 + seededRandom.nextDouble(settings.obstacleDistanceMin, settings.obstacleDistanceMax)
            val y1Temp = seededRandom.nextDouble(settings.obstacleHeightMin, settings.obstacleHeightMax)
            val x2Temp = x1Temp + seededRandom.nextDouble(settings.obstacleWidthMin, settings.obstacleWidthMax)
            val y2Temp = y1Temp + seededRandom.nextDouble(settings.obstacleThicknessMin, settings.obstacleThicknessMax)
            obstacle.add(
                Obstacle(
                    x1Temp,
                    y1Temp,
                    x2Temp,
                    y2Temp,
                    Obstacle.State.entries[seededRandom.nextInt(Obstacle.State.entries.size)]
                )
            )
        }
        // Remove obstacles only when they are significantly behind the camera
        obstacle.removeAll { it.x2 < posCamera - zoom1 }
    }
}
