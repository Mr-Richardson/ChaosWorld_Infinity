package io.github.richardson

import processing.core.PApplet

class Button(p: PApplet, cursor: Cursor, xPos: Float, yPos: Float, w: Float, h: Float, color: Int, ellipse: Boolean) {
    private val p: PApplet
    private val cursor: Cursor
    private val ellipse: Boolean
    private val xPos: Float
    private val yPos: Float
    private val w: Float
    private val h: Float
    private val color: Int

    init {
        this.p = p
        this.cursor = cursor
        this.xPos = xPos
        this.yPos = yPos
        this.w = w
        this.h = h
        this.color = color
        this.ellipse = ellipse
    }

    fun render() {
        p.fill(color)
        if (ellipse) {
            p.ellipseMode(PApplet.CENTER)
            p.ellipse(xPos, yPos, w, h)
        } else {
            p.rectMode(PApplet.CENTER)
            p.rect(xPos, yPos, w, h)
        }
    }

    fun hovered(): Boolean {
        val collide: Boolean
        val w2 = w * 0.5f
        val h2 = h * 0.5f
        if (ellipse) {
            collide =
                1 >= (p.mouseX - xPos) * (p.mouseX - xPos) / (w2 * w2) + (p.mouseY - yPos) * (p.mouseY - yPos) / (h2 * h2)
        } else {
            collide = Math.abs(p.mouseX - xPos) < w2 && Math.abs(p.mouseY - yPos) < h2
        }
        return collide && cursor.isCursorVisible
    }

    fun pressed(): Boolean {
        return hovered() && p.mousePressed
    }
}
