package io.github.richardson

import processing.core.PApplet
import processing.core.PFont

class Font internal constructor(private val p: PApplet, path: String?, size: Float) {
    private val font: PFont?


    init {
        font = p.createFont(path, size, true)
    }

    fun apply() {
        p.textFont(font)
    }
}
