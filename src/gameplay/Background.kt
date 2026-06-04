package gameplay

import p
import processing.core.PApplet
import processing.core.PGraphics
import kotlin.math.sqrt

class Background(c1: Int, c2: Int) {
    private val bg: PGraphics = p.createGraphics(p.displayWidth, p.displayHeight)

    init { // TODO: eliminate color banding
        this.bg.beginDraw()
        this.bg.noFill()
        for (i in 0..p.displayHeight) {
            this.bg.stroke(p.lerpColor(c1, c2, sqrt((i.toFloat() / p.displayHeight).toDouble()).toFloat()))
            this.bg.line(0f, i.toFloat(), p.displayWidth.toFloat(), i.toFloat())
        }
        this.bg.endDraw()
    }

    fun render() {
        p.imageMode(PApplet.CORNER)
        p.image(bg, 0f, 0f, p.width.toFloat(), p.height.toFloat())
    }
}