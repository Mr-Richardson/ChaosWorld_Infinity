package gameplay

import processing.core.PApplet
import processing.core.PGraphics
import kotlin.math.sqrt

class Background(private val p: PApplet, c1: Int, c2: Int) {
    private val bg: PGraphics = p.createGraphics(p.width, p.height)

    init {
        this.bg.beginDraw()
        this.bg.noFill()
        for (i in 0..p.height) {
            this.bg.stroke(p.lerpColor(c1, c2, sqrt((i.toFloat() / p.height).toDouble()).toFloat()))
            this.bg.line(0f, i.toFloat(), p.width.toFloat(), i.toFloat())
        }
        this.bg.endDraw()
    }

    fun render() {
        p.image(bg, 0f, 0f)
        //java.lang.System.out.println("rendering background (" + p.millis() + "ms)");
    }
}