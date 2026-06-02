import processing.core.PApplet
import processing.core.PFont

class Font internal constructor(path: String, size: Float) {
    private val font: PFont = p.createFont(path, size, true)

    fun apply() {
        p.textFont(font)
    }
}
