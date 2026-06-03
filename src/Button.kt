import processing.core.PApplet
import kotlin.math.abs

class Button(
    private val cursor: Cursor,
    private val pos: Vector,
    private val size: Vector, // TODO: finish refactor
    private val color: Int,
    private val ellipse: Boolean
) {

    fun render() {
        p.fill(color)
        if (ellipse) {
            p.ellipseMode(PApplet.CENTER)
            p.ellipse((pos.x * p.width).toFloat(), (pos.y * p.width).toFloat(), (size.x * p.width).toFloat(), (size.y * p.width).toFloat())
        } else {
            p.rectMode(PApplet.CENTER)
            p.rect((pos.x * p.width).toFloat(), (pos.y * p.width).toFloat(), (size.x * p.width).toFloat(), (size.y * p.width).toFloat())
        }
    }

    fun hovered(): Boolean {
        val x = pos.x * p.width
        val y = pos.y * p.width
        val w2 = size.x * 0.5f * p.width
        val h2 = size.y * 0.5f * p.width
        val collide = if (ellipse) {
            1 >= (p.mouseX - x) * (p.mouseX - x) / (w2 * w2) + (p.mouseY - y) * (p.mouseY - y) / (h2 * h2)
        } else {
            abs(p.mouseX - x) < w2 && abs(p.mouseY - y) < h2
        }
        return collide && cursor.isCursorVisible
    }

    fun pressed(): Boolean {
        return hovered() && p.mousePressed
    }
}
