package io.github.richardson.gameplay

import processing.core.PApplet

class Obstacle(private val p: PApplet, val x1: Float, val y1: Float, val x2: Float, val y2: Float) {
    var type: State = State.NORMAL

    fun render(color: Int) {
        p.rectMode(PApplet.CORNERS)
        p.fill(color)
        p.rect(x1, y1, x2, y2)
    }

    internal enum class State {
        //TODO: add different types of obstacles
        NORMAL,
        SLIPPERY,
        BOUNCY
    }
}
