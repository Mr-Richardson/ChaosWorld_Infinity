package gameplay

import menu.Settings
import processing.core.PApplet
import processing.core.PVector

class ObstacleManager(private val p: PApplet, private val settings: Settings) {
    var obstacle: ArrayList<Obstacle> = ArrayList()

    fun renderAll(color: Int) {
        for (o in obstacle) {
            o.render(color)
        }
    }

    fun generate(location: Float) {
        if (obstacle.isEmpty()) {
            obstacle.add(
                Obstacle(
                    p,
                    settings.playerStart.x - 100,
                    settings.playerStart.y + 200,
                    settings.playerStart.x + 100,
                    settings.playerStart.y + 100,
                    Obstacle.State.NORMAL
                )
            )
        }
        while (obstacle.last().x2 - location <= p.width) {
            val x1Temp = (obstacle.last().x2 + settings.obstacleDistanceMin
                    + Math.random() * (settings.obstacleDistanceMax - settings.obstacleDistanceMin)).toFloat()
            val y1Temp = (settings.obstacleHeightMin
                    + Math.random() * (settings.obstacleHeightMax - settings.obstacleHeightMin)).toFloat()
            val x2Temp = (x1Temp
                    + Math.random() * (settings.obstacleWidthMax - settings.obstacleWidthMin)).toFloat()
            val y2Temp = (y1Temp
                    + Math.random() * (settings.obstacleThicknessMax - settings.obstacleThicknessMin)).toFloat()
            obstacle.add(Obstacle(p, x1Temp, y1Temp, x2Temp, y2Temp, Obstacle.State.NORMAL))
        }
        // Remove obstacles only when they are significantly behind the camera
        obstacle.removeAll { it.x2 < location - p.width }
    }

    fun maxUntilCollide(pos: PVector, vel: PVector, radius: Float): PVector { // TODO: this is unfinished
        for (o in obstacle) {
            val a: Float
            val b: Float
            val am: Float = if (vel.y > 0) {
                (o.y1 - radius - pos.y) / vel.y
            } else {
                (o.y2 + radius - pos.y) / vel.y
            }
            a = vel.x * am + pos.x
            val bm: Float = if (vel.x > 0) {
                (o.x1 - radius - pos.x) / vel.x
            } else {
                (o.x2 + radius - pos.x) / vel.x
            }
            b = vel.y * bm + pos.y
            //X
            if (o.x2 + radius > a && o.x1 - radius < a) {
                vel.mult(am)
            } else if (o.y2 + radius > b && o.y1 - radius < b) {
                vel.mult(bm)
            }
        }
        return vel
    }
}
