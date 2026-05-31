package io.github.richardson.gameplay

import io.github.richardson.menu.Settings
import processing.core.PApplet
import processing.core.PVector

class ObstacleManager(private val p: PApplet, private val settings: Settings) {
    var obstacle: ArrayList<Obstacle> = ArrayList<Obstacle>()

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
                    settings.playerStart.y - 200,
                    settings.playerStart.x + 100,
                    settings.playerStart.y - 100
                )
            )
        }
        while (obstacle.get(obstacle.toTypedArray().size - 1).getX2() - location <= p.width) {
            val x1Temp = ((obstacle.get(obstacle.toTypedArray().size - 1).getX2() + settings.obstacleDistanceMin
                    + Math.random() * (settings.obstacleDistanceMax - settings.obstacleDistanceMin))).toFloat()
            val y1Temp = (settings.obstacleHeightMin
                    + Math.random() * (settings.obstacleHeightMax - settings.obstacleHeightMin)).toFloat()
            val x2Temp = (x1Temp
                    + Math.random() * (settings.obstacleWidthMax - settings.obstacleWidthMin)).toFloat()
            val y2Temp = (y1Temp
                    + Math.random() * (settings.obstacleThicknessMax - settings.obstacleThicknessMin)).toFloat()
            obstacle.add(Obstacle(p, x1Temp, y1Temp, x2Temp, y2Temp))
        }
        // removal
        while (obstacle.getFirst().getX2() - location < p.width) {
            obstacle.removeFirst()
        }
    }

    fun maxUntilCollide(pos: PVector, vel: PVector, radius: Float): PVector {
        for (o in obstacle) {
            val a: Float
            val b: Float
            val am: Float
            val bm: Float
            if (vel.y > 0) {
                am = (o.getY1() - radius - pos.y) / vel.y
            } else {
                am = (o.getY2() + radius - pos.y) / vel.y
            }
            a = vel.x * am + pos.x
            if (vel.x > 0) {
                bm = (o.getX1() - radius - pos.x) / vel.x
            } else {
                bm = (o.getX2() + radius - pos.x) / vel.x
            }
            b = vel.y * bm + pos.y
            //X
            if (o.getX2() + radius > a && o.getX1() - radius < a) {
                vel.mult(am)
            } else if (o.getY2() + radius > b && o.getY1() - radius < b) {
                vel.mult(bm)
            }
        }
        return vel
    }
}

