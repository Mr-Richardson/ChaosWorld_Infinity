package gameplay

import processing.core.PApplet

class Obstacle(private val p: PApplet, val x1: Double, val y1: Double, val x2: Double, val y2: Double, var type: State) {

    fun render(color: Int) {
        p.rectMode(PApplet.CORNERS)
        p.fill(color)
        p.rect((x1 * p.width).toFloat(), (y1 * p.width).toFloat(), (x2 * p.width).toFloat(), (y2 * p.width).toFloat())
    }

    enum class State { //TODO: implement behavior of different types of obstacles
        NORMAL,
        SLIPPERY,
        BOUNCY
    }
}
